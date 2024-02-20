package com.crystalpixel.neogfutils.game.HSD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum RenderMode {
    CONSTANT(1 << 0),
    VERTEX(1 << 1),
    BOTH(CONSTANT.getValue() | VERTEX.getValue()),
    DIFFUSE(1 << 2),
    SPECULAR(1 << 3),
    TEX0(1 << 4),
    TEX1(1 << 5),
    TEX2(1 << 6),
    TEX3(1 << 7),
    TEX4(1 << 8),
    TEX5(1 << 9),
    TEX6(1 << 10),
    TEX7(1 << 11),
    TOON(1 << 12),
    // ALPHA_COMPAT(0 << 13),
    ALPHA_MAT(1 << 13),
    ALPHA_VTX(2 << 13),
    ALPHA_BOTH(3 << 13),
    ZOFST(1 << 24),
    EFFECT(1 << 25),
    SHADOW(1 << 26),
    ZMODE_ALWAYS(1 << 27),
    DF_ALL(1 << 28),
    NO_ZUPDATE(1 << 29),
    XLU(1 << 30),
    USER(1 << 31);

    private final int value;

    RenderMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static RenderMode valueOf(int value) {
        for (RenderMode flag : RenderMode.values()) {
            if (flag.getValue() == value) {
                return flag;
            }
        }
        throw new IllegalArgumentException("Invalid RenderMode value: " + value);
    }

    public boolean contains(RenderMode... flagsToCheck) {
        for (RenderMode flag : flagsToCheck) {
            if ((this.value & flag.getValue()) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean contains(RenderMode[] flags, RenderMode... flag) {
        for (RenderMode f : flags) {
            for (RenderMode singleFlag : flag) {
                if (f == singleFlag) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void add(RenderMode[] flags, RenderMode... flagsToAdd) {
        List<RenderMode> flagsList = new ArrayList<>(Arrays.asList(flags));
        for (RenderMode flag : flagsToAdd) {
            if (!flagsList.contains(flag)) {
                flagsList.add(flag);
            }
        }
        flags = flagsList.toArray(new RenderMode[0]);
    }

    public static void remove(RenderMode[] flags, RenderMode... flagsToRemove) {
        List<RenderMode> flagsList = new ArrayList<>(Arrays.asList(flags));
        for (RenderMode flag : flagsToRemove) {
            flagsList.remove(flag);
        }
        flags = flagsList.toArray(new RenderMode[0]);
    }

    RenderMode remove(RenderMode... flags) {
        int result = this.value;
        for (RenderMode flag : flags) {
            result &= ~flag.getValue();
        }
        return RenderMode.valueOf(result);
    }

    public RenderMode add(RenderMode... flagsToAdd) {
        int result = this.value;
        for (RenderMode flag : flagsToAdd) {
            if ((result & flag.getValue()) == 0) {
                result |= flag.getValue();
            }
        }
        return RenderMode.valueOf(result);
    }
}
