package com.crystalpixel.editor.modules.disc;

public enum DiscType {
    Unknown(' '),
    Revolution('R'),
    Wii('S'),
    Gamecube('G'),
    Utility('U'),
    GamecubeDemo('D'),
    GamecubePromotional('P'),
    Diagnostic('0'),
    Diagnostic2('1'),
    WiiBackup('4'),
    WiiFitChanInstaller('_');

    private final char code;

    DiscType(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static DiscType fromCode(char code) {
        for (DiscType type : DiscType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return Unknown;
    }
}