using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Arbol : MonoBehaviour
{
    //public Material material;
    private LineRenderer lineRenderer;
    public int lastPoint;
    public float ind = 1;
    public float sl = 0.25f;
    public float x = 310;
    public float y = 450;

    void Start()
    {
        //material = new Material(Shader.Find("Standard"));
        //material.color = Color.green;
        lineRenderer = gameObject.GetComponent<LineRenderer>();
        //lineRenderer.material = material;
        lineRenderer.widthMultiplier = 0.1f;
        lineRenderer.positionCount = 0;
        lastPoint = 0;
        arbol(x, y, 35, 90);
    }

    void arbol(float x0, float y0, float l, float an)
    {
        float x1, y1;
        if (l > ind)
        {
            x1 = x0 - (l * Mathf.Cos(an / 57.29578f)) * sl;
            y1 = y0 - (l * Mathf.Sin(an / 57.29578f)) * sl;

            lineRenderer.positionCount += 2;
            lineRenderer.SetPosition(lastPoint++, new Vector3(x0 * sl , -1 * y0 * sl, 0));
            lineRenderer.SetPosition(lastPoint++, new Vector3(x1 * sl, -1 * y1 * sl, 0));

            arbol(x1, y1, l / 1.72f, an - 57);
            arbol(x1, y1, l - 1.5f, an);
            arbol(x1, y1, l / 1.72f, an + 57);

            return;
        }
    }
}