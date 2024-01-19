package com.crystalpixel.neogfutils.game.HSD.archive;

public class ArchivePublicInfo {
    public int offset;
    public int symbol;

    public ArchivePublicInfo() {
        this(0,0);
    }

    public ArchivePublicInfo(int offset, int symbol) {
        this.offset = offset;
        this.symbol = symbol;
    }
}
