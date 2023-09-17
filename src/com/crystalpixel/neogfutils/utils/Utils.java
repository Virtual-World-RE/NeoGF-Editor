package com.crystalpixel.neogfutils.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.*;

public class Utils {

    private static Map<Short, Character> capcomTable = new HashMap<>();
    private static Map<Integer, Integer> dataSectionMap = new TreeMap<>(Collections.reverseOrder());

    private static Integer getOffsetForDataAddress(int address) throws IOException {
        if (dataSectionMap.isEmpty()) {
            RandomAccessFile raf = getRaf();
            for (int i = 0; i <= 10; i++) {
                int dataOffset = seekRaf(raf, 0x1c + i * 4, new byte[4]).asIntBuffer().get();
                int dataAddress = seekRaf(raf, 0x64 + i * 4, new byte[4]).asIntBuffer().get();
                if (dataOffset > 0 && dataAddress < 0) {
                    dataSectionMap.put(dataAddress, dataOffset);
                }
            }
        }
        for (Map.Entry<Integer, Integer> dataSection : dataSectionMap.entrySet()) {
            if (address > dataSection.getKey()) {
                return 0x7fffffff & dataSection.getKey() - dataSection.getValue();
            }
        }
        return -1;
    }

//    private static int getOffsetForDataAddress(int address) throws IOException {
//        return dataSectionMap.get(address);
//    }

    public static RandomAccessFile getRaf() throws FileNotFoundException {
        //File file = new File(Utils.class.getClassLoader().getResource("boot.dol").getPath());
        File file = new File(System.getProperty("."), "resources/boot.dol");
        return new RandomAccessFile(file, "rw");
    }

    public static ByteBuffer seekRaf(int address, byte[] magic) throws IOException {
        return seekRaf(getRaf(), address, magic);
    }

    public static ByteBuffer seekRaf(RandomAccessFile raf, int address, byte[] magic) throws IOException {
        if ((address & 0xffffffffL) >= 0x100) address -= getOffsetForDataAddress(address);
        raf.seek(0xffffff & address);
        raf.readFully(magic);
        return ByteBuffer.wrap(magic);
    }

    public static String getAsHexString(int number) {
        return String.format("0x%s", Integer.toHexString(number).toUpperCase());
    }

    public static int getHexInput(String text) {
        System.out.print(text);
        String input = new Scanner(System.in).nextLine().toLowerCase().trim();
        return input.startsWith("0x") ? Integer.parseInt(input.substring(2), 16) : Integer.parseInt(input);
    }

    public static Map<Short, Character> getCapcomTable() {
        if (capcomTable == null) {
//            capcomTable.put()
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

//    private List<String> getBattleOptions(int value) {
//        List<String> battleOptions = new ArrayList<>();
//        if ((value & 0x1) != 0) battleOptions.add("Ally GF Energy");
//        if ((value & 0x2) != 0 || (value & 0x40) != 0) battleOptions.add("Enemy GF Energy");
//        if ((value & 0x4) != 0) battleOptions.add("Ally Score");
//        if ((value & 0x8) != 0) battleOptions.add("Enemy Score");
//        if ((value & 0x10) != 0) battleOptions.add("Time Up Ally Win");
//        else battleOptions.add("Time Up Enemy Win");
//        if ((value & 0x40) != 0) {
//            battleOptions.add("Face Your Opponent!");
//        }
//        else if ((value & 0x8) != 0) {
//            battleOptions.add("Defeat The Boss!");
//        }
//        else {
//            battleOptions.add("Defeat Enemy Gotcha Borgs!");
//        }
//    }
}
