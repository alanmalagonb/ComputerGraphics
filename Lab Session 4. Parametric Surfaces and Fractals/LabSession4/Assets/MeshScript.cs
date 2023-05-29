using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MeshScript : MonoBehaviour
{
    public MeshFilter myMeshFilter;
    public MeshRenderer myMeshRenderer;
    // Start is called before the first frame update
    void Start()
    {
        myMeshRenderer = GetComponent<MeshRenderer>();
        myMeshFilter = GetComponent<MeshFilter>();
        Generate();
    }

    void Generate() {
        Vector3[] vertices = new Vector3[3]
            {
                new Vector3(-1,0,0),new Vector3(0,1,0),new Vector3(1,0,0)
            };
        int[] triangles = new int[6]
           { 
               0,1,2,1,0,2
           };
        myMeshFilter.mesh.vertices = vertices;
        myMeshFilter.mesh.triangles = triangles;

        myMeshFilter.mesh.RecalculateBounds();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}

