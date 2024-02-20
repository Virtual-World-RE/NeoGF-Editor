package com.crystalpixel.neogfutils.game.HSD;

public enum PObjFlags {
    SHAPESET_AVERAGE(1 << 0),
    SHAPESET_ADDITIVE(1 << 1),
    UNKNOWN2(1 << 2),
    ANIM(1 << 3),
    SHAPEANIM(1 << 12),
    ENVELOPE(1 << 13),
    CULLBACK(1 << 14),
    CULLFRONT(1 << 15);

    private final int value;

    PObjFlags(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public boolean contains(PObjFlags... flagsToCheck) {
        for (PObjFlags flag : flagsToCheck) {
            if ((this.value & flag.getValue()) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean contains(PObjFlags[] flags, PObjFlags... flag) {
        for (PObjFlags f : flags) {
            for (PObjFlags singleFlag : flag) {
                if (f == singleFlag) {
                    return true;
                }
            }
        }
        return false;
    }
}
