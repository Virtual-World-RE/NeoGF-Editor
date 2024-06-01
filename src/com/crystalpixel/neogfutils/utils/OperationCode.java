package com.crystalpixel.neogfutils.utils;

public enum OperationCode {

    ADD_IMMEDIATE(14),
    ADD_IMMEDIATE_SHIFTED(15),
    NOP(24),
    BRANCH(18);

    private int id;

    OperationCode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // Returns the Operation Code for the instruction provided.
    public static OperationCode getOpCode(int instruction) {
        int id = instruction >> 26;
        for (OperationCode opCode : values()) {
            if (opCode.id == id) return opCode;
        }
        return null;
    }
}
