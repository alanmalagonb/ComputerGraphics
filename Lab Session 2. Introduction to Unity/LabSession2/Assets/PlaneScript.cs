using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlaneScript : MonoBehaviour
{
    public float speed = 1.0f;      // velocidad de movimiento
    public float amplitude = 1.0f;  // amplitud de la onda

    private float startTime;        // tiempo de inicio del movimiento
    private Vector3 startPos;       // posición de inicio del movimiento

    void Start()
    {
        // guarda la posición de inicio y tiempo de inicio
        startTime = Time.time;
        startPos = transform.position;
    }

    void Update()
    {
        // calcula la nueva posición del objeto
        float time = (Time.time - startTime) % (2 * Mathf.PI);  // tiempo desde el inicio del movimiento
        float x = time * speed;                                 // componente x de la posición
        float y = amplitude * Mathf.Sin(time);                  // componente y de la posición
        Vector3 newPos = startPos + new Vector3(x, y, 0.0f);     // nueva posición del objeto

        // calcula la rotación del objeto
        Vector3 direction = new Vector3(speed, amplitude * Mathf.Cos(time), 0.0f).normalized;
        transform.rotation = Quaternion.LookRotation(direction, Vector3.up);

        // mueve el objeto a la nueva posición
        transform.position = newPos;
    }
}
