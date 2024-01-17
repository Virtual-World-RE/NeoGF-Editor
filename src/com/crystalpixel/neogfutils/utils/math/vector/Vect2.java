package com.crystalpixel.neogfutils.utils.math.vector;

public class Vect2 {
    public float x, y;

    public Vect2() {
        this(0,0);
    }

    public Vect2(float x, float y) {
        this.x = x;
        this.y = y;
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
}
