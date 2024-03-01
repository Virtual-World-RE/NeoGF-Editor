package com.crystalpixel.editor.modules.jobj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum JObjFlags {
    NULL(0x00000000),
    SKELETON(0x00000001),
    SKELETON_ROOT(0x00000002),
    ENVELOPE_MODEL(0x00000004),
    CLASSICAL_SCALING(0x00000008),
    HIDDEN(0x00000010),
    PTCL(0x00000020),
    MTX_DIRTY(0x00000040),
    LIGHTING(0x00000080),
    TEXGEN(0x00000100),
    BILLBOARD(0x00000200),
    VBILLBOARD(0x00000400),
    HBILLBOARD(0x00000600),
    RBILLBOARD(0x00000800),
    INSTANCE(0x00001000),
    PBILLBOARD(0x00002000),
    SPLINE(0x00004000),
    FLIP_IK(0x00008000),
    SPECULAR(0x00010000),
    USE_QUATERNION(0x00020000),
    OPA(0x00040000),
    XLU(0x00080000),
    TEXEDGE(0x00100000),
    JOINT1(0x00200000),
    JOINT2(0x00400000),
    EFFECTOR(0x00600000),
    USER_DEFINED_MTX(0x00800000),
    MTX_INDEPEND_PARENT(0x01000000),
    MTX_INDEPEND_SRT(0x02000000),
    MTX_SCALE_COMPENSATE(0x04000000),
    ROOT_OPA(0x10000000),
    ROOT_XLU(0x20000000),
    ROOT_TEXEDGE(0x40000000);

    private final int value;

    JObjFlags(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static JObjFlags valueOf(int value) {
        for (JObjFlags flag : JObjFlags.values()) {
            if (flag.getValue() == value) {
                return flag;
            }
        }
        throw new IllegalArgumentException("Invalid JObjFlags value: " + value);
    }

    public boolean contains(JObjFlags... flagsToCheck) {
        for (JObjFlags flag : flagsToCheck) {
            if ((this.value & flag.getValue()) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean contains(JObjFlags[] flags, JObjFlags... flag) {
        for (JObjFlags f : flags) {
            for (JObjFlags singleFlag : flag) {
                if (f == singleFlag) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void add(JObjFlags[] flags, JObjFlags... flagsToAdd) {
        List<JObjFlags> flagsList = new ArrayList<>(Arrays.asList(flags));
        for (JObjFlags flag : flagsToAdd) {
            if (!flagsList.contains(flag)) {
                flagsList.add(flag);
            }
        }
        flags = flagsList.toArray(new JObjFlags[0]);
    }    

    public static void remove(JObjFlags[] flags, JObjFlags... flagsToRemove) {
        List<JObjFlags> flagsList = new ArrayList<>(Arrays.asList(flags));
        for (JObjFlags flag : flagsToRemove) {
            flagsList.remove(flag);
        }
        flags = flagsList.toArray(new JObjFlags[0]);
    }

    JObjFlags remove(JObjFlags... flags) {
        int result = this.value;
        for (JObjFlags flag : flags) {
            result &= ~flag.getValue();
        }
        return JObjFlags.valueOf(result);
    }

    public JObjFlags add(JObjFlags... flagsToAdd) {
        int result = this.value;
        for (JObjFlags flag : flagsToAdd) {
            if ((result & flag.getValue()) == 0) {
                result |= flag.getValue();
            }
        }
        return JObjFlags.valueOf(result);
    }
    
}
