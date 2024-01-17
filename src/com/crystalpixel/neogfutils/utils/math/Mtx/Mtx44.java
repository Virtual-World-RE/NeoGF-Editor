package com.crystalpixel.neogfutils.utils.math.Mtx;

public class Mtx44 {
    private float[][] elements;

    public Mtx44() {
        elements = new float[4][4];
    }

    public float getElement(int row, int col) {
        return elements[row][col];
    }

    public void setElement(int row, int col, float value) {
        elements[row][col] = value;
    }
}
