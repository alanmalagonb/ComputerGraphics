package com.ipn.computergraphics.math;

public final class Vector3 {

    public static final Vector3 ZERO = new Vector3(0, 0, 0);
    public static final Vector3 ONE = new Vector3(1, 1, 1);

    public static Vector3 at(double x, double y, double z) {
        // switch for efficiency on typical cases
        // in MC y is rarely 0/1 on selections
        int yTrunc = (int) y;
        switch (yTrunc) {
            case 0:
                if (x == 0 && y == 0 && z == 0) {
                    return ZERO;
                }
                break;
            case 1:
                if (x == 1 && y == 1 && z == 1) {
                    return ONE;
                }
                break;
            default:
                break;
        }
        return new Vector3(x, y, z);
    }

    private final double x;
    private final double y;
    private final double z;
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public Vector3 withX(double x) {
        return Vector3.at(x, y, z);
    }

    public double getY() {
        return y;
    }

    public Vector3 withY(double y) {
        return Vector3.at(x, y, z);
    }

    public double getZ() {
        return z;
    }

    public Vector3 withZ(double z) {
        return Vector3.at(x, y, z);
    }


    public Vector3 getMinimum(Vector3 v2) {
        return new Vector3(
                Math.min(x, v2.x),
                Math.min(y, v2.y),
                Math.min(z, v2.z)
        );
    }

    public Vector3 getMaximum(Vector3 v2) {
        return new Vector3(
                Math.max(x, v2.x),
                Math.max(y, v2.y),
                Math.max(z, v2.z)
        );
    }



    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector3)) {
            return false;
        }

        Vector3 other = (Vector3) obj;
        return other.x == this.x && other.y == this.y && other.z == this.z;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + Double.hashCode(x);
        hash = 31 * hash + Double.hashCode(y);
        hash = 31 * hash + Double.hashCode(z);
        return hash;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}

