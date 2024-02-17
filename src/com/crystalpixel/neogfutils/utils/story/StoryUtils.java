package com.crystalpixel.neogfutils.utils.story;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.crystalpixel.neogfutils.battle.Battle;
import com.crystalpixel.neogfutils.battle.MapLocation;
import com.crystalpixel.neogfutils.battle.entity.Commander;
import com.crystalpixel.neogfutils.battle.entity.Opponent;
import com.crystalpixel.neogfutils.battle.entity.Position;
import com.crystalpixel.neogfutils.event.*;
import com.crystalpixel.neogfutils.game.*;
import com.crystalpixel.neogfutils.system.BorgSpecies;
import com.crystalpixel.neogfutils.utils.Utils;

public class StoryUtils {

    private static final int BATTLE_ADDRESSES_START_ADDRESS = 0x8035fa04;
    private static final int BATTLE_SCRIPTS_START_ADDRESS = 0x803832c8;
    private static final int BATTLE_CONFIGURATION_START_ADDRESS = 0x803864c8;
    private static final int OPPONENT_START_ADDRESS = 0x803867e8;

    public static Opponent getOpponent(short index) throws IOException {
        if (index == -1)
            return null;
        ByteBuffer buffer = Utils.seekDolRaf(OPPONENT_START_ADDRESS + index * 20, new byte[20]);
        return new Opponent(Commander.get(buffer.get(0)), buffer.get(0x1), buffer.get(0x2) & 0xFF,
                buffer.get(0x3) & 0xFF, new Position(buffer.getFloat(0x4), buffer.getFloat(0x8), buffer.getFloat(0xC)),
                buffer.get(0x10) & 0xFF);
    }

    // Returns an IntBuffer of four pointers, each representing a script's address.
    public static int getBattleConfigurationAddress(int battle) throws IOException {
        return Utils.seekDolRaf(BATTLE_CONFIGURATION_START_ADDRESS + battle * 4, new byte[4]).asIntBuffer().get();
    }

    public static Battle readBattle(int startAddress) throws IOException {
        ByteBuffer buffer = Utils.seekDolRaf(startAddress, new byte[48]);
        return new Battle(Arrays.asList(getOpponent(buffer.getShort(0x0)), getOpponent(buffer.getShort(0x2)),
                getOpponent(buffer.getShort(0x4)), getOpponent(buffer.getShort(0x6)),
                getOpponent(buffer.getShort(0x8))),
                new Position(buffer.getFloat(0xC), buffer.getFloat(0x10), buffer.getFloat(0x14)),
                new Position(buffer.getFloat(0x18), buffer.getFloat(0x1C), buffer.getFloat(0x20)),
                buffer.get(0x24) & 0xFF, buffer.get(0x25) & 0xFF, buffer.getShort(0x26) & 0xFFFF,
                buffer.getShort(0x28) & 0xFFFF, buffer.get(0x2A) & 0xFF, buffer.get(0x2B) & 0xFF,
                buffer.get(0x2C) & 0xFF, buffer.get(0x2D) & 0xFF, buffer.get(0x2E) & 0xFF);
    }

    // Returns an IntBuffer of seven words, each representing start addresses for
    // elements of the Battle.
    private static IntBuffer getStoryBattleAddresses(int battle) throws IOException {
        return Utils.seekDolRaf(BATTLE_ADDRESSES_START_ADDRESS + battle * 28, new byte[28]).asIntBuffer();
    }

    public static int getStoryBattleConfigurationAddress(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(0);
    }

    public static int getStoryBattleAllyBorgListOverride(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(1);
    }

    public static int getStoryBattleUnlockAddress(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(2);
    }

    public static int getStoryBattleUnlistAddress(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(3);
    }

    public static int getStoryBattleBeforeCutsceneAddress(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(4);
    }

    public static int getStoryBattleWinCutsceneAddress(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(5);
    }

    public static int getStoryBattleLoseCutsceneAddress(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(6);
    }

    // public static void readBattleConfiguration(int startAddress) throws
    // IOException {
    // byte[] magic = new byte[18];
    // Utils.seekRaf(startAddress, magic);
    // ByteBuffer buffer = ByteBuffer.wrap(magic);
    // MapLocation mapLocation = MapLocation.get(buffer.get(0x0));
    // int battleLocation = buffer.get(0x1); //TODO: convert to enum value
    // int pillarFormation = buffer.get(0x2);
    // BattleDifficulty difficulty = BattleDifficulty.get(buffer.get(0x3));
    // int mapScene = buffer.getShort(0x4); //TODO: convert to enum value
    // String battleName = mapLocation.getBattleNames()[buffer.get(0x6)];
    // BitSet playAllowed = new BitSet(buffer.get(0x7) & 0xFF);
    // int gfEnergyOverride = buffer.getInt(0x8);
    // int gRedHint = buffer.get(0xC); //TODO: convert to enum value
    // MapObjective objective = MapObjective.get(buffer.get(0xD));
    // int allyOption = buffer.get(0xE); //TODO: convert to enum value
    // Commander allyCommander = Commander.get(buffer.get(0xF));
    // int allyIntelligence = buffer.get(0x10);
    // Commander enemyCommander1 = Commander.get(buffer.get(0x11));
    // Commander enemyCommander2 = Commander.get(buffer.get(0x12));
    // }

    // Returns the Battle name, along with the Map location.
    public static List<String> getMissionStringIdentifiers(int startAddress) throws IOException {
        ByteBuffer byteBuffer = Utils.seekDolRaf(startAddress, new byte[20]);
        MapLocation mapLocation = MapLocation.get(byteBuffer.get(0));
        String battle = mapLocation.getBattleNames()[byteBuffer.get(6)];
        return Arrays.asList(battle, mapLocation.getName());
    }

    public static List<String> getMissionStringIdentifiersByIndex(int index) throws IOException {
        return getMissionStringIdentifiers(getStoryBattleAddresses(index).get(0));
    }

    // Returns an IntBuffer of four words, each representing a script's address.
    public static IntBuffer getBattleScriptAddresses(int battle) throws IOException {
        return Utils.seekDolRaf(BATTLE_SCRIPTS_START_ADDRESS + battle * 16, new byte[16]).asIntBuffer();
    }

    public static List<MissionEvent> readBattleScript(int startAddress) throws IOException {
        RandomAccessFile raf = Utils.getDolRaf();
        List<MissionEvent> missionEvents = new ArrayList<>();
        byte[] magic = new byte[32];

        while (true) {
            ByteBuffer byteBuffer = Utils.seekDolRaf(raf, startAddress, magic);
            if (byteBuffer.getShort(0) == (short) 0xffff || byteBuffer.getShort(0) == 0x7fff)
                break;
            int timer1 = byteBuffer.getShort(0x0) & 0xffff;
            int timer2 = byteBuffer.getShort(0x2) & 0xffff;
            int slot1 = byteBuffer.getShort(0x4) & 0xffff;
            int slot2 = byteBuffer.getShort(0x6) & 0xffff;
            int eventType = byteBuffer.get(0xa) & 0xff;
            startAddress += magic.length;
            switch (eventType) {
                case 0x70:
                case 0x71:
                    continue;
                case 0x72: {
                    missionEvents.add(new MusicEvent(timer1, timer2, slot1, slot2, Music.get(byteBuffer.get(0xb))));
                    break;
                }
                case 0x73: {
                    missionEvents.add(new FocusEvent(timer1, timer2, slot1, slot2,
                            BorgSpecies.getBorgSpecies(byteBuffer.getShort(0x8) & 0xffff), byteBuffer.get(0xb) == 1,
                            byteBuffer.get(0x10), byteBuffer.getFloat(0x14), byteBuffer.getFloat(0x18)));
                    break;
                }
                case 0x74: {
                    missionEvents.add(
                            new SpeechEvent(timer1, timer2, slot1, slot2, Commander.get(byteBuffer.get(0xb) & 0xff),
                                    byteBuffer.get(0xc) & 0xff, byteBuffer.get(0xd) == 1, byteBuffer.get(0xe) == 1));
                    break;
                }
                case 0x75:
                case 0x76: {
                    missionEvents.add(new VoiceEvent(timer1, timer2, slot1, slot2,
                            Commander.get(byteBuffer.get(0xb) & 0xff), true));
                    break;
                }
                default: {
                    missionEvents.add(new SpawnEvent(timer1, timer2, slot1, slot2,
                            byteBuffer.getShort(0x8) & 0xffff, eventType, Commander.get(byteBuffer.get(0xb) & 0xff),
                            byteBuffer.get(0xc) >> 4, byteBuffer.get(0xc) & 0xf, byteBuffer.get(0xd) & 0xff,
                            byteBuffer.get(0xe) & 0xff, byteBuffer.get(0xf) == 1, byteBuffer.get(0x10) == 1,
                            byteBuffer.get(0x11) & 0xf8, byteBuffer.get(0x11) & 0x3, new Position(
                                    byteBuffer.getFloat(0x14), byteBuffer.getFloat(0x18), byteBuffer.getFloat(0x1c))));
                    break;
                }
            }
        }
        return missionEvents;
    }

    public static void addStory(int index, int configurationAddress, List<Integer> scriptAddresses) throws IOException {
        RandomAccessFile raf = Utils.getDolRaf();

        // Determine the current size of the file
        long fileSize = raf.length();

        // Calculate the write offsets for the new data
        long configOffset = BATTLE_CONFIGURATION_START_ADDRESS + index * 4;
        long scriptsOffset = BATTLE_SCRIPTS_START_ADDRESS + index * 16;

        // Move the existing data after the write offsets
        moveData(raf, configOffset, fileSize, configurationAddress);
        moveData(raf, scriptsOffset, fileSize, scriptAddresses.toArray(new Integer[0]));

        // Write the new data at the write offsets
        raf.seek(configOffset);
        byte[] configAddressBytes = ByteBuffer.allocate(4).putInt(configurationAddress).array();
        raf.write(configAddressBytes);

        raf.seek(scriptsOffset);
        for (int i = 0; i < scriptAddresses.size(); i++) {
            byte[] scriptAddressBytes = ByteBuffer.allocate(4).putInt(scriptAddresses.get(i)).array();
            raf.write(scriptAddressBytes);
        }
    }

    private static void moveData(RandomAccessFile raf, long sourceOffset, long fileSize, Integer... newValues)
            throws IOException {
        // Move the data after sourceOffset to the end of the file
        byte[] buffer = new byte[1024];
        long bytesRead;
        long bytesToMove = fileSize - sourceOffset;

        while (bytesToMove > 0) {
            int bytesToRead = (int) Math.min(buffer.length, bytesToMove);
            raf.seek(sourceOffset);
            bytesRead = raf.read(buffer, 0, bytesToRead);
            raf.seek(fileSize + bytesRead);
            raf.write(buffer, 0, (int) bytesRead);
            sourceOffset += bytesRead;
            bytesToMove -= bytesRead;
        }

        // Write the new values at sourceOffset
        raf.seek(sourceOffset);
        for (int i = 0; i < newValues.length; i++) {
            byte[] valueBytes = ByteBuffer.allocate(4).putInt(newValues[i]).array();
            raf.write(valueBytes);
        }
    }
}