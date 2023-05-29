using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RhodoneaCurves : MonoBehaviour
{
    public int n = 5;  // number of petals
    public float a = 1.0f;  // radius of the petal
    public float stepSize = 0.005f;  // step size between each point on the curve

    void Start()
    {
    }

    (float x, float y) rhodonea(float t)
    {
        float cosT = Mathf.Cos(t);
        float sinT = Mathf.Sin(t);

        float xCoord = a * Mathf.Cos(n * t) * cosT;
        float yCoord = a * Mathf.Cos(n * t) * sinT;

        return (xCoord, yCoord);
    }

    public Vector3 newPosition = new Vector3(0, 0, 0);
    public float t;

    void Update()
    {
        float x, y;
        t += stepSize;
        (x, y) = rhodonea(t);
        newPosition.x = x - 7;
        newPosition.y = y;
        transform.position = newPosition;
    }
}