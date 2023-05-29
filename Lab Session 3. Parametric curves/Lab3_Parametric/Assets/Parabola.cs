using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Parabola : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {

    }

    (float x, float y) parabola(float t)
    {
        float x = t;
        float y = t * t;
        return (x, y);
    }

    public Vector3 newPosition = new Vector3(0, 0, 0);
    public float t = 0;
    public float direction = 1.0f;

    // Update is called once per frame
    void Update()
    {
        float x, y;
        t += Time.deltaTime * 5 * direction;
        (x, y) = parabola(t);
        newPosition.x = x;
        newPosition.y = y;
        transform.position = newPosition;

        // Change direction when reaching the max points
        if (newPosition.x >= 5.0f || newPosition.x <= -5.0f)
        {
            direction *= -1.0f;
        }
    }
}
