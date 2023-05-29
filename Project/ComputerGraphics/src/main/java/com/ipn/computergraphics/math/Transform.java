package com.ipn.computergraphics.math;

public class Transform {

    private double[][] matrix = {{0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0}};

    public Transform(){}

    public void scaling(Vector3 v){
        double sx = v.getX();
        double sy = v.getY();
        double sz = v.getZ();
        double scalingMatrix[][] = {
                {sx, 0, 0, 0},
                {0, sy, 0, 0},
                {0, 0, sz, 0},
                {0, 0, 0, 1}
        };
        matrix = scalingMatrix;
    }

    public void translation(Vector3 v){
        double tx = v.getX();
        double ty = v.getY();
        double tz = v.getZ();
        double translationMatrix[][] = {
                {1, 0, 0, tx},
                {0, 1, 0, ty},
                {0, 0, 1, tz},
                {0, 0, 0, 1}
        };
        matrix = translationMatrix;
    }

    public void rotation(double angle,String axis){
        double[][] rotationMatrix = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1}
        };
        double angleRads = Math.toRadians(angle);

        switch (axis){
            case "x":
                rotationMatrix[0][0] = 1;
                rotationMatrix[1][1] = Math.cos(angleRads);
                rotationMatrix[1][2] = -Math.sin(angleRads);
                rotationMatrix[2][1] = Math.sin(angleRads);
                rotationMatrix[2][2] = Math.cos(angleRads);
                break;
            case "y":
                rotationMatrix[0][0] = Math.cos(angleRads);
                rotationMatrix[0][2] = Math.sin(angleRads);
                rotationMatrix[1][1] = 1;
                rotationMatrix[2][0] = -Math.sin(angleRads);
                rotationMatrix[2][2] = Math.cos(angleRads);
                break;
            case "z":
                rotationMatrix[0][0] = Math.cos(angleRads);
                rotationMatrix[0][1] = -Math.sin(angleRads);
                rotationMatrix[1][0] = Math.sin(angleRads);
                rotationMatrix[1][1] = Math.cos(angleRads);
                rotationMatrix[2][2] = 1;
                break;
            default:  break;
        }
        matrix = rotationMatrix;
    }

    public Vector3 multiply(Vector3 vector){
        double x = matrix[0][0] * vector.getX() + matrix[0][1] * vector.getY() + matrix[0][2] * vector.getZ() + matrix[0][3] * 1;
        double y = matrix[1][0] * vector.getX() + matrix[1][1] * vector.getY() + matrix[1][2] * vector.getZ() + matrix[1][3] * 1;
        double z = matrix[2][0] * vector.getX() + matrix[2][1] * vector.getY() + matrix[2][2] * vector.getZ() + matrix[2][3] * 1;

        return new Vector3(x,y,z);
    }
}
