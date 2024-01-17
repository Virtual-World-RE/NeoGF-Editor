package com.crystalpixel.neogfutils.utils.math.vector;

public class Vect3 {
    private float x, y, z;

    public Vect3() {
        this(0,0,0);
    }

    public Vect3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}

