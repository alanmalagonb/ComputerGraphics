using UnityEngine;
using System.Collections;

// https://docs.unity3d.com/ScriptReference/LineRenderer.SetPosition.html

public class ExampleClass : MonoBehaviour
{
    // Creates a line renderer that follows a Sin() function
    // and animates it.

    public Color c1 = Color.yellow;
    public Color c2 = Color.red;
    public int lengthOfLineRenderer = 20;

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

    (float x, float y) f(float t)
    {
        return (t * t + 1, t);
    }

    public float t_ini = -1, t_fin = 10, t, delta,x,y;
    public Vector3 newPosition = new Vector3(0, 0, 0);

    void Update()
    {
        LineRenderer lineRenderer = GetComponent<LineRenderer>();
        //var t = Time.time;

        //delta = (t_fin - t_ini) / lengthOfLineRenderer;
        delta = (4F - (-1F)) / lengthOfLineRenderer;
        t = -1;
        (x, y) = f(t);
        newPosition.x = x;
        newPosition.y = y;

        lineRenderer.SetPosition(0, newPosition);
                
        for (int i = 1; i < lengthOfLineRenderer; i++)
        {
            t += delta;
            (x, y) = f(t);
            newPosition.x = x;
            newPosition.y = y;
            lineRenderer.SetPosition(i, newPosition);
        }
    }
}