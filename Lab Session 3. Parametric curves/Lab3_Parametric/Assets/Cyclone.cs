using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Cyclone : MonoBehaviour
{
    private float timer;
    private bool canRestart;

    // Start is called before the first frame update
    void Start()
    {
        timer = 0.0f;
        canRestart = false;
    }

    (float x, float y) cycloid(float t)
    {
        return (0.5F * t - 0.8F * Mathf.Sin(t), 0.5F - 0.8F * Mathf.Cos(t));
    }

    public Vector3 newPosition = new Vector3(0, 0, 0);
    public float t;

    // Update is called once per frame
    void Update()
    {
        float x, y;
        t += Time.deltaTime * 5;//*0.01F
        (x, y) = cycloid(t);
        newPosition.x = x - 7;
        newPosition.y = y;
        transform.position = newPosition;

        if (canRestart)
        {
            timer += Time.deltaTime;

            if (timer >= 10.0f)
            {
                t = 0.0f;
                timer = 0.0f;
                canRestart = false;
            }
        }
        else if (t >= 6.28f)
        {
            canRestart = true;
        }
    }
}
