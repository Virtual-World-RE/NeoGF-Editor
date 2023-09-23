package com.crystalpixel.neogfutils.battle.entity;

public class Position {

    public static final Position ZERO = new Position(0.0, 0.0, 0.0);

    private double x;
    private double y;
    private double z;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    private int hash = 0;

    public Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distance(double x1, double y1, double z1) {
        double a = getX() - x1;
        double b = getY() - y1;
        double c = getZ() - z1;
        return Math.sqrt(a * a + b * b + c * c);
    }

    public double distance(Position point) {
        return distance(point.getX(), point.getY(), point.getZ());
    }

    public Position add(double x, double y, double z) {
        return new Position(getX() + x, getY() + y, getZ() + z);
    }

    public Position add(Position point) {
        return add(point.getX(), point.getY(), point.getZ());
    }

    public Position subtract(double x, double y, double z) {
        return new Position(getX() - x, getY() - y, getZ() - z);
    }

    public Position subtract(Position point) {
        return subtract(point.getX(), point.getY(), point.getZ());
    }

    public Position multiply(double factor) {
        return new Position(getX() * factor, getY() * factor, getZ() * factor);
    }

    public Position normalize() {
        final double mag = magnitude();

        if (mag == 0.0) {
            return new Position(0.0, 0.0, 0.0);
        }

        return new Position(getX() / mag, getY() / mag, getZ() / mag);
    }

    public Position midpoint(double x, double y, double z) {
        return new Position(x + (getX() - x) / 2.0, y + (getY() - y) / 2.0, z + (getZ() - z) / 2.0);
    }

    public Position midpoint(Position point) {
        return midpoint(point.getX(), point.getY(), point.getZ());
    }

    public double angle(double x, double y, double z) {
        final double ax = getX();
        final double ay = getY();
        final double az = getZ();

        final double delta = (ax * x + ay * y + az * z) / Math.sqrt(
                (ax * ax + ay * ay + az * az) * (x * x + y * y + z * z));

        if (delta > 1.0) {
            return 0.0;
        }
        if (delta < -1.0) {
            return 180.0;
        }

        return Math.toDegrees(Math.acos(delta));
    }

    public double angle(Position point) {
        return angle(point.getX(), point.getY(), point.getZ());
    }

    public double angle(Position p1, Position p2) {
        final double x = getX();
        final double y = getY();
        final double z = getZ();

        final double ax = p1.getX() - x;
        final double ay = p1.getY() - y;
        final double az = p1.getZ() - z;
        final double bx = p2.getX() - x;
        final double by = p2.getY() - y;
        final double bz = p2.getZ() - z;

        final double delta = (ax * bx + ay * by + az * bz) / Math.sqrt(
                (ax * ax + ay * ay + az * az) * (bx * bx + by * by + bz * bz));

        if (delta > 1.0) {
            return 0.0;
        }
        if (delta < -1.0) {
            return 180.0;
        }

        return Math.toDegrees(Math.acos(delta));
    }

    public double magnitude() {
        final double x = getX();
        final double y = getY();
        final double z = getZ();

        return Math.sqrt(x * x + y * y + z * z);
    }

    public double dotProduct(double x, double y, double z) {
        return getX() * x + getY() * y + getZ() * z;
    }

    public double dotProduct(Position vector) {
        return dotProduct(vector.getX(), vector.getY(), vector.getZ());
    }

    public Position crossProduct(double x, double y, double z) {
        final double ax = getX();
        final double ay = getY();
        final double az = getZ();

        return new Position(
                ay * z - az * y,
                az * x - ax * z,
                ax * y - ay * x);
    }

    public Position crossProduct(Position vector) {
        return crossProduct(vector.getX(), vector.getY(), vector.getZ());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Position) {
            Position other = (Position) obj;
            return getX() == other.getX() && getY() == other.getY() && getZ() == other.getZ();
        } else return false;
    }

    @Override
    public int hashCode() {
        if (hash == 0) {
            long bits = 7L;
            bits = 31L * bits + Double.doubleToLongBits(getX());
            bits = 31L * bits + Double.doubleToLongBits(getY());
            bits = 31L * bits + Double.doubleToLongBits(getZ());
            hash = (int) (bits ^ (bits >> 32));
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Position [x = " + getX() + ", y = " + getY() + ", z = " + getZ() + "]";
    }
}

