package com.crystalpixel.neogfutils.utils;

import com.crystalpixel.neogfutils.system.MissionScriptThing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {

    private static final Map<Short, Character> capcomTable = new TreeMap<>();
    private static final Map<Integer, Integer> dolSectionMap = new TreeMap<>(Collections.reverseOrder());

    private static final int SECTION_COUNT = 18;
    private static final int DATA_SECTION_ENTRY_SIZE = 4;
    private static final int DATA_OFFSET_START = 0x00;
    private static final int DATA_ADDRESS_START = 0x48;
    private static final long DATA_ADDRESS_MASK = 0xffffffffL;
    private static final int DATA_ADDRESS_LIMIT = 0xffffff;
    private static final int DATA_OFFSET_MASK = 0x7fffffff;

    private static final int INSTRUCTION_BRANCH_OFFSET_MASK = 0x3fffffc;

    // returns the physical address from the virtual address provided.
    public static Integer getOffsetForDataAddress(int address) throws IOException {
        if (dolSectionMap.isEmpty()) {
            populateDataSectionMap();
        }
        for (Map.Entry<Integer, Integer> dataSection : dolSectionMap.entrySet()) {
            if (address >= dataSection.getKey()) {
                return address - dataSection.getKey() + dataSection.getValue();
            }
        }
        return -1;
    }

    private static void populateDataSectionMap() throws IOException {
        try (RandomAccessFile raf = getDolRaf()) {
            for (int i = 0; i <= SECTION_COUNT; i++) {
                int dataOffset = seekDolRaf(raf, DATA_OFFSET_START + i * DATA_SECTION_ENTRY_SIZE, new byte[4]).getInt();
                if (dataOffset > 0) {
                    int dataAddress = seekDolRaf(raf, DATA_ADDRESS_START + i * DATA_SECTION_ENTRY_SIZE, new byte[4]).getInt();
                    dolSectionMap.put(dataAddress, dataOffset);
                    System.out.println(Utils.getAsHexString(dataAddress) + ": " + Utils.getAsHexString(dataOffset));
                }
            }
        }
    }

    public static RandomAccessFile getDolRaf() throws FileNotFoundException {
        return getRaf("/boot.dol");
    }

    public static RandomAccessFile getRaf(String filepath) throws FileNotFoundException {
        URL url = Utils.class.getResource(filepath);
        return new RandomAccessFile(url.getFile(), "rw");
    }

    public static ByteBuffer seekDolRaf(int address, byte[] magic) throws IOException {
        try (RandomAccessFile raf = getDolRaf()) {
            return seekDolRaf(raf, address, magic);
        }
    }

    public static ByteBuffer seekDolRaf(RandomAccessFile raf, int address, byte[] magic) throws IOException {
        if ((address & DATA_ADDRESS_MASK) >= 0x100) {
            address = getOffsetForDataAddress(address);
        }
        return seekRaf(raf, (address & DATA_ADDRESS_LIMIT), magic);
    }

    public static ByteBuffer seekRaf(RandomAccessFile raf, int address, byte[] magic) throws IOException {
        raf.seek(address & DATA_ADDRESS_MASK);
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

//    // Returns multiple RAM write codes, starting from the address provided.
//    public static String makeActionReplayCodes(int address, byte[] data) {
//        StringBuilder code = new StringBuilder();
//        ByteBuffer dataBuffer = ByteBuffer.wrap(data);
//        while (dataBuffer.hasRemaining()) {
//            int size = Math.min(4, dataBuffer.array().length - dataBuffer.position());
//            int codeType = (size >> 1) * 2;
//            size = Math.max(1, codeType);
//
//            int dataLine = 0;
//            for (int i = 0; i < size; i++) {
//                dataLine |= (dataBuffer.get() << ((size - 1) * 8 - (i * 8)));
//            }
//            int addressLine = (codeType << 24) | (0x00ffffff & address);
//            code.append(String.format("%08x %08x\n", addressLine, dataLine));
//            address += size;
//        }
//        return code.toString();
//    }

    // Returns multiple RAM write code lines, starting from the address provided.
    public static String makeActionReplayCode(int address, byte[] data) {
        StringBuilder code = new StringBuilder();
        ByteBuffer dataBuffer = ByteBuffer.wrap(data);
        while (dataBuffer.hasRemaining()) {
            int alignment = (3 & address);
            if (alignment == 0) alignment = 4;
            if ((alignment & 1) != 0) alignment = 1;

            int size = Math.min(4, dataBuffer.array().length - dataBuffer.position());
            if (alignment < size) size = alignment;
            int codeType = (size >> 1) * 2;

            int dataLine = 0;
            switch (codeType) {
                case 4: dataLine = dataBuffer.getInt(); break;
                case 2: dataLine = dataBuffer.getShort(); break;
                case 0: dataLine = dataBuffer.get(); break;
            }
            int addressLine = (codeType << 24) | (0xffffff & address);
            code.append(String.format("%08x %08x\n", addressLine, dataLine));
            address += Math.max(1, codeType);
        }
        return code.toString();
    }

    public static boolean applyGeckoCode(RandomAccessFile raf, String code) throws IOException {
        code = code.replaceAll("\\s", "");
        byte[] codeBytes = new byte[code.length()/2];
        for (int x = 0; x < code.length(); x+=8) {
            int instruction = Integer.parseUnsignedInt(code.substring(x, x + 8), 16);
            for (int y = 0; y < 4; y++) {
                codeBytes[(x/2) + y] = (byte) (instruction >> (24 - (y * 8)));
            }
        }
        return applyGeckoCode(raf, codeBytes);
    }

    public static boolean applyGeckoCode(RandomAccessFile raf, byte[] code) throws IOException {
        final int WRITE = 0x0;
        final int ASM = 0x6;

        // Write sub code types.
        final int BYTE = 0x0;
        final int HALFWORD = 0x1;
        final int FULLWORD = 0x2;
        final int PATCH = 0x3;
        final int SLIDER = 0x4;

        // ASM sub code types.
        final int INSERT = 0x1;
        final int BRANCH = 0x3;

        final int baseAddress = 0x80000000;

        final Map<Integer, byte[]> asmInsertCodeMap = new HashMap<>();

        final String errorArgumentFormat = "Code type '%s' is not supported";
        ByteBuffer codeBuffer = ByteBuffer.wrap(code);
        int position = 0;
        while (codeBuffer.hasRemaining()) {
            final int codeMainType = (0xe0 & codeBuffer.get(codeBuffer.position())) >> 5;
            final int codeSubType = (0xe & codeBuffer.get(codeBuffer.position())) >> 1;
            final boolean isPointerOffset = 0 != (codeBuffer.get(codeBuffer.position()) & 0x10);

            switch (codeMainType) {
                case WRITE: {
                    final int address = baseAddress | codeBuffer.getInt(position) & 0x1ffffff;

                    switch (codeSubType) {
                        case BYTE:
                        case HALFWORD:
                        case FULLWORD: {
                            final int writeCount = codeSubType == FULLWORD ? 1 : (codeBuffer.getShort(position + 4) & 0xffff) + 1;
                            final byte[] writeBytes;
                            switch (codeSubType) {
                                case FULLWORD: writeBytes = getByteArrayRange(codeBuffer.array(), position + 4, Integer.BYTES); break;
                                case HALFWORD: writeBytes = getByteArrayRange(codeBuffer.array(), position + 6, Short.BYTES); break;
                                case BYTE: writeBytes = getByteArrayRange(codeBuffer.array(), position + 7, Byte.BYTES); break;
                                default: throw new IllegalArgumentException(String.format(errorArgumentFormat, codeSubType));
                            }

                            for (int i = 0; i < writeCount; i++) {
                                raf.seek(getOffsetForDataAddress(address + (writeBytes.length * i)));
                                raf.write(writeBytes);
                            }
                            codeBuffer.position(position += 8);
                        } break;
                        case PATCH: {
                            final int byteCount = codeBuffer.getInt(position + 4);
                            final byte[] writeBytes = getByteArrayRange(codeBuffer.array(), position + 8, byteCount);

                            raf.seek(getOffsetForDataAddress(address));
                            raf.write(writeBytes);
                            codeBuffer.position(position += 8 + ((byteCount + 7) & -8));
                        } break;
                        case SLIDER: {
                            final int baseValue = codeBuffer.getInt(position + 4);
                            final int type = codeBuffer.get(position + 8) >> 4;
                            final int writeCount = (codeBuffer.getShort(position + 8) & 0xfff) + 1;
                            final int addressIncrement = codeBuffer.getShort(position + 10) & 0xffff;
                            final int valueIncrement = codeBuffer.getInt(position + 12);

                            for (int i = 0; i < writeCount; i++) {
                                final int value = baseValue + (valueIncrement * i);
                                final byte[] writeBytes;
                                switch (type) {
                                    case FULLWORD: writeBytes = getAsBytes(value); break;
                                    case HALFWORD: writeBytes = getAsBytes((short) value); break;
                                    case BYTE: writeBytes = getAsBytes((byte) value); break;
                                    default: throw new IllegalArgumentException(String.format(errorArgumentFormat, type));
                                }

                                raf.seek(getOffsetForDataAddress(address + (addressIncrement * i)));
                                raf.write(writeBytes);
                            }
                            codeBuffer.position(position += 16);
                        } break;
                        default: throw new IllegalArgumentException(String.format(errorArgumentFormat, codeSubType));
                    }
                } break;
                case ASM: {
                    final int address = baseAddress | codeBuffer.getInt(position) & 0x1ffffff;
                    switch (codeSubType) {
                        case INSERT: {
                            final int codeLineCount = codeBuffer.getInt(position + 4);
                            byte[] writebytes = getByteArrayRange(codeBuffer.array(), position + 8, codeLineCount * 8);
                            asmInsertCodeMap.put(address, writebytes);
//                            final int nopInstruction = OperationCode.NOP.getId() << 26;

//                            IntBuffer intBuf =
//                                    ByteBuffer.wrap(writebytes)
//                                            .order(ByteOrder.BIG_ENDIAN)
//                                            .asIntBuffer();
//                            int[] array = new int[intBuf.remaining()];
//                            intBuf.get(array);
//                            List<Integer> instructionList = Arrays.stream(array).boxed().filter(i -> i != nopInstruction).collect(Collectors.toList());

                            codeBuffer.position(position += 8 + (8 * codeLineCount));
                        } break;
                        case BRANCH: {
                            final int branchAddress = codeBuffer.getInt(position + 4) & -4;
                            raf.seek(getOffsetForDataAddress(address));
                            raf.writeInt(createBranchInstruction(address, branchAddress));
                            codeBuffer.position(position += 8);
                        } break;
                        default: throw new IllegalArgumentException(String.format(errorArgumentFormat, codeSubType));
                    }
                } break;
                default: throw new IllegalArgumentException(String.format(errorArgumentFormat, codeMainType));
            }
        }

        // writing asm insert codes...
        for (Map.Entry<Integer, byte[]> codeEntry : asmInsertCodeMap.entrySet()) {
            final int branchAddress = codeEntry.getKey();
            final byte[] writeBytes = codeEntry.getValue();
            for (Map.Entry<Integer, Integer> memoryEntry : MissionScriptThing.memorySizeMap.entrySet()) {
                if (writeBytes.length <= memoryEntry.getValue()) {
                    // write code to raf.
                    raf.seek(getOffsetForDataAddress(branchAddress));
                    raf.writeInt(createBranchInstruction(branchAddress, memoryEntry.getKey()));

                    final int branchInstructionOffset = writeBytes.length - 4;
                    final int branchInstruction = createBranchInstruction(memoryEntry.getKey() + branchInstructionOffset, branchAddress + 4);
                    ByteBuffer.wrap(writeBytes).putInt(branchInstructionOffset, branchInstruction);
                    raf.seek(getOffsetForDataAddress(memoryEntry.getKey()));
                    raf.write(writeBytes);

                    MissionScriptThing.memorySizeMap.put(memoryEntry.getKey(), memoryEntry.getValue() - writeBytes.length);
                    break;
                }
            }
        }
        return true;
    }

    public static byte[] getByteArrayRange(byte[] array, int offset, int length) {
        return Arrays.copyOfRange(array, offset, offset + length);
    }

    public static int createBranchInstruction(int fromAddress, int toAddress) {
        return createBranchInstruction(fromAddress, toAddress, false);
    }

    public static int createBranchInstruction(int fromAddress, int toAddress, boolean link) {
        final int offset = toAddress - fromAddress;
        int instruction = offset & INSTRUCTION_BRANCH_OFFSET_MASK;
        instruction |= (OperationCode.BRANCH.getId() << 26);
        if (link) instruction |= 0x1;
        return instruction;
    }

    public static byte[] getAsBytes(Number value) {
        return getAsBytes(value, ByteOrder.BIG_ENDIAN);
    }

    public static byte[] getAsBytes(Number value, ByteOrder order) {
        int length = 0;
        if (value instanceof Byte) length = Byte.BYTES;
        if (value instanceof Short) length = Short.BYTES;
        if (value instanceof Integer) length = Integer.BYTES;
        if (value instanceof Long) length = Long.BYTES;
        byte[] bytes = new byte[length];
        boolean isBigEndian = order == ByteOrder.BIG_ENDIAN;
        for (int i = 0; i < length; i++) {
            bytes[isBigEndian ? length - 1 - i : i] = (byte) (value.longValue() >> (Byte.SIZE * i));
        }
        return bytes;
    }

//    public static boolean isLisBranchInstruction(int instruction) {
//        if (OperationCode.getOpCode(instruction) == OperationCode.ADD_IMMEDIATE_SHIFTED) {
//            int registerTarget = (instruction & 0x3e00000) >> 21;
//            int registerAdd = (instruction & 0x1f0000) >> 16;
//            if (registerTarget == 12 && registerAdd == 0) {
//                System.out.println("This is a lis r12 instruction.");
//                return true;
//            }
//        }
//        return false;
//    }

//    public static byte[] getAsmInsertBytes() {
//        for (int i = 0; i < codeLineCount; i++) {
//            int instruction = codeBuffer.getInt();
//            if (OperationCode.getOpCode(instruction) == OperationCode.ADD_IMMEDIATE_SHIFTED) {
//                int registerTarget = (instruction & 0x3e00000) >> 21;
//                int registerAdd = (instruction & 0x1f0000) >> 16;
//                if (registerTarget == 12 && registerAdd == 0) {
//                    System.out.println("This is a lis r12 instruction.");
//                }
//            }
//        }
//    }

    // Returns multiple RAM write code lines, starting from the address provided.
//    public static String makeActionReplayCode(int address, byte[] data) {
//        StringBuilder code = new StringBuilder();
//        ByteBuffer dataBuffer = ByteBuffer.wrap(data);
//        while (dataBuffer.hasRemaining()) {
////            int remainingLength = dataBuffer.array().length - dataBuffer.position();
//
//            int startAlignment = (3 & address);
//            if (startAlignment == 0) startAlignment = 4;
//            if ((startAlignment & 1) != 0) startAlignment = 1;
//
////            List<Byte> dynamicByteList = new ArrayList<>();
//            byte startByte = dataBuffer.get();
//            int writeLength = 0;
//            while (dataBuffer.hasRemaining()) {
//                int position = dataBuffer.position();
//                if (dataBuffer.get(position) != startByte) break;
//                dataBuffer.position(position + 1);
//                writeLength++;
//            }
//
//            int endAlignment = (3 & address + writeLength);
//            if (endAlignment == 0) endAlignment = 4;
//            if ((endAlignment & 1) != 0) endAlignment = 1;
//
//
//
//            int codeType;
//            int dataLine = 0;
//            if (startAlignment == 1 || endAlignment == 1) {
//                codeType = 0;
//                dataLine |= (writeLength << 8);
//            }
//            else if (startAlignment == 2) {
//                codeType = 2;
//                dataLine |= (writeLength << 16);
//            }
//            else codeType = 4;
//
//
//
//            int size = Math.min(4, dataBuffer.array().length - dataBuffer.position());
//            if (startAlignment < size) size = startAlignment;
////            int codeType = (size >> 1) * 2;
//
//            int addressLine = (codeType << 24) | (0xffffff & address);
////            int dataLine = 0;
//            code.append(String.format("%08x %08x\n", addressLine, dataLine));
//            address += Math.max(1, codeType);
//        }
//        return code.toString();
//    }

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
