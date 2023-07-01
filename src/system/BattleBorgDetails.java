package system;

import scriptevents.BorgEvent;
import structures.Borg;
import utils.BorgListUtils;
import utils.StoryUtils;
import utils.Utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.*;
import java.util.stream.Collectors;

public class BattleBorgDetails {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

//        List<BorgSpecies> borgSpeciesList = BorgSpecies.getAllBorgSpecies().values().stream().sorted(Comparator.comparing(BorgSpecies::getId)).collect(Collectors.toList());
//        for (BorgSpecies borgSpecies : borgSpeciesList) {
////            System.out.println(String.format("%08x %08x", borgSpecies.getId()));
//            System.out.println(borgSpecies.getNo());
//        }
//
//        Borg borg = new Borg(BorgSpecies.getBorgSpecies(0x615));

        System.out.print("Please enter the Battle ID: ");
        BattleBorgDetails battleBorgDetails = new BattleBorgDetails();
        battleBorgDetails.printBorgCounts(Integer.parseInt(SCANNER.nextLine()));
    }

    private void printBorgCounts(int battle) throws IOException {
        IntBuffer scriptAddresses = StoryUtils.getBattleScriptAddresses(battle).position(2);
        List<BorgEvent> borgEvents = new ArrayList<>();
        Map<Integer, Integer> borgCounts = new TreeMap<>();
        while (scriptAddresses.hasRemaining()) {
            borgEvents.addAll(StoryUtils.readBattleScript(scriptAddresses.get()).stream().filter(se ->
                    se instanceof BorgEvent).map(BorgEvent.class::cast).collect(Collectors.toList()));
        }
        borgEvents.forEach(be -> borgCounts.put(be.getId(), borgCounts.containsKey(be.getId()) ? borgCounts.get(be.getId()) + 1 : 1));

        if (!borgCounts.isEmpty()) System.out.println("\nEnemy Script Borg Counts:");

        borgCounts.forEach((Integer id, Integer count) ->
                System.out.println(String.format("%sx %s", count,
                (id & 0x8000) != 0 ? "Random Borg List: " + Utils.getAsHexString(id & 0x7FFF) : BorgSpecies.getBorgSpecies(id).getNameFormatted())));

        for (Integer id : borgCounts.keySet()) {
            if ((id & 0x8000) != 0)
                System.out.println(String.format("\nRandom Borg List: %s ", Utils.getAsHexString(id & 0x7FFF)) +
                        BorgListUtils.getBorgListAsString(BorgListUtils.getRandomBorgList(id & 0x7FFF)));
        }
    }

    public static String getShiftJIS() throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        int nameAddress = 0x8F3268;
        byte[] magic = new byte[2];
        raf.seek(0xFFFFFF & nameAddress);
        raf.readFully(magic);
        ByteBuffer buffer = ByteBuffer.wrap(magic);
        StringBuilder name = new StringBuilder();
        while (buffer.getShort(0) != 0) {
            name.append(new String(buffer.array(), "Shift_JIS"));
            nameAddress += 2;
            raf.seek(0xFFFFFF & nameAddress);
            raf.readFully(magic);
        }
        return name.toString();
    }

    public static String toFullWidthWithLoop(String halfWidth) {
        StringBuilder builder = new StringBuilder();
        for (char c : halfWidth.toCharArray()) {
            builder.append((char) (c - 65248));
        }
        return builder.toString();
    }

    public static String toASCII(String chars) {
        String ascii = "";
        for(int i = 0, l = chars.length(); i < l; i++) {
            char c = chars.charAt(i);

            // make sure we only convert half-full width char
//            if (c >= 0xFF00 && c <= 0xFFEF) {
                c = (char) (0xFF & (c + 0x20));
//            }

            ascii += c;
        }

        return ascii;
    }
}