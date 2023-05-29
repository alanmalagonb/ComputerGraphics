using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Surface : MonoBehaviour
{
    // public Material myMaterial;
    public MeshFilter myMeshFilter;
    public MeshRenderer myMeshRenderer;
    // Start is called before the first frame update
    void Start()
    {
        myMeshFilter = GetComponent<MeshFilter>();
        myMeshRenderer = GetComponent<MeshRenderer>();
        // Generate();
        BezierSurface();
    }

    float factorial(int n)
    {
        float f = 1;
        if (n == 0 || n == 1) return 1;
        for(int i=2;i<=n;i++)
        {
            f*= i;
        }
        return f;
    }

    int combinations(int n, int i)
    {
        return (int)(factorial(n) / (factorial(i) * factorial(n - i)));
    }

    public int n = 2, m= 2, sizeU=10, sizeV=10;
    public float[] coefficientsN, coefficientsM;
    public Vector3[,] k;
    public Vector3[] S;
    public int[] triangles;

    void initializeSt() {
        S = new Vector3[sizeU * sizeV];
        triangles = new int[(sizeU - 1) * (sizeV - 1) * 3];

        for (int i = 0; i < sizeU; i++)
        {
            for (int j = 0; j < sizeV; j++)
            {
                S[i * sizeV + j] = new Vector3(0, 0, 0);
                if (i < (sizeU - 1) && j < (sizeV - 1))
                {
                    triangles[(i * (sizeV - 1) + j) * 3] = i * sizeV + j;
                    triangles[(i * (sizeV - 1) + j) * 3 + 1] = i * sizeV + j + 1;
                    triangles[(i * (sizeV - 1) + j) * 3 + 2] = (i + 1) * sizeV + j;
                }
            }
        }
        return;
    }

    void BezierSurface() {
        coefficientsN = new float[n + 1];
        coefficientsM = new float[m + 1];
        float u = 0, v = 0, deltaU = 1F / (sizeU - 1), deltaV = 1F / (sizeV -  1);
        Vector3 aux = new Vector3(0, 0, 0);
        k = new Vector3[,]
        {
                { new Vector3(-1,-2,-1), new Vector3(-1,0,0),new Vector3(-1,2,1)},
                { new Vector3(0,0,-1), new Vector3(0,0,0),new Vector3(0,0,1)},
                { new Vector3(1,-2,-1), new Vector3(1,0,0),new Vector3(1,2,1)}
        };
        for (int i = 0; i <= n; i++)
        {
            coefficientsN[i] = (float) combinations(n, i);
        }
        for (int i = 0; i <= m; i++)
        {
            coefficientsM[i] = (float) combinations(m, i);
        }
        initializeSt();
        for (int i = 0; i < sizeU; i++)
        {
            v = 0;
            for (int j = 0; j < sizeV; j++)
            {
                aux = p(u, v);
                S[i * sizeV + j].Set(aux.x, aux.y, aux.z);
                v += deltaV;
            }
            u += deltaU;
        }

        myMeshFilter.mesh.vertices = S;
        myMeshFilter.mesh.triangles = triangles;
        myMeshFilter.mesh.RecalculateBounds();  
     }


    // Parece ser que esta bien
     
    public Vector3 r = new Vector3(0, 0, 0);

    Vector3 p(float u, float v)
    {
        Vector3 aux = new Vector3(0, 0, 0);
        r.Set(0, 0, 0);

        for(int i=0; i<=n;i++)
        { 
            for (int j = 0; j <= m; j++)
            {
                aux.Set(k[i, j].x, k[i, j].y, k[i, j].z);
                // r += B(n,i,u)*B(n,j,v) * k[i,j]
                r += (coefficientsN[i] * Mathf.Pow(u,i) * Mathf.Pow(1-u,n-i)
                    * coefficientsM[j] * Mathf.Pow(v,j) * Mathf.Pow(1-v,m-j)) * aux;
            }
        }
        return r;
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
