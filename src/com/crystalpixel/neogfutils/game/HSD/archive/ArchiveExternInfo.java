package com.crystalpixel.neogfutils.game.HSD.archive;

public class ArchiveExternInfo {
    public int offset;
    public int symbol;

    public ArchiveExternInfo() {
        this(0,0);
    }

    public ArchiveExternInfo(int offset, int symbol) {
        this.offset = offset;
        this.symbol = symbol;
    }
}
