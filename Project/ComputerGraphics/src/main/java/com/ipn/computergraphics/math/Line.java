package com.ipn.computergraphics.math;

import java.util.ArrayList;
import java.util.List;

public class Line {

    public static List<List<Double>> BresenhamLine3D(Vector3 v1, Vector3 v2) {
        double x1=v1.getX(),y1=v1.getY(),z1=v1.getZ(),x2=v2.getX(),y2=v2.getY(),z2=v2.getZ();
        List<List<Double>> ListOfPoints = new ArrayList<>();
        ListOfPoints.add(List.of(x1, y1, z1));
        double dx = Math.abs(x2 - x1);
        double dy = Math.abs(y2 - y1);
        double dz = Math.abs(z2 - z1);
        double xs;
        double ys;
        double zs;
        if (x2 > x1) {
            xs = 1;
        } else {
            xs = -1;
        }
        if (y2 > y1) {
            ys = 1;
        } else {
            ys = -1;
        }
        if (z2 > z1) {
            zs = 1;
        } else {
            zs = -1;
        }

        // Driving axis is X-axis"
        if (dx >= dy && dx >= dz) {
            double p1 = 2 * dy - dx;
            double p2 = 2 * dz - dx;
            while (x1 != x2) {
                x1 += xs;
                if (p1 >= 0) {
                    y1 += ys;
                    p1 -= 2 * dx;
                }
                if (p2 >= 0) {
                    z1 += zs;
                    p2 -= 2 * dx;
                }
                p1 += 2 * dy;
                p2 += 2 * dz;
                ListOfPoints.add(List.of(x1, y1, z1));
            }

            // Driving axis is Y-axis"
        } else if (dy >= dx && dy >= dz) {
            double p1 = 2 * dx - dy;
            double p2 = 2 * dz - dy;
            while (y1 != y2) {
                y1 += ys;
                if (p1 >= 0) {
                    x1 += xs;
                    p1 -= 2 * dy;
                }
                if (p2 >= 0) {
                    z1 += zs;
                    p2 -= 2 * dy;
                }
                p1 += 2 * dx;
                p2 += 2 * dz;
                ListOfPoints.add(List.of(x1, y1, z1));
            }

            // Driving axis is Z-axis"
        } else {
            double p1 = 2 * dy - dz;
            double p2 = 2 * dx - dz;
            while (z1 != z2) {
                z1 += zs;
                if (p1 >= 0) {
                    y1 += ys;
                    p1 -= 2 * dz;
                }
                if (p2 >= 0) {
                    x1 += xs;
                    p2 -= 2 * dz;
                }
                p1 += 2 * dy;
                p2 += 2 * dx;
                ListOfPoints.add(List.of(x1, y1, z1));
            }
        }
        return ListOfPoints;
    }

    public static List<Vector3> DDALine3D(Vector3 start, Vector3 end) {
        List<Vector3> line = new ArrayList<>();
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        double dz = end.getZ() - start.getZ();
        double maxStep = Math.max(Math.max(Math.abs(dx), Math.abs(dy)), Math.abs(dz));
        double xInc = dx / maxStep;
        double yInc = dy / maxStep;
        double zInc = dz / maxStep;
        double x = start.getX();
        double y = start.getY();
        double z = start.getZ();
        for (int i = 0; i <= maxStep; i++) {
            line.add(new Vector3(x, y, z));
            x += xInc;
            y += yInc;
            z += zInc;
        }
        return line;
    }

}
