using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CylinderScript : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    public Vector3 origin = new Vector3(0, 0, 0);
    public Vector3 myAxis = new Vector3 (1,1,0);

    // Update is called once per frame
    void Update()
    {
        transform.RotateAround(origin, myAxis, 180 * Time.deltaTime);
        //transform.Rotate(Vector3.up,180*Time.deltaTime);
    }
}
