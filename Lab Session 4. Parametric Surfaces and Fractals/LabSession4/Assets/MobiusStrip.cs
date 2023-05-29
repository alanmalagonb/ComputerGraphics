using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MobiusStrip : MonoBehaviour
{
	private List<Vector2> uvs = new List<Vector2>();
	public float uResolution = .1f;
	public float vResolution = .1f;
	public int planeResolution = 100;

	private List<Vector3> vertices = new List<Vector3>();
	private List<int> triangles = new List<int>();
	
	void Start()
	{
		vertices = new List<Vector3>(planeResolution * planeResolution);
		float u = 0;
		float v = -1;
		float uStepSize = (Mathf.PI * 2) / planeResolution;
		float vStepSize = 2.0f / planeResolution;
		v += vStepSize;
		float currX = 0;
		while (u <= Mathf.PI * 2.0f)
		{
			float currY = 0;

			while (v <= 1)
			{
				uvs.Add(new Vector2(currX / (planeResolution - 1), currY / (planeResolution - 1)));
				float x = (1 + ((v / 2.0f) * Mathf.Cos(u / 2.0f))) * Mathf.Cos(u);
				float y = (1 + ((v / 2.0f) * Mathf.Cos(u / 2.0f))) * Mathf.Sin(u);
				float z = (v / 2.0f) * Mathf.Sin((u) / 2.0f);
				Vector3 position = new Vector3(x, y, z);
				vertices.Add(position);
				v += vStepSize;
				currY++;
			}
			currX++;
			v = -1 + vStepSize;
			u += uStepSize;
		}

		for (int i = 0; i < vertices.Count; i++)
		{
			if (!((i + 1) % (planeResolution) == 0))
			{
				int index1 = i + 1;
				int index2 = i + planeResolution;
				int index3 = i + planeResolution + 1;
				if (index1 % vertices.Count != index1)
				{
					index1 %= vertices.Count;
					index1 = planeResolution - index1 - 1;
				}
				if (index2 % vertices.Count != index2)
				{
					index2 %= vertices.Count;
					index2 = planeResolution - index2 - 1;
				}
				if (index3 % vertices.Count != index3)
				{
					index3 %= vertices.Count;
					index3 = planeResolution - index3 - 1;
				}

				triangles.Add(i);
				triangles.Add(index1);
				triangles.Add(index2);

				triangles.Add(index2);
				triangles.Add(index1);
				triangles.Add(index3);
			}
		}

		Mesh m = new Mesh();
		m.vertices = vertices.ToArray();
		m.triangles = triangles.ToArray();
		m.uv = uvs.ToArray();
		m.RecalculateNormals();

		GetComponent<MeshFilter>().mesh = m;
	}

	void Update()
	{

	}
}