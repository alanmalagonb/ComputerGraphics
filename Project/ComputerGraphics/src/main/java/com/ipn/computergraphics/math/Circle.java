package com.ipn.computergraphics.math;

import java.util.ArrayList;
import java.util.List;

public class Circle {
    public static List<Vector2> BresenhamCircle2D(int centerX, int centerY, int radius) {
        List<Vector2> points = new ArrayList<>();
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;
        points.add(new Vector2(centerX + x, centerY + y));
        points.add(new Vector2(centerX - x, centerY + y));
        points.add(new Vector2(centerX + x, centerY - y));
        points.add(new Vector2(centerX - x, centerY - y));
        points.add(new Vector2(centerX + y, centerY + x));
        points.add(new Vector2(centerX - y, centerY + x));
        points.add(new Vector2(centerX + y, centerY - x));
        points.add(new Vector2(centerX - y, centerY - x));
        while (y >= x) {
            x++;
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            points.add(new Vector2(centerX + x, centerY + y));
            points.add(new Vector2(centerX - x, centerY + y));
            points.add(new Vector2(centerX + x, centerY - y));
            points.add(new Vector2(centerX - x, centerY - y));
            points.add(new Vector2(centerX + y, centerY + x));
            points.add(new Vector2(centerX - y, centerY + x));
            points.add(new Vector2(centerX + y, centerY - x));
            points.add(new Vector2(centerX - y, centerY - x));
        }
        return points;
    }

    public static List<Vector2> midPointCircle2D(int x_centre, int y_centre, int r) {
        List<Vector2> points = new ArrayList<>();

        int x = r, y = 0;
        points.add(new Vector2(x + x_centre, y + y_centre));

        if (r > 0) {
            points.add(new Vector2(-x + x_centre, -y + y_centre));
            points.add(new Vector2(y + x_centre,x + y_centre));
            points.add(new Vector2(-y + x_centre, -x + y_centre));
        }

        int P = 1 - r;
        while (x > y) {
            y++;

            if (P <= 0)
                P = P + 2 * y + 1;
            else {
                x--;
                P = P + 2 * y - 2 * x + 1;
            }

            if (x < y)
                break;

            points.add(new Vector2(x + x_centre, y + y_centre));
            points.add(new Vector2(-x + x_centre, y + y_centre));
            points.add(new Vector2(x + x_centre, -y + y_centre));
            points.add(new Vector2(-x + x_centre, -y + y_centre));

            if (x != y) {
                points.add(new Vector2(y + x_centre, x + y_centre));
                points.add(new Vector2(-y + x_centre, x + y_centre));
                points.add(new Vector2(y + x_centre, -x + y_centre));
                points.add(new Vector2(-y + x_centre, -x + y_centre));
            }
        }

        return points;
    }
}
