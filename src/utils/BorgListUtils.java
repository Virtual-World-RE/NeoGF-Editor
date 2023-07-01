package utils;

import system.BorgSpecies;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.*;

public class BorgListUtils {

    private static final int OPPONENT_BORG_ARRAYS_START_ADDRESS = 0x8038705c;
    private static final int RANDOM_BORG_ARRAYS_START_ADDRESS = 0x803876fc;
    private static Map<Integer, List<BorgSpecies>> opponentBorgListMap = new HashMap<>();
    private static Map<Integer, List<BorgSpecies>> randomBorgListMap = new HashMap<>();

    // Returns an address containing an array of opponent borg ids for a given index.
    private static int getOpponentBorgListAddress(int index) throws IOException {
        return getBorgListAddress(OPPONENT_BORG_ARRAYS_START_ADDRESS, index);
    }

    // Returns an address containing an array of random encounter borg ids for a given index.
    private static int getRandomBorgListAddress(int index) throws IOException {
        return getBorgListAddress(RANDOM_BORG_ARRAYS_START_ADDRESS, index);
    }

    // Returns an address containing an array of borg ids for a given index.
    private static int getBorgListAddress(int startAddress, int index) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        raf.seek((startAddress & 0xffffff) + index * 4);
        byte[] magic = new byte[4];
        raf.readFully(magic);
        return ByteBuffer.wrap(magic).asIntBuffer().get();
    }

    // Returns a list of borg species for a given start address.
    private static List<BorgSpecies> getBorgList(int startAddress) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        raf.seek(startAddress & 0xffffff);
        List<BorgSpecies> borgSpeciesList = new ArrayList<>();
        while (true) {
            byte[] magic = new byte[2];
            raf.readFully(magic);
            ByteBuffer byteBuffer = ByteBuffer.wrap(magic);
            int borgId = byteBuffer.getShort();
            if (borgId == -1) break;
            borgSpeciesList.add(BorgSpecies.getBorgSpecies(borgId));
        }
        return borgSpeciesList;
    }

    public static List<BorgSpecies> getOpponentBorgList(int index) throws IOException {
        if (!opponentBorgListMap.containsKey(index)) {
            opponentBorgListMap.put(index, getBorgList(getOpponentBorgListAddress(index)));
        }
        return opponentBorgListMap.get(index);
    }

    public static List<BorgSpecies> getRandomBorgList(int index) throws IOException {
        if (!randomBorgListMap.containsKey(index)) {
            randomBorgListMap.put(index, getBorgList(getRandomBorgListAddress(index)));
        }
        return randomBorgListMap.get(index);
    }

    public static String getBorgListAsString(List<BorgSpecies> borgList) {
        StringBuilder builder = new StringBuilder("{");
        borgList.forEach(bs -> builder.append("\n\t").append(bs.getNameFormatted()));
        builder.append("\n}");
        return builder.toString();
    }
}