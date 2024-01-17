package com.crystalpixel.neogfutils.utils.math;

public class Quaternion {
    private double w, x, y, z;

    public Quaternion() {
        this(1, 0, 0, 0);
    }

    public Quaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quaternion add(Quaternion other) {
        return new Quaternion(w + other.w, x + other.x, y + other.y, z + other.z);
    }

    public Quaternion multiply(Quaternion other) {
        double newW = w * other.w - x * other.x - y * other.y - z * other.z;
        double newX = w * other.x + x * other.w + y * other.z - z * other.y;
        double newY = w * other.y - x * other.z + y * other.w + z * other.x;
        double newZ = w * other.z + x * other.y - y * other.x + z * other.w;

        return new Quaternion(newW, newX, newY, newZ);
    }

    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    public double magnitude() {
        return Math.sqrt(w * w + x * x + y * y + z * z);
    }

    public Quaternion normalize() {
        double mag = magnitude();
        return new Quaternion(w / mag, x / mag, y / mag, z / mag);
    }

    @Override
    public String toString() {
        return "(" + w + ", " + x + "i, " + y + "j, " + z + "k)";
    }
}