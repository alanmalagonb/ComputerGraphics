package com.ipn.computergraphics.math;

import java.util.ArrayList;
import java.util.List;

public class Curve {

    public static List<Vector3> hermiteCurve(Vector3 startPoint, Vector3 endPoint, Vector3 startTangent, Vector3 endTangent, int numPoints) {
        List<Vector3> points = new ArrayList<>();

        for (int i = 0; i < numPoints; i++) {
            double t = (double)i / (double)(numPoints - 1);
            double t2 = t * t;
            double t3 = t2 * t;

            double factor1 = 2 * t3 - 3 * t2 + 1;
            double factor2 = -2 * t3 + 3 * t2;
            double factor3 = t3 - 2 * t2 + t;
            double factor4 = t3 - t2;

            double x = startPoint.getX() * factor1 + endPoint.getX() * factor2 + startTangent.getX() * factor3 + endTangent.getX() * factor4;
            double y = startPoint.getY() * factor1 + endPoint.getY() * factor2 + startTangent.getY() * factor3 + endTangent.getY() * factor4;
            double z = startPoint.getZ() * factor1 + endPoint.getZ() * factor2 + startTangent.getZ() * factor3 + endTangent.getZ() * factor4;

            Vector3 point = new Vector3(x, y, z);

            points.add(point);
        }

        return points;
    }

}
