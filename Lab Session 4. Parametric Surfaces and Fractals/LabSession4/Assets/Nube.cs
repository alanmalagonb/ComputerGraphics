using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Nube : MonoBehaviour
{
    //public Material material;
    private LineRenderer lineRenderer;
    public int lastPoint;
    public float sl = 1;
    public float x = 280;
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
        nube(x, y, 55, 180, 9);
    }

    void nube(float x0, float y0, float l, float an, float ind)
    {
        float x1, y1;
        if (ind > 0)
        {
            x1 = x0 - (l * Mathf.Cos(an / 57.29578f)) * sl;
            y1 = y0 - (l * Mathf.Sin(an / 57.29578f)) * sl;

            lineRenderer.positionCount += 2;
            lineRenderer.SetPosition(lastPoint++, new Vector3(x0 * sl, -1 * y0 * sl, 0));
            lineRenderer.SetPosition(lastPoint++, new Vector3(x1 * sl, -1 * y1 * sl, 0));

            nube(x0, y0, l / 1.25f, an + 30, ind - 1);
            nube(x1, y1, l / 1.3f, an + 50, ind - 1);
            nube(x1, y1, l / 1.4f, an + 100, ind - 1);

            return;
        }
    }
}