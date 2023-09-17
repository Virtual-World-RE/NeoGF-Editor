package com.crystalpixel.neogfutils.system;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.*;
import java.util.stream.Collectors;

import com.crystalpixel.neogfutils.scriptevents.SpawnEvent;
import com.crystalpixel.neogfutils.utils.Utils;
import com.crystalpixel.neogfutils.utils.borg.BorgListUtils;
import com.crystalpixel.neogfutils.utils.story.StoryUtils;

public class BattleBorgDetails {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.print("Please enter the Battle ID: ");
        BattleBorgDetails battleBorgDetails = new BattleBorgDetails();
        battleBorgDetails.printBorgCounts(Integer.parseInt(SCANNER.nextLine()));
    }

    private void printBorgCounts(int battle) throws IOException {
        IntBuffer scriptAddresses = (IntBuffer) StoryUtils.getBattleScriptAddresses(battle).position(2);
        List<SpawnEvent> spawnEvents = new ArrayList<>();
        Map<Integer, Integer> borgCounts = new TreeMap<>();
        while (scriptAddresses.hasRemaining()) {
            spawnEvents.addAll(StoryUtils.readBattleScript(scriptAddresses.get()).stream().filter(se ->
                    se instanceof SpawnEvent).map(SpawnEvent.class::cast).collect(Collectors.toList()));
        }
        spawnEvents.forEach(be -> borgCounts.put(be.getId(), borgCounts.containsKey(be.getId()) ? borgCounts.get(be.getId()) + 1 : 1));

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
       StringBuilder ascii = new StringBuilder();
       for (int i = 0; i < chars.length(); i++) {
           char c = chars.charAt(i);

           //Check if the character is half-width or full-width (variable-width character)
           if (c >= 0xFF00 && c <= 0xFFEF) {
               c = (char) (0xFF & (c - 0xFEE0));
           }

           ascii.append(c);
       }

       return ascii.toString();
    }

}