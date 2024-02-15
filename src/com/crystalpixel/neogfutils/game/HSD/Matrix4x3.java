package com.crystalpixel.neogfutils.game.HSD;

import com.crystalpixel.neogfutils.utils.accesor.Accessor;

public class Matrix4x3 extends Accessor {

    public Matrix4x3() {
        super.trimmedSize = 0x30;
    }

    public float getM11() {
        return _s.getFloat(0x00);
    }

    public void setM11(float value) {
        _s.setFloat(0x00, value);
    }

    public float getM12() {
        return _s.getFloat(0x04);
    }

    public void setM12(float value) {
        _s.setFloat(0x04, value);
    }

    public float getM13() {
        return _s.getFloat(0x08);
    }

    public void setM13(float value) {
        _s.setFloat(0x08, value);
    }

    public float getM14() {
        return _s.getFloat(0x0C);
    }

    public void setM14(float value) {
        _s.setFloat(0x0C, value);
    }

    public float getM21() {
        return _s.getFloat(0x10);
    }

    public void setM21(float value) {
        _s.setFloat(0x10, value);
    }

    public float getM22() {
        return _s.getFloat(0x14);
    }

    public void setM22(float value) {
        _s.setFloat(0x14, value);
    }

    public float getM23() {
        return _s.getFloat(0x18);
    }

    public void setM23(float value) {
        _s.setFloat(0x18, value);
    }

    public float getM24() {
        return _s.getFloat(0x1C);
    }

    public void setM24(float value) {
        _s.setFloat(0x1C, value);
    }

    public float getM31() {
        return _s.getFloat(0x20);
    }

    public void setM31(float value) {
        _s.setFloat(0x20, value);
    }

    public float getM32() {
        return _s.getFloat(0x24);
    }

    public void setM32(float value) {
        _s.setFloat(0x24, value);
    }

    public float getM33() {
        return _s.getFloat(0x28);
    }

    public void setM33(float value) {
        _s.setFloat(0x28, value);
    }

    public float getM34() {
        return _s.getFloat(0x2C);
    }

    public void setM34(float value) {
        _s.setFloat(0x2C, value);
    }

    @Override
    public String toString() {
        return "[" + getM11() + ", " + getM21() + ", " + getM31() + "] [" +
                getM12() + ", " + getM22() + ", " + getM32() + "] [" +
                getM13() + ", " + getM23() + ", " + getM33() + "] [" +
                getM14() + ", " + getM24() + ", " + getM34() + "]";
    }
}
