using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Rio : MonoBehaviour
{
    //public Material material;
    private LineRenderer lineRenderer;
    public int lastPoint;
    public float sl = 1f;
    public float x = 340;
    public float y = 250;

    void Start()
    {
        //material = new Material(Shader.Find("Standard"));
        //material.color = Color.green;
        lineRenderer = gameObject.GetComponent<LineRenderer>();
        //lineRenderer.material = material;
        lineRenderer.widthMultiplier = 0.1f;
        lineRenderer.positionCount = 0;
        lastPoint = 0;
        rio(x, y, 83, -31, 9);
    }

    void rio(float x0, float y0, float l, float an, float ind)
    {
        float x1, y1;
        if (ind > 0)
        {
            x1 = x0 - (l * Mathf.Cos(an / 57.29578f)) * sl;
            y1 = y0 - (l * Mathf.Sin(an / 57.29578f)) * sl;

            lineRenderer.positionCount += 2;
            lineRenderer.SetPosition(lastPoint++, new Vector3(x0 * sl, -1 * y0 * sl, 0));
            lineRenderer.SetPosition(lastPoint++, new Vector3(x1 * sl, -1 * y1 * sl, 0));

            rio(x1, y1, l / 1.2f, an + 6, ind - 1);
            rio(x1, y1, l / 1.55f, an + 172, ind - 1);
            rio(x1, y1, l / 1.8f, an + 186, ind - 1);
        }
    }
}