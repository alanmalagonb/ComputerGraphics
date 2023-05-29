using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Mariposa : MonoBehaviour
{
    public Material material;
    private LineRenderer lineRenderer;
    public int lastPoint;
    public float sl = 0.2f;
    public float x = 350;
    public float y = 350;

    void Start()
    {
        //material = new Material(Shader.Find("Standard"));
        //material.color = Color.green;
        lineRenderer = gameObject.GetComponent<LineRenderer>();
        //lineRenderer.material = material;
        lineRenderer.widthMultiplier = 0.1f;
        lineRenderer.positionCount = 0;
        lastPoint = 0;

        mariposa(x, y, 90, 10, 12);
        mariposa(x, y, 90, 160, 12);
    }

    void mariposa(float x0, float y0, float l, float an,float ind)
    {
        float x1, y1;
        if (ind > 0)
        {
            x1 = x0 - (l * Mathf.Cos(an / 57.29578f)) * sl;
            y1 = y0 - (l * Mathf.Sin(an / 57.29578f)) * sl;

            mariposa(x0, y0, l - 1, an - 10, ind - 1);

            lineRenderer.positionCount += 2;
            lineRenderer.SetPosition(lastPoint++, new Vector3(x0 * sl, y0 * sl, 0));
            lineRenderer.SetPosition(lastPoint++, new Vector3(x1 * sl, y1 * sl, 0));

            return;
        }
    }
}