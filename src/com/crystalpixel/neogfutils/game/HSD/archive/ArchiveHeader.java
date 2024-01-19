package com.crystalpixel.neogfutils.game.HSD.archive;

public class ArchiveHeader {
    public int file_size;
    public int data_size;
    public int nb_reloc;
    public int nb_public;
    public int nb_extern;
    public byte[] version;
    public int[] pad;

    public ArchiveHeader() {
        this(0,0,0,0,0,new byte[3],new int[4]);
    }

    public ArchiveHeader(int file_size, int data_size, int nb_reloc, int nb_public, int nb_extern, byte[] version,
            int[] pad) {
        this.file_size = file_size;
        this.data_size = data_size;
        this.nb_reloc = nb_reloc;
        this.nb_public = nb_public;
        this.nb_extern = nb_extern;
        this.version = version;
        this.pad = pad;
    }
}
