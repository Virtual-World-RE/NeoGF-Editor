package com.crystalpixel.neogfutils.game.HSD.archive;

public class Archive {
    public ArchiveHeader header;
    public byte[] data;
    public ArchiveRelocationInfo[] reloc_info;
    public ArchivePublicInfo[] public_info;
    public ArchiveExternInfo[] extern_info;
    public char[] symbols;
    public Archive next;
    public char[] name;
    public int flags;
    public Object top_ptr;

    public Archive() {
        this(new ArchiveHeader(), new byte[0], new ArchiveRelocationInfo[0], new ArchivePublicInfo[0], new ArchiveExternInfo[0], new char[0], null, new char[0], 0 , null);
    }

    public Archive(ArchiveHeader header, byte[] data, ArchiveRelocationInfo[] reloc_info,
            ArchivePublicInfo[] public_info, ArchiveExternInfo[] extern_info, char[] symbols, Archive next, char[] name,
            int flags, Object top_ptr) {
        this.header = header;
        this.data = data;
        this.reloc_info = reloc_info;
        this.public_info = public_info;
        this.extern_info = extern_info;
        this.symbols = symbols;
        this.next = next;
        this.name = name;
        this.flags = flags;
        this.top_ptr = top_ptr;
    }
}
