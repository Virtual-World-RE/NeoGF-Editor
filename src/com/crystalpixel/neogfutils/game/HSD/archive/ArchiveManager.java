package com.crystalpixel.neogfutils.game.HSD.archive;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

import com.crystalpixel.neogfutils.utils.Utils;

public class ArchiveManager {

    public static int parseArchive(Archive archive, File file, int fileSize) throws IOException {
        int offset = 0;

        if (archive == null) {
            return -1;
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");

        ByteBuffer buffer = Utils.seekRaf(raf, 0x100, new byte[24]);
        archive.header = new ArchiveHeader(buffer.getInt(0x0), buffer.getInt(0x4), buffer.getInt(0x8), buffer.getInt(0xC), 
                                           buffer.getInt(0x10), getVersion(buffer.get(0x14)), getPad(buffer.getInt(0x18)));

        if (archive.header.file_size != fileSize) {
            System.out.printf("ArchiveParse: byte-order mismatch! Please check data format %x %x\n",
                    archive.header.file_size, fileSize);
            return -1;
        }

        offset = 24;

        if (archive.header.data_size != 0) {
            archive.data = new byte[archive.header.data_size];
            raf.read(archive.data);
            offset += archive.header.data_size;
        }

        if (archive.header.nb_reloc != 0) {
            archive.reloc_info = new ArchiveRelocationInfo[archive.header.nb_reloc];
            for (int i = 0; i < archive.header.nb_reloc; i++) {
                archive.reloc_info[i] = new ArchiveRelocationInfo();
                archive.reloc_info[i].offset = buffer.getInt(offset);
                offset += 4;
            }
        }

        if (archive.header.nb_public != 0) {
            archive.public_info = new ArchivePublicInfo[archive.header.nb_public];
            for (int i = 0; i < archive.header.nb_public; i++) {
                archive.public_info[i] = new ArchivePublicInfo();
                archive.public_info[i].offset = buffer.getInt(offset);
                archive.public_info[i].symbol = buffer.getInt(offset + 4);
                offset += 8;
            }
        }

        if (archive.header.nb_extern != 0) {
            archive.extern_info = new ArchiveExternInfo[archive.header.nb_extern];
            for (int i = 0; i < archive.header.nb_extern; i++) {
                archive.extern_info[i] = new ArchiveExternInfo();
                archive.extern_info[i].offset = buffer.getInt(offset);
                archive.extern_info[i].symbol = buffer.getInt(offset + 4);
                offset += 8;
            }
        }

        if (offset < archive.header.file_size) {
            int symbolsSize = archive.header.file_size - offset;
            archive.symbols = new char[symbolsSize];
            for (int i = 0; i < symbolsSize; i++) {
                archive.symbols[i] = (char) (raf.readByte() & 0xFF);
            }
        }

        archive.top_ptr = buffer.array();
        locate(archive);

        return 0;
    }

    public static void locate(Archive archive) {
        for (int i = 0; i < archive.header.nb_reloc; i++) {
            int offset = archive.reloc_info[i].offset;
            int[] ptr = ByteBuffer.wrap(archive.data, offset, 4).asIntBuffer().array();
            ptr[0] += ByteBuffer.wrap(archive.data).getInt();
        }
    }
    
    public static int getPublicAddress(Archive archive, String symbols) {
        for (int i = 0; i < archive.header.nb_public; i++) {
            String symbol = new String(archive.symbols, archive.public_info[i].symbol, symbols.length());

            if (symbol.equals(symbols)) {
                return ByteBuffer.wrap(archive.data, archive.public_info[i].offset, archive.data.length - archive.public_info[i].offset).get();
            }
        }

        return -1;
    }

    public static int getExtern(Archive archive, int offset) {
        if (offset < 0 || offset >= archive.header.nb_extern) {
            return -1;
        }

        return CharBuffer.wrap(archive.symbols, archive.extern_info[offset].symbol, archive.symbols.length - archive.extern_info[offset].symbol).get();
    }

    public static void ArchiveLocateExtern(Archive archive, String symbols, Object addr) {
        long next;
        long offset = -1;
        int i;

        for (i = 0; i < archive.header.nb_extern; i++) {
            String symbol = new String(archive.symbols, archive.extern_info[i].symbol, symbols.length());
            int comparison = symbols.compareTo(symbol);

            if (comparison == 0) {
                offset = archive.extern_info[i].offset;
                break;
            }
        }

        if (offset == -1) {
            return;
        }

        while (offset != -1 && offset < archive.header.data_size) {
            next = ByteBuffer.wrap(archive.data, (int) offset, Long.BYTES)
                    .order(ByteOrder.LITTLE_ENDIAN)
                    .getLong();

            ByteBuffer.wrap(archive.data, (int) offset, Long.BYTES)
                    .order(ByteOrder.LITTLE_ENDIAN)
                    .putLong((long) addr);

            offset = next;
        }
    }

    private static byte[] getVersion(byte index) throws IOException {
        if (index == -1)
        return null;

        ByteBuffer buffer = Utils.seekRaf(0x0 + index, new byte[4]);
        return new byte[] {buffer.get(0x0), buffer.get(0x1), buffer.get(0x2), buffer.get(0x3)};
    }

    private static int[] getPad(int index) throws IOException {
        if (index == -1)
        return null;

        ByteBuffer buffer = Utils.seekRaf(0x0 + index, new byte[2]);
        return new int[] {buffer.getInt(0x0), buffer.getInt(0x4)};
    }
}