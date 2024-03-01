package com.crystalpixel.editor.modules.jobj;

public enum JointTrackType {
    A_J_NONE(0),
    A_J_ROTX(1),
    A_J_ROTY(2),
    A_J_ROTZ(3),
    A_J_PATH(4),
    A_J_TRAX(5),
    A_J_TRAY(6),
    A_J_TRAZ(7),
    A_J_SCAX(8),
    A_J_SCAY(9),
    A_J_SCAZ(10),
    A_J_NODE(11),
    A_J_BRANCH(12),
    A_J_SETBYTE0(13),
    A_J_SETBYTE1(14),
    A_J_SETBYTE2(15),
    A_J_SETBYTE3(16),
    A_J_SETBYTE4(17),
    A_J_SETBYTE5(18),
    A_J_SETBYTE6(19),
    A_J_SETBYTE7(20),
    A_J_SETBYTE8(21),
    A_J_SETBYTE9(22),
    A_J_SETFLOAT0(23),
    A_J_SETFLOAT1(24),
    A_J_SETFLOAT2(25),
    A_J_SETFLOAT3(26),
    A_J_SETFLOAT4(27),
    A_J_SETFLOAT5(28),
    A_J_SETFLOAT6(29),
    A_J_SETFLOAT7(30),
    A_J_SETFLOAT8(31),
    A_J_SETFLOAT9(32),
    A_J_PTCL(40);

    private final int value;

    JointTrackType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
