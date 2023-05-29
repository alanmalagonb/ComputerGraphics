using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Oso : MonoBehaviour
{
    //public Material material;
    private LineRenderer lineRenderer;
    public int lastPoint;
    public float sl = 0.25f;
    public float x = 300;
    public float y = 150;

    void Start()
    {
        //material = new Material(Shader.Find("Standard"));
        //material.color = Color.green;
        lineRenderer = gameObject.GetComponent<LineRenderer>();
        //lineRenderer.material = material;
        lineRenderer.widthMultiplier = 0.1f;
        lineRenderer.positionCount = 0;
        lastPoint = 0;
        oso(x, y, 63, -72, 9);
    }

    void oso(float x0, float y0, float l, float an,float ind)
    {
        float x1, y1;
        if (ind > 0)
        {
            x1 = x0 - (l * Mathf.Cos(an / 57.29578f)) * sl;
            y1 = y0 - (l * Mathf.Sin(an / 57.29578f)) * sl;

            lineRenderer.positionCount += 2;
            lineRenderer.SetPosition(lastPoint++, new Vector3(x0 * sl, -1 * y0 * sl, 0));
            lineRenderer.SetPosition(lastPoint++, new Vector3(x1 * sl, -1 * y1 * sl, 0));

            oso(x1, y1, l / 1.2f, an + 51, ind - 1);
            oso(x1, y1, l / 1.55f, an + 72, ind - 1);
            oso(x1, y1, l / 1.8f, an + 144, ind - 1);

            return;
        }
    }
}