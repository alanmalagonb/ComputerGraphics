using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Astroid : MonoBehaviour
{
    void Start()
    {
    }

    (float x, float y) astroid(float t)
    {
        float xCoord = Mathf.Pow(Mathf.Cos(t), 3);
        float yCoord = Mathf.Pow(Mathf.Sin(t), 3);

        return (xCoord, yCoord);
    }

    public Vector3 newPosition = new Vector3(0, 0, 0);
    public float t;

    void Update()
    {
        float x, y;
        t += Time.deltaTime * 5;
        (x, y) = astroid(t);
        newPosition.x = x - 7;
        newPosition.y = y;
        transform.position = newPosition;
    }
}