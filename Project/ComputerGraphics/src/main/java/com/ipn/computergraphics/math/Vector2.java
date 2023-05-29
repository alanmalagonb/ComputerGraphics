package com.ipn.computergraphics.math;

public final class Vector2 {

    public static final Vector2 ZERO = new Vector2(0, 0);
    public static final Vector2 ONE = new Vector2(1, 1);

    public static Vector2 at(double x, double y) {
        // switch for efficiency on typical cases
        // in MC y is rarely 0/1 on selections
        int yTrunc = (int) y;
        switch (yTrunc) {
            case 0:
                if (x == 0 && y == 0) {
                    return ZERO;
                }
                break;
            case 1:
                if (x == 1 && y == 1) {
                    return ONE;
                }
                break;
            default:
                break;
        }
        return new Vector2(x, y);
    }

    private final double x;
    private final double y;
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public Vector2 withX(double x) {
        return Vector2.at(x, y);
    }

    public double getY() {
        return y;
    }

    public Vector2 withY(double y) {
        return Vector2.at(x, y);
    }



    public Vector2 getMinimum(Vector2 v2) {
        return new Vector2(
                Math.min(x, v2.x),
                Math.min(y, v2.y)
        );
    }

    public Vector2 getMaximum(Vector2 v2) {
        return new Vector2(
                Math.max(x, v2.x),
                Math.max(y, v2.y)
        );
    }



    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector2)) {
            return false;
        }

        Vector2 other = (Vector2) obj;
        return other.x == this.x && other.y == this.y;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + Double.hashCode(x);
        hash = 31 * hash + Double.hashCode(y);
        return hash;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}


