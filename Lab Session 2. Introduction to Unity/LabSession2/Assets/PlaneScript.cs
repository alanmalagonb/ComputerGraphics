using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlaneScript : MonoBehaviour
{
    public float speed = 1.0f;      // velocidad de movimiento
    public float amplitude = 1.0f;  // amplitud de la onda

    private float startTime;        // tiempo de inicio del movimiento
    private Vector3 startPos;       // posici�n de inicio del movimiento

    void Start()
    {
        // guarda la posici�n de inicio y tiempo de inicio
        startTime = Time.time;
        startPos = transform.position;
    }

    void Update()
    {
        // calcula la nueva posici�n del objeto
        float time = (Time.time - startTime) % (2 * Mathf.PI);  // tiempo desde el inicio del movimiento
        float x = time * speed;                                 // componente x de la posici�n
        float y = amplitude * Mathf.Sin(time);                  // componente y de la posici�n
        Vector3 newPos = startPos + new Vector3(x, y, 0.0f);     // nueva posici�n del objeto

        // calcula la rotaci�n del objeto
        Vector3 direction = new Vector3(speed, amplitude * Mathf.Cos(time), 0.0f).normalized;
        transform.rotation = Quaternion.LookRotation(direction, Vector3.up);

        // mueve el objeto a la nueva posici�n
        transform.position = newPos;
    }
}
