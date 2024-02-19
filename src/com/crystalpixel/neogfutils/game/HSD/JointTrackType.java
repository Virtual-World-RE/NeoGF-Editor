package com.crystalpixel.neogfutils.game.HSD;

public enum JointTrackType {
    HSD_A_J_NONE(0),
    HSD_A_J_ROTX(1),
    HSD_A_J_ROTY(2),
    HSD_A_J_ROTZ(3),
    HSD_A_J_PATH(4),
    HSD_A_J_TRAX(5),
    HSD_A_J_TRAY(6),
    HSD_A_J_TRAZ(7),
    HSD_A_J_SCAX(8),
    HSD_A_J_SCAY(9),
    HSD_A_J_SCAZ(10),
    HSD_A_J_NODE(11),
    HSD_A_J_BRANCH(12),
    HSD_A_J_SETBYTE0(13),
    HSD_A_J_SETBYTE1(14),
    HSD_A_J_SETBYTE2(15),
    HSD_A_J_SETBYTE3(16),
    HSD_A_J_SETBYTE4(17),
    HSD_A_J_SETBYTE5(18),
    HSD_A_J_SETBYTE6(19),
    HSD_A_J_SETBYTE7(20),
    HSD_A_J_SETBYTE8(21),
    HSD_A_J_SETBYTE9(22),
    HSD_A_J_SETFLOAT0(23),
    HSD_A_J_SETFLOAT1(24),
    HSD_A_J_SETFLOAT2(25),
    HSD_A_J_SETFLOAT3(26),
    HSD_A_J_SETFLOAT4(27),
    HSD_A_J_SETFLOAT5(28),
    HSD_A_J_SETFLOAT6(29),
    HSD_A_J_SETFLOAT7(30),
    HSD_A_J_SETFLOAT8(31),
    HSD_A_J_SETFLOAT9(32),
    HSD_A_J_PTCL(40);

    private final int value;

    JointTrackType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
