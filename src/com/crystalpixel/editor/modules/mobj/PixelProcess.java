package com.crystalpixel.editor.modules.mobj;

public enum PixelProcess {
    COLOR_UPDATE(1 << 0),
    ALPHA_UPDATE(1 << 1),
    DST_ALPHA(1 << 2),
    BEFORE_TEX(1 << 3),
    COMPARE(1 << 4),
    ZUPDATE(1 << 5),
    DITHER(1 << 6);

    private final int value;

    PixelProcess(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

