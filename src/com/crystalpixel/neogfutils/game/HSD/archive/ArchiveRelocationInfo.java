package com.crystalpixel.neogfutils.game.HSD.archive;

public class ArchiveRelocationInfo {
    public int offset;

    public ArchiveRelocationInfo() {
        this(0);
    }

    public ArchiveRelocationInfo(int offset) {
        this.offset = offset;
    }
}
