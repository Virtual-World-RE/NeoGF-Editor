package com.crystalpixel.neogfutils.system;

import com.crystalpixel.neogfutils.borg.BorgLevelStats;
import com.crystalpixel.neogfutils.game.Language;
import com.crystalpixel.neogfutils.utils.Utils;
import com.crystalpixel.neogfutils.utils.borg.BorgUtils;
import com.crystalpixel.neogfutils.utils.story.StoryUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class for looking at mission script data and re-organising it to reduce size.
 *
 * @author CrystalPixel
 */
public class MissionScriptThing {

    public static Map<Integer, Integer> memorySizeMap = new TreeMap<Integer, Integer>() {{
        put(0x80364818, 19552);
        put(0x8036e970, 28928);
        put(0x8039a908, 56192);
    }};

    public static void main(String[] args) throws IOException {
//        Utils.createBranchInstruction(0x80207d40, 0x80207d44, true);
//        Utils.applyGeckoCode(Utils.getDolRaf(), "c20decf4 00000005\n" +
//                "a88603e8 2c040409\n" +
//                "40820014 3c80bf80\n" +
//                "90810018 c0010018\n" +
//                "ec210032 7c641b78\n" +
//                "60000000 00000000");
//        RAM WRITE TEST
//        Utils.getAsHexString(Utils.seekDolRaf(0x8031d2a8, new byte[4]).getInt());
//        Utils.applyGeckoCode(Utils.getDolRaf(), "0231d2a8 00015678\n" +
//                "0431d2b0 8031d1d0\n" +
//                "0231cf82 00000002\n" +
//                "040e0a84 38800002\n" +
//                "040e0ea4 38800000\n" +
//                "040e0fc0 38800000\n" +
//                "040e0948 a8030774");
//        Utils.getAsHexString(Utils.seekDolRaf(0x8031d2a8, new byte[4]).getInt());

//        PATCH TEST
//        BorgUtils.getBorgName(1, Language.ENGLISH);
//        Utils.applyGeckoCode(Utils.getDolRaf(), "062b3e54 00000019\n" +
//                "82628274 82738273\n" +
//                "82648271 8140826d\n" +
//                "8268826d 82698260\n" +
//                "00000000 00000000");
//        BorgUtils.getBorgName(1, Language.ENGLISH);

//        SLIDER TEST
//        Arrays.stream(BorgUtils.getBorgLevelStats(0xa04)).map(BorgLevelStats::getHp).toArray();
//        Utils.applyGeckoCode(Utils.getDolRaf(), "082f4f58 00000122\n" +
//                "10130012 00000005");
//        Arrays.stream(BorgUtils.getBorgLevelStats(0xa04)).map(BorgLevelStats::getHp).toArray();

//        BRANCH TEST
//        Utils.getAsHexString(Utils.seekDolRaf(0x80207d40, new byte[4]).getInt());
//        Utils.applyGeckoCode(Utils.getDolRaf(), "c6207d40 80207d47");

        System.out.println("Starting...");
        List<List<MissionScript>> battleScriptLists = new ArrayList<>();
        for (int x = 0; x < 200; x++) {
            List<MissionScript> missionScripts = new ArrayList<>();
            for (int y = 0; y < 4; y++) {
                int address = StoryUtils.getBattleScriptAddresses(x).get(y);
                missionScripts.add(StoryUtils.readBattleScript(address));
            }
            battleScriptLists.add(missionScripts);
        }
        List<MissionScript> distinctList = battleScriptLists.stream().flatMap(Collection::stream).distinct()
                .sorted(Comparator.comparing(m -> ((MissionScript) m).getAsBytes().length).reversed())
                .collect(Collectors.toList());
//        int totalSize = 0;
//        for (int i = 0x0; i < 200; i++) {
//            int size = 0;
//            for (int p = 0; p < 4; p++) {
//                List<MissionEvent> missionEvents = distinctList.get((i * 4) + p);
//                size += 32 * (missionEvents.size() + 1);
//            }
//            System.out.println(String.format("Mission %s: %s", i, size));
//            totalSize += size;
//        }
//        System.out.println(String.format("Total: %s", totalSize));
//        for (MissionScript missionScript : distinctList) {
//            size += missionScript.getAsBytes().length;
//        }


        Map<MissionScript, Integer> missionScriptPointerMap = new HashMap<>();
        for (MissionScript missionScript : distinctList) {
            int scriptSize = missionScript.getAsBytes().length;
            for (Map.Entry<Integer, Integer> entry : memorySizeMap.entrySet()) {
                int remainingSize = entry.getValue();
                if (remainingSize >= scriptSize) {
                    int pointer = entry.getKey();
                    missionScriptPointerMap.put(missionScript, pointer);
                    memorySizeMap.remove(pointer);
                    remainingSize -= scriptSize;
                    if (remainingSize > 0) {
                        pointer += scriptSize;
                        memorySizeMap.put(pointer, remainingSize);
                    }
                    break;
                }
            }
        }

        System.out.println("Making Mission Script Pointer Codes...");
        int pointerAddress = StoryUtils.BATTLE_SCRIPTS_START_ADDRESS;
        for (MissionScript missionScript : battleScriptLists.stream().flatMap(Collection::stream).collect(Collectors.toList())) {
            String code = Utils.makeActionReplayCode(pointerAddress, ByteBuffer.allocate(4).putInt(missionScriptPointerMap.get(missionScript)).array());
            Utils.applyGeckoCode(Utils.getDolRaf(), code);
            System.out.print(code);
            pointerAddress += 4;
        }

        System.out.println("Making Mission Script Data Codes...");
        for (Map.Entry<MissionScript, Integer> entry : missionScriptPointerMap.entrySet()) {
//            System.out.print(Utils.makeActionReplayCode(0x80000005, new byte[]{1, 2, 3, 4, 5, 6, 7}));
//            System.out.print(Utils.makeActionReplayCode(entry.getValue(), StoryUtils.readBattleScript(StoryUtils.getBattleScriptAddresses(0).get(0)).getAsBytes()));
            String code = Utils.makeActionReplayCode(entry.getValue(), entry.getKey().getAsBytes());
            Utils.applyGeckoCode(Utils.getDolRaf(), code);
            System.out.print(code);
        }

        System.out.println("Applying Gecko Codes...");
        Utils.applyGeckoCode(Utils.getDolRaf(), "c20decf4 00000005\n" +
                "a88603e8 2c040409\n" +
                "40820014 3c80bf80\n" +
                "90810018 c0010018\n" +
                "ec210032 7c641b78\n" +
                "60000000 00000000");

        System.out.println("Finishing...");
//        List<Collection<MissionScript>> collect = battleScriptLists.stream().map(Map::values).collect(Collectors.toList());
//        for (battleScriptLists.stream().map(Map::values).collect(Collectors.toList())) {
//
//        }
    }
}