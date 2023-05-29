using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HermiteCurves : MonoBehaviour
{
    public Color c1 = Color.yellow;
    public Color c2 = Color.red;
    public int lengthOfLineRenderer = 1000;
    public Vector3 startPoint = new Vector3(0, 0, 0);
    public Vector3 endPoint = new Vector3(10, 10, 0);
    public Vector3 startTangent = new Vector3(2, 2, 0);
    public Vector3 endTangent = new Vector3(-2, -2, 0);

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

    public Vector3 CalculateHermite(Vector3 p0, Vector3 p1, Vector3 m0, Vector3 m1, float t)
    {
        float t2 = t * t;
        float t3 = t2 * t;
        float h00 = 2 * t3 - 3 * t2 + 1;
        float h10 = t3 - 2 * t2 + t;
        float h01 = -2 * t3 + 3 * t2;
        float h11 = t3 - t2;

        return h00 * p0 + h10 * m0 + h01 * p1 + h11 * m1;
    }

    public Vector3 newPoint = new Vector3(0, 0, 0);

    void Update()
    {
        LineRenderer lineRenderer = GetComponent<LineRenderer>();

        float delta = 1.0f / (lengthOfLineRenderer - 1);

        for (int i = 0; i < lengthOfLineRenderer; i++)
        {
            float t = delta * i;
            newPoint = CalculateHermite(startPoint, endPoint, startTangent, endTangent, t);
            lineRenderer.SetPosition(i, newPoint);
        }
    }
}