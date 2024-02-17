package com.crystalpixel.neogfutils.system;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.*;

import com.crystalpixel.neogfutils.battle.entity.Commander;
import com.crystalpixel.neogfutils.borg.BorgLevelStats;
import com.crystalpixel.neogfutils.event.*;
import com.crystalpixel.neogfutils.event.cutscene.*;
import com.crystalpixel.neogfutils.utils.Utils;
import com.crystalpixel.neogfutils.utils.borg.BorgUtils;

public class BattleParser {

    public static void main(String[] args) throws IOException {
        BattleParser main = new BattleParser();
        BorgUtils.getBorgLevelStats(0x908);
//        int missionIndex = 117;
//        System.out.println("Battle " + missionIndex + ": " + StoryUtils.getMissionStringIdentifiers(StoryUtils.getStoryBattleConfigurationAddress(missionIndex)));
//        System.out.println("Unlock Conditions:");
//        List<Condition> conditionList = main.readConditions(StoryUtils.getStoryBattleUnlockAddress(missionIndex), true);
//        for (Condition condition : conditionList) {
//            System.out.println("- " + condition.toString());
//        }
//        System.out.println("\nUnlist Conditions:");
//        conditionList = main.readConditions(StoryUtils.getStoryBattleUnlistAddress(missionIndex), false);
//        for (Condition condition : conditionList) {
//            System.out.println("- " + condition.toString());
//        }

//        for (BorgSpeciesThing borgSpeciesThing : main.borgSpeciesMap.values()) {
//            int id = Integer.parseInt(borgSpeciesThing.getId(), 16);
//            int number = borgSpeciesThing.getNo();
//            String name = borgSpeciesThing.getName();
//            int cost = borgSpeciesThing.getCost();
//            Tribe tribe = borgSpeciesThing.getTribe();
//            System.out.println(String.format("%s(%s, %s, \"%s\", %s, Tribe.%s),", name.toUpperCase().replaceAll(" ", "_"), id, number, name, cost, tribe.name()));
//        }

//        for (int i = 0; i < 200; i++) {
        int i = 76;
        BorgUtils.getBorgLevelStats(0x000);
//        main.makeHpBorgLevelStatsCode(BorgUtils.getBorgLevelStatsStartAddress(0xa09), 310, 5);
//        main.makeAmmoBorgLevelStatsCode(BorgUtils.getBorgLevelStatsStartAddress(0xa09), 240);
        BorgLevelStats[] BorgLevelStats = BorgUtils.getBorgLevelStats(0x609);
        for (int x = 0; x < BorgLevelStats.length; x++) {
            BorgLevelStats[x].setHp(390 + (x * 5));
            BorgLevelStats[x].setbAmmoStart(8);
            BorgLevelStats[x].setbAmmoMax(8);
            BorgLevelStats[x].setbAmmoType(1);
            BorgLevelStats[x].setbAmmoReload(180);

            BorgLevelStats[x].setxAmmoStart(3);
            BorgLevelStats[x].setxAmmoMax(3);
            BorgLevelStats[x].setxAmmoType(1);
            BorgLevelStats[x].setxAmmoReload(360);
        }
//        main.makeAmmoBorgLevelStatsCode(BorgUtils.getBorgLevelStatsStartAddress(0x61d), BorgLevelStats);

        main.makeShiftCode(0x8039717e, 4, 0xec);
    }

    private List<MissionEvent> getCustomBattleScript() {
        List<MissionEvent> missionEvents = new ArrayList<>();
        VoiceEvent voiceEvent = new VoiceEvent(0, 0, 5, 5);
        voiceEvent.setMute(true);
        missionEvents.add(voiceEvent);
        for (int i = 0; i < 200; i++) {
            SpeechEvent speechEvent = new SpeechEvent(0, 0, 5, 5);
            speechEvent.setQueue(true);
            speechEvent.setVisible(true);
            speechEvent.setSound(i);
            speechEvent.setCommander(Commander.MANA);
            missionEvents.add(speechEvent);
        }
        return missionEvents;
    }

    private void makeShiftCode(int startAddress, int offset, int size) throws IOException {
        RandomAccessFile raf = Utils.getDolRaf();
        byte[] magic = new byte[4];
        while (size > 0) {
            raf.seek(startAddress & 0x00FFFFFF);
            raf.readFully(magic);
            if (size > 3) {
                int codeLine1 = (0x00FFFFFF & startAddress + offset) | 0x04000000;
                int codeLine2 = ByteBuffer.wrap(magic).getInt(0);
                System.out.println(String.format("%08x %08x", codeLine1, codeLine2));
                startAddress+=4;
                size-=4;
            }
            else {
                int codeLine1 = (0x00FFFFFF & startAddress + offset) | 0x02000000;
                int codeLine2 = ByteBuffer.wrap(magic).getShort(0);
                System.out.println(String.format("%08x %08x", codeLine1, codeLine2));
                startAddress+=2;
                codeLine1 = (0x00FFFFFF & startAddress + offset);
                codeLine2 = ByteBuffer.wrap(magic).get(2);
                System.out.println(String.format("%08x %08x", codeLine1, codeLine2));
                size-=3;
            }
        }
    }

    private void makeBattleScriptCode(int startAddress, List<MissionEvent> missionEvents) {
        for (MissionEvent missionEvent : missionEvents) {
            ByteBuffer scriptBytes = ByteBuffer.wrap(missionEvent.getAsBytes());
            while (scriptBytes.hasRemaining()) {
                int codeLine1 = (0x00FFFFFF & startAddress) | 0x04000000;
                int codeLine2 = scriptBytes.getInt();
                System.out.println(String.format("%08x %08x", codeLine1, codeLine2));
                startAddress+=4;
            }
        }
        int codeLine1 = (0x00FFFFFF & startAddress) | 0x04000000;
        int codeLine2 = 0xFFFF0000;
        System.out.println(String.format("%08x %08x", codeLine1, codeLine2));
    }

    private List<GameEvent> getCustomCutsceneScript() {
        List<GameEvent> cutsceneEvents = new LinkedList<>();
        cutsceneEvents.add(new MusicMapEvent(0));
        cutsceneEvents.add(new PortraitImageEvent(0, Commander.KOU));
        Commander commander = Commander.TSUTOMU;
        cutsceneEvents.add(new PortraitImageEvent(2, commander));
        for (int i = 0; i <= 0x7c; i++) {
            cutsceneEvents.add(new PortraitSoundEvent(commander, i));
            cutsceneEvents.add(new PortraitTextEvent(2, 0x1eb + i));
        }
        cutsceneEvents.add(new TerminatorEvent());
        return cutsceneEvents;
    }

    private void makeCutsceneScriptCode(int startAddress, List<GameEvent> cutsceneEvents) {
        for (GameEvent cutsceneEvent : cutsceneEvents) {
            ByteBuffer scriptBytes = cutsceneEvent.getAsBytes();
            while (scriptBytes.hasRemaining()) {
                int codeLine1 = (0x00FFFFFF & startAddress) | 0x02000000;
                int codeLine2 = scriptBytes.getShort();
                System.out.println(String.format("%08x %08x", codeLine1, codeLine2));
                startAddress+=2;
            }
        }
    }

    //TODO: move to a utils class.
    private void makeAmmoBorgLevelStatsCode(int startAddress, BorgLevelStats[] borgLevelStats) {
        int levelCount = 20;
        int levelSize = 18;
        int dataSize = 2;
        for (int property = 0; property < levelSize/dataSize; property++) {
            System.out.println(BorgLevelStats.getPropertyNameByIndex(property));
            int tempStartAddress = startAddress + (property * dataSize);
            for (int level = 0; level < levelCount; level++) {
                int codeLine = (0x00FFFFFF & tempStartAddress) | 0x2000000;
                System.out.println(String.format("%08x %08x", codeLine, borgLevelStats[level].getPropertyValueByIndex(property)));
                tempStartAddress+=levelSize;
            }
            System.out.println();
        }
    }

    //TODO: make this less bad.
    private List<Condition> readConditions(int startAddress, boolean unlock) throws IOException {
        ByteBuffer byteBuffer;
        List<Condition> conditionList = new ArrayList<>();
        while (true) {
            byteBuffer = Utils.seekDolRaf(startAddress, new byte[2]);
            int parameterCount = byteBuffer.get() & 0xFF;
            int conditionType = byteBuffer.get() & 0xFF;
            startAddress += 2;
            if (conditionType == 0x2e) break;
            List<Integer> parameterList = new ArrayList<>();
            for (int i = 0; i < parameterCount; i++) {
                byteBuffer = Utils.seekDolRaf(startAddress, new byte[2]);
                parameterList.add(byteBuffer.getShort() & 0xffff);
                startAddress += 2;
            }
            if (unlock) {
                conditionList.add(new UnlockCondition(conditionType, parameterList));
            }
            else {
                conditionList.add(new UnlistCondition(conditionType, parameterList));
            }
        }
        return conditionList;
    }

    private Set<String> getCutsceneScript(int startAddress) throws IOException {
        File file = new File(getClass().getClassLoader().getResource("mem1.raw").getPath());
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.seek(startAddress & 0x00FFFFFF);
        List<String> typeList = new ArrayList<>();
        conditionLoop:
        while (true) {
            byte[] magic = new byte[2];
            raf.readFully(magic);
            ByteBuffer byteBuffer = ByteBuffer.wrap(magic);
            int type = byteBuffer.getShort() - 20 & 0xFF;
            switch (type) {
                case 0x0:
                case 0x2:
                case 0x6:
                case 0x15: break;
                case 0x1:
                case 0x5:
                case 0x7:
                case 0x9:
                case 0xA:
                case 0xB:
                case 0xD:
                case 0x12:
                case 0x16:
                case 0x17:
                case 0x18:
                case 0x19:
                    raf.readFully(magic);
                    int paraml = ByteBuffer.wrap(magic).getShort() & 0xFFFF; break;
                case 0x3:
                case 0x4:
                case 0x8:
                case 0xC:
                case 0xE:
                case 0xF:
                case 0x10:
                case 0x11:
                case 0x13:
                case 0x14:
                    byte[] paramMagic = new byte[4];
                    raf.readFully(paramMagic);
                    ByteBuffer paramBuffer = ByteBuffer.wrap(paramMagic);
                    int param1 = paramBuffer.getShort() & 0xFFFF;
                    int param2 = paramBuffer.getShort() & 0xFFFF; break;
                case 0x1A: break conditionLoop;
                default: break conditionLoop;
            }
            typeList.add(Utils.getAsHexString(type));
        }
        return new LinkedHashSet<>(typeList);
    }
}