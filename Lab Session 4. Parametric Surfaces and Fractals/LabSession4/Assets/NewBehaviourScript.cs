//The equation of Nature developed by  
//F. Galindo Soria http://fgalindosoria.com  
//Code written by Dr. Jorge L. Rosas-Trigueros 
//8may23 
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NewBehaviourScript : MonoBehaviour
{
    public Material material;
    public LineRenderer lineRenderer;
    public int lastPoint;

    // Start is called before the first frame update 
    void Start()
    {
        material = new Material(Shader.Find("Standard"));
        material.color = Color.white;
        lineRenderer = gameObject.AddComponent<LineRenderer>();
        lineRenderer.material = material;
        lineRenderer.widthMultiplier = 0.1f;
        lineRenderer.positionCount = 0;
        lastPoint = 0;

        //1 
        ec(0, 0, 70, 90, 0.05F, 57, 1);

        //2 
        // ec(0, 0, 0.05F, 0, 1.1F, 37, 50); 

        //3 
        //        ec(0, 0, 0.05F, 30, 0.05F, 50, 100); 
    }

    void ec(float x, float y, float l, float a, float sl, float da, float n)
    {
        float x2, y2, ar, cos, sin, xrot, yrot;

        if (n < 0) return;


        x2 = x + l;
        y2 = y;
        ar = a * Mathf.Deg2Rad;
        cos = Mathf.Cos(ar);
        sin = Mathf.Sin(ar);
        xrot = (x2 - x) * sin + (y2 - y) * cos;
        yrot = (x2 - x) * cos - (y2 - y) * sin;

        x2 = xrot + x;
        y2 = yrot + y;

        lineRenderer.positionCount += 2;

        lineRenderer.SetPosition(lastPoint++, new Vector3(x, y, 0));
        lineRenderer.SetPosition(lastPoint++, new Vector3(x2, y2, 0));

        //1 
        ec(x2, y2, l * sl, a - da, sl, da, n - 1);
        ec(x2, y2, l * sl, a + da, sl, da, n - 1);
        ec(x2, y2, l * sl, a, sl, da, n - 1);

        /*    //2 
        ec(x, y, l * sl, a - da, sl, da, n - 1); */

        //3 
        //ec(x2, y2, l + sl, a + da, sl, da, n - 1); 

        lineRenderer.positionCount++;
        lineRenderer.SetPosition(lastPoint++, new Vector3(x, y, 0));

        return;
    }

    // Update is called once per frame 

    void Update()
    {
    }
}