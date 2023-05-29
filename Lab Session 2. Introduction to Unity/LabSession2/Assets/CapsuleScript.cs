using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CapsuleScript : MonoBehaviour
{
    public float minScale = 1f;
    public float maxScale = 5f;
    public float scaleSpeed = 1f;
    public float rotationSpeed = 1f;

    void Start() { 
    }

    void Update()
    {
        float scale = Mathf.PingPong(Time.time * scaleSpeed, maxScale - minScale) + minScale;
        transform.localScale = new Vector3(scale, scale, scale);

        if (Random.value > 0.5f)
        {
            transform.Rotate(Vector3.up, 45f * rotationSpeed * Time.deltaTime);
        }
        else
        {
            transform.Rotate(Vector3.forward, 45f * rotationSpeed * Time.deltaTime);
        }
    }
}
