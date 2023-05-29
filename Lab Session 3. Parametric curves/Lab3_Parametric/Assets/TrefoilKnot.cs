using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TrefoilKnot : MonoBehaviour
{
    public float a = 1.0f;
    public float b = 2.0f;
    public float c = 3.0f;

    void Start()
    {
    }

    (float x, float y, float z) trefoil(float t)
    {
        float xCoord = (a + b * Mathf.Cos(c * t)) * Mathf.Cos(t);
        float yCoord = (a + b * Mathf.Cos(c * t)) * Mathf.Sin(t);
        float zCoord = b * Mathf.Sin(c * t);

        return (xCoord, yCoord, zCoord);
    }

    public Vector3 newPosition = new Vector3(0, 0, 0);
    public float t;

    void Update()
    {
        float x, y, z;
        t += Time.deltaTime * 5;
        (x, y, z) = trefoil(t);
        newPosition.x = x - 7;
        newPosition.y = y;
        newPosition.z = z;
        transform.position = newPosition;
    }
}