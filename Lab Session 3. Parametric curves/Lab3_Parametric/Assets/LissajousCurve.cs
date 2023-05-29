using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LissajousCurve : MonoBehaviour
{
    public Color c1 = Color.yellow;
    public Color c2 = Color.red;
    public int lengthOfLineRenderer = 1000;
    public float tIni = 0.0f;
    public float tFin = 2 * Mathf.PI;
    public float kx = 3.0f;
    public float ky = 2.0f;

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

    (float x, float y) lissajous(float t)
    {
        float xCoord = Mathf.Cos(kx * t);
        float yCoord = Mathf.Sin(ky * t);
        return (xCoord, yCoord);
    }

    public Vector3 newPosition = new Vector3(0, 0, 0);

    void Update()
    {
        LineRenderer lineRenderer = GetComponent<LineRenderer>();

        float delta = (tFin - tIni) / (lengthOfLineRenderer - 1);
        float t = tIni, x, y;

        for (int i = 0; i < lengthOfLineRenderer; i++)
        {
            (x, y) = lissajous(t);
            newPosition.x = x;
            newPosition.y = y;
            lineRenderer.SetPosition(i, newPosition);
            t += delta;
        }
    }
}