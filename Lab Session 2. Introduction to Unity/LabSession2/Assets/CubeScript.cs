using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CubeScript : MonoBehaviour
{
    public float speed = 20.0f; // Speed of cube movement
    public float distance = 10.0f; // Distance between each vertex of the square

    private Vector3[] vertices; // Array of vertices of the square
    private int currentVertex = 0; // Current vertex index that the cube is moving towards

    void Start()
    {
        // Set up an array of vertices of the square
        vertices = new Vector3[4];
        vertices[0] = new Vector3(-5.0f, 0.0f, -5.0f);
        vertices[1] = new Vector3(5.0f, 0.0f, -5.0f);
        vertices[2] = new Vector3(5.0f, 0.0f, 5.0f);
        vertices[3] = new Vector3(-5.0f, 0.0f, 5.0f);
    }

    void Update()
    {
        // Get the position of the current vertex that the cube is moving towards
        Vector3 targetPosition = vertices[currentVertex];

        // Move the cube towards the target position
        transform.position = Vector3.MoveTowards(transform.position, targetPosition, speed * Time.deltaTime);

        // If the cube reaches the target position, move to the next vertex
        if (transform.position == targetPosition)
        {
            currentVertex = (currentVertex + 1) % vertices.Length;
        }

        // Rotate the cube as it moves
        transform.Rotate(new Vector3(0, 1, 0), 50 * Time.deltaTime);
    }
}