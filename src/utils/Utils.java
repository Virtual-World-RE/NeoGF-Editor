package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.*;

public class Utils {

    private static Map<Short, Character> capcomTable = new HashMap<>();

    public static RandomAccessFile getRaf() throws FileNotFoundException {
        File file = new File(Utils.class.getClassLoader().getResource("mem1.raw").getPath());
        return new RandomAccessFile(file, "rw");
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
