package com.crystalpixel.neogfutils.utils.math.Mtx;

public class MtxPtr {
    private float[] array;

    public MtxPtr() {
        array = new float[4];
    }

    public float getElement(int index) {
        return array[index];
    }

    public void setElement(int index, float value) {
        array[index] = value;
    }
}

