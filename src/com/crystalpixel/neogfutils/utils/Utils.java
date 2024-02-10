package com.crystalpixel.neogfutils.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Utils {

    private static final Map<Short, Character> capcomTable = new TreeMap<>();
    private static final Map<Integer, Integer> dataSectionMap = new TreeMap<>();

    private static final int DATA_SECTION_COUNT = 10;
    private static final int DATA_SECTION_ENTRY_SIZE = 4;
    private static final int DATA_OFFSET_START = 0x1c;
    private static final int DATA_ADDRESS_START = 0x64;
    private static final long DATA_ADDRESS_MASK = 0xffffffffL;
    private static final int DATA_ADDRESS_LIMIT = 0xffffff;
    private static final int DATA_OFFSET_MASK = 0x7fffffff;

    public static Integer getOffsetForDataAddress(int address) throws IOException {
        if (dataSectionMap.isEmpty()) {
            populateDataSectionMap();
        }
        for (Map.Entry<Integer, Integer> dataSection : dataSectionMap.entrySet()) {
            if (address > dataSection.getKey()) {
                return DATA_OFFSET_MASK & (dataSection.getKey() - dataSection.getValue());
            }
        }
        return -1;
    }

    private static void populateDataSectionMap() throws IOException {
        try (RandomAccessFile raf = getRaf()) {
            for (int i = 0; i <= DATA_SECTION_COUNT; i++) {
                int dataOffset = seekRaf(raf, DATA_OFFSET_START + i * DATA_SECTION_ENTRY_SIZE, new byte[4]).getInt();
                int dataAddress = seekRaf(raf, DATA_ADDRESS_START + i * DATA_SECTION_ENTRY_SIZE, new byte[4]).getInt();
                if (dataOffset > 0 && dataAddress < 0) {
                    dataSectionMap.put(dataAddress, dataOffset);
                }
            }
        }
    }

    public static RandomAccessFile getRaf() throws FileNotFoundException {
        URL url = Utils.class.getResource("/boot.dol");
        return new RandomAccessFile(url.getFile(), "rw");
    }

    public static ByteBuffer seekRaf(int address, byte[] magic) throws IOException {
        try (RandomAccessFile raf = getRaf()) {
            return seekRaf(raf, address, magic);
        }
    }

    public static ByteBuffer seekRaf(RandomAccessFile raf, int address, byte[] magic) throws IOException {
        if ((address & DATA_ADDRESS_MASK) >= 0x100) {
            address -= getOffsetForDataAddress(address);
        }
        raf.seek(DATA_ADDRESS_LIMIT & address);
        raf.readFully(magic);
        return ByteBuffer.wrap(magic);
    }

    public static String getAsHexString(int number) {
        return String.format("0x%s", Integer.toHexString(number).toUpperCase());
    }

    public static int getHexInput(String text) {
        System.out.print(text);
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            input = scanner.nextLine().toLowerCase().trim();
        }
        return input.startsWith("0x") ? Integer.parseInt(input.substring(2), 16) : Integer.parseInt(input);
    }

    public static Map<Short, Character> getCapcomTable() {
        if (capcomTable == null) {
            // capcomTable.put()
        }
        return capcomTable;
    }

    public static String encodeCapcomText(byte[] bytes) {

        return null;
    }

    public static byte[] decodeCapcomText(String text) {

        return null;
    }

    public static String toHalfWidth(String fullWidth) {
        StringBuilder builder = new StringBuilder();
        for (char c : fullWidth.toCharArray()) {
            builder.append((char) (c - 65248));
        }
        return builder.toString();
    }

    

    // private List<String> getBattleOptions(int value) {
    // List<String> battleOptions = new ArrayList<>();
    // if ((value & 0x1) != 0) battleOptions.add("Ally GF Energy");
    // if ((value & 0x2) != 0 || (value & 0x40) != 0) battleOptions.add("Enemy GF
    // Energy");
    // if ((value & 0x4) != 0) battleOptions.add("Ally Score");
    // if ((value & 0x8) != 0) battleOptions.add("Enemy Score");
    // if ((value & 0x10) != 0) battleOptions.add("Time Up Ally Win");
    // else battleOptions.add("Time Up Enemy Win");
    // if ((value & 0x40) != 0) {
    // battleOptions.add("Face Your Opponent!");
    // }
    // else if ((value & 0x8) != 0) {
    // battleOptions.add("Defeat The Boss!");
    // }
    // else {
    // battleOptions.add("Defeat Enemy Gotcha Borgs!");
    // }
    // }

    // private static int getOffsetForDataAddress(int address) throws IOException {
    // return dataSectionMap.get(address);
    // }
}
