using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraScript : MonoBehaviour
{
    public float speed = 10f;
    private float angle = 0f;
    private Vector3 center = Vector3.zero;

    void Update()
    {
        Vector3 pos = transform.position;
        Vector3 towardsCenter = center - pos;
        towardsCenter.Normalize();
        transform.rotation = Quaternion.LookRotation(towardsCenter);
        transform.RotateAround(center, Vector3.up, speed * Time.deltaTime);
    }
}