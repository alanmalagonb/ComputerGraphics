using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Torus : MonoBehaviour
{
    public Color c1 = Color.yellow;
    public Color c2 = Color.red;
    public int lengthOfLineRenderer = 1000;
    public float tIni = 0.0f;
    public float tFin = 100;
    public float R = 2.0f;
    public float r = 0.5f;

    void Start()
    {
        LineRenderer lineRenderer = gameObject.AddComponent<LineRenderer>();
        lineRenderer.material = new Material(Shader.Find("Sprites/Default"));
        lineRenderer.widthMultiplier = 0.2f;
        lineRenderer.positionCount = lengthOfLineRenderer;

        // A simple 2 color gradient with a fixed alpha of 1.0f.
        float alpha = 1.0f;
        Gradient gradient = new Gradient();
        gradient.SetKeys(
            new GradientColorKey[] { new GradientColorKey(c1, 0.0f), new GradientColorKey(c2, 1.0f) },
            new GradientAlphaKey[] { new GradientAlphaKey(alpha, 0.0f), new GradientAlphaKey(alpha, 1.0f) }
        );
        lineRenderer.colorGradient = gradient;
    }

    (float x, float y, float z) torus(float t, float u)
    {
        float xCoord = (R + r * Mathf.Cos(u)) * Mathf.Cos(t);
        float yCoord = (R + r * Mathf.Cos(u)) * Mathf.Sin(t);
        float zCoord = r * Mathf.Sin(u);
        return (xCoord, yCoord, zCoord);
    }

    public Vector3 newPosition = new Vector3(0, 0, 0);

    void Update()
    {
        LineRenderer lineRenderer = GetComponent<LineRenderer>();

        float deltaT = (tFin - tIni) / (lengthOfLineRenderer - 1);
        float deltaU = (2 * Mathf.PI) / (lengthOfLineRenderer - 1);
        float t = tIni, u = 0.0f;
        float x, y, z;

        for (int i = 0; i < lengthOfLineRenderer; i++)
        {
            (x, y, z) = torus(t, u);
            newPosition.x = x;
            newPosition.y = y;
            newPosition.z = z;
            lineRenderer.SetPosition(i, newPosition);

            t += deltaT;
            u += deltaU;
            u %= (2 * Mathf.PI);
        }
    }
}