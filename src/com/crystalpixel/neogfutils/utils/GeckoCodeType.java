package com.crystalpixel.neogfutils.utils;

public class GeckoCodeType {

    public enum MAIN {
        WRITE,
        IF,
        ADDRESS,
        FLOW,
        REGISTER,
        GECKO_IF,
        ASM,
        OTHER,
        END
    }

    public enum WRITE {
        BYTE,
        HALFWORD,
        FULLWORD,
        PATCH,
        SLIDER
    }

    public enum ASM {
        EXECUTE,
        INSERT,
        BRANCH
    }
}
