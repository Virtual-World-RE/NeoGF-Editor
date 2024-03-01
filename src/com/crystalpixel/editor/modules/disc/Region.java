package com.crystalpixel.editor.modules.disc;

public enum Region {
    Unknown(' '),
    Australia('U'),
    France('F'),
    Germany('D'),
    Italy('I'),
    Japan('J'),
    Korea('K'),
    PAL('P'),
    Russia('R'),
    Spanish('S'),
    Taiwan('T'),
    USA('E');

    private final char code;

    Region(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static Region fromCode(char code) {
        for (Region region : Region.values()) {
            if (region.getCode() == code) {
                return region;
            }
        }
        return Unknown;
    }
}