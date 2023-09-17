package com.crystalpixel.neogfutils.utils.story;

import javafx.geometry.Point3D;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.crystalpixel.neogfutils.battle.Commander;
import com.crystalpixel.neogfutils.battle.MapLocation;
import com.crystalpixel.neogfutils.battle.Opponent;
import com.crystalpixel.neogfutils.game.*;
import com.crystalpixel.neogfutils.scriptevents.*;
import com.crystalpixel.neogfutils.system.BattleConfiguration;
import com.crystalpixel.neogfutils.system.BorgSpecies;
import com.crystalpixel.neogfutils.utils.Utils;

public class StoryUtils {

    private static final int BATTLE_ADDRESSES_START_ADDRESS = 0x8035fa04;
    private static final int BATTLE_SCRIPTS_START_ADDRESS = 0x803832c8;
    private static final int BATTLE_CONFIGURATION_START_ADDRESS = 0x803864c8;
    private static final int OPPONENT_START_ADDRESS = 0x803867e8;

    public static Opponent getOpponent(int index) throws IOException {
        if (index == -1) return null;
        ByteBuffer buffer = Utils.seekRaf(OPPONENT_START_ADDRESS + index * 20, new byte[20]);
        Opponent opponent = new Opponent(
            Commander.get(buffer.get(0)),
            buffer.get(0x1),
            buffer.get(0x2) & 0xFF,
            buffer.get(0x3) & 0xFF,
            new Point3D(buffer.getFloat(0x4), buffer.getFloat(0x8), buffer.getFloat(0xC)),
            buffer.get(0x10) & 0xFF
        );
        
        return opponent;
    }

    // Returns an IntBuffer of four pointers, each representing a script's address.
    public static int getBattleConfigurationAddress(int battle) throws IOException {
        return Utils.seekRaf(BATTLE_CONFIGURATION_START_ADDRESS + battle * 4, new byte[4]).asIntBuffer().get();
    }

    public static BattleConfiguration readBattleConfiguration(int startAddress) throws IOException {
        ByteBuffer buffer = Utils.seekRaf(startAddress, new byte[48]);
        BattleConfiguration battleConfiguration = new BattleConfiguration();
        for (int i = 0; i < 0xa; i+=2) {
            battleConfiguration.getOpponents().add(getOpponent(buffer.getShort(i)));
        }
        battleConfiguration.setPlayerCoordinates(new Point3D(buffer.getFloat(0xC), buffer.getFloat(0x10), buffer.getFloat(0x14)));
        battleConfiguration.setAllyCoordinates(new Point3D(buffer.getFloat(0x18), buffer.getFloat(0x1C), buffer.getFloat(0x20)));
        battleConfiguration.setPlayerEntrance(buffer.get(0x24) & 0xFF);
        battleConfiguration.setAllyEntrance(buffer.get(0x25) & 0xFF);
        battleConfiguration.setTimer(buffer.getShort(0x26) & 0xFFFF);
        battleConfiguration.setBattleOptions(buffer.getShort(0x28) & 0xFFFF); //TODO: turn into a list
        battleConfiguration.setGreenScore(buffer.get(0x2A) & 0xFF);
        battleConfiguration.setRedScore(buffer.get(0x2B) & 0xFF);
        battleConfiguration.setGreenHandicap(buffer.get(0x2C) & 0xFF);
        battleConfiguration.setRedHandicap(buffer.get(0x2D) & 0xFF);
        battleConfiguration.setMusic(buffer.get(0x2E) & 0xFF); //TODO: convert to enum
        return battleConfiguration;
    }

    // Returns an IntBuffer of seven words, each representing start addresses for elements of the Battle.
    private static IntBuffer getStoryBattleAddresses(int battle) throws IOException {
        return Utils.seekRaf(BATTLE_ADDRESSES_START_ADDRESS + battle * 28, new byte[28]).asIntBuffer();
    }

    private static int getStoryBattleConfigurationAddress(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(0);
    }

    private static int getStoryBattleAllyBorgListOverride(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(1);
    }

    private static int getStoryBattleUnlockAddress(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(2);
    }

    private static int getStoryBattleUnlistAddress(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(3);
    }

    private static int getStoryBattleBeforeCutsceneAddress(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(4);
    }

    private static int getStoryBattleWinCutsceneAddress(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(5);
    }

    private static int getStoryBattleLoseCutsceneAddress(int battle) throws IOException {
        return getStoryBattleAddresses(battle).get(6);
    }

//    public static void readBattleConfiguration(int startAddress) throws IOException {
//        byte[] magic = new byte[18];
//        Utils.seekRaf(startAddress, magic);
//        ByteBuffer buffer = ByteBuffer.wrap(magic);
//        MapLocation mapLocation = MapLocation.get(buffer.get(0x0));
//        int battleLocation = buffer.get(0x1); //TODO: convert to enum value
//        int pillarFormation = buffer.get(0x2);
//        BattleDifficulty difficulty = BattleDifficulty.get(buffer.get(0x3));
//        int mapScene = buffer.getShort(0x4); //TODO: convert to enum value
//        String battleName = mapLocation.getBattleNames()[buffer.get(0x6)];
//        BitSet playAllowed = new BitSet(buffer.get(0x7) & 0xFF);
//        int gfEnergyOverride = buffer.getInt(0x8);
//        int gRedHint = buffer.get(0xC); //TODO: convert to enum value
//        MapObjective objective = MapObjective.get(buffer.get(0xD));
//        int allyOption = buffer.get(0xE); //TODO: convert to enum value
//        Commander allyCommander = Commander.get(buffer.get(0xF));
//        int allyIntelligence = buffer.get(0x10);
//        Commander enemyCommander1 = Commander.get(buffer.get(0x11));
//        Commander enemyCommander2 = Commander.get(buffer.get(0x12));
//    }

    // Returns the Battle name, along with the Map location.
    public static List<String> getMissionStringIdentifiers(int startAddress) throws IOException {
        ByteBuffer byteBuffer = Utils.seekRaf(startAddress, new byte[20]);
        MapLocation mapLocation = MapLocation.get(byteBuffer.get(0));
        String battle = mapLocation.getBattleNames()[byteBuffer.get(6)];
        return Arrays.asList(battle, mapLocation.getName());
    }

    public static List<String> getMissionStringIdentifiersByIndex(int index) throws IOException {
        return getMissionStringIdentifiers(getStoryBattleAddresses(index).get(0));
    }

    // Returns an IntBuffer of four words, each representing a script's address.
    public static IntBuffer getBattleScriptAddresses(int battle) throws IOException {
        return Utils.seekRaf(BATTLE_SCRIPTS_START_ADDRESS + battle * 16, new byte[16]).asIntBuffer();
    }

    // Returns a List of ScriptEvents for a given script address.
    public static List<ScriptEvent> readBattleScript(int startAddress) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        List<ScriptEvent> scriptEvents = new ArrayList<>();
        byte[] magic = new byte[32];

        while (true) {
            ByteBuffer byteBuffer = Utils.seekRaf(raf, startAddress, magic);
            if (byteBuffer.getShort(0) == (short) 0xffff || byteBuffer.getShort(0) == 0x7fff) break;
            int timer1 = byteBuffer.getShort(0x0) & 0xffff;
            int timer2 = byteBuffer.getShort(0x2) & 0xffff;
            int slot1 = byteBuffer.getShort(0x4) & 0xffff;
            int slot2 = byteBuffer.getShort(0x6) & 0xffff;
            int eventType = byteBuffer.get(0xa) & 0xff;
            startAddress += magic.length;
            ScriptEvent scriptEvent;
            switch (eventType) {
                case 0x70:
                case 0x71: continue;
                case 0x72: {
                    Music music = Music.get(byteBuffer.get(0xb));
                    scriptEvent = new MusicEvent(timer1, timer2, slot1, slot2);
                    ((MusicEvent) scriptEvent).setMusic(music);
                    break;
                }
                case 0x73: {
                    BorgSpecies borgSpecies = BorgSpecies.getBorgSpecies(byteBuffer.getShort(0x8) & 0xffff);
                    boolean pause = byteBuffer.get(0xb) == 1;
                    int joint = byteBuffer.get(0x10);
                    float distance = byteBuffer.getFloat(0x14);
                    float duration = byteBuffer.getFloat(0x18);
                    scriptEvent = new FocusEvent(timer1, timer2, slot1, slot2);
                    ((FocusEvent) scriptEvent).setBorgSpecies(borgSpecies);
                    ((FocusEvent) scriptEvent).setPause(pause);
                    ((FocusEvent) scriptEvent).setJoint(joint);
                    ((FocusEvent) scriptEvent).setDistance(distance);
                    ((FocusEvent) scriptEvent).setDuration(duration);
                    break;
                }
                case 0x74: {
                    Commander commander = Commander.get(byteBuffer.get(0xb));
                    int voice = byteBuffer.get(0xc) & 0xff;
                    boolean visible = byteBuffer.get(0xd) == 1;
                    boolean queue = byteBuffer.get(0xe) == 1;
                    scriptEvent = new SpeechEvent(timer1, timer2, slot1, slot2);
                    ((SpeechEvent) scriptEvent).setCommander(commander);
                    ((SpeechEvent) scriptEvent).setSound(voice);
                    ((SpeechEvent) scriptEvent).setVisible(visible);
                    ((SpeechEvent) scriptEvent).setQueue(queue);
                    break;
                }
                case 0x75:
                case 0x76: {
                    Commander commander = Commander.get(byteBuffer.get(0xb) & 0xff);
                    boolean mute = eventType == 0x76;
                    scriptEvent = new VoiceEvent(timer1, timer2, slot1, slot2);
                    ((VoiceEvent) scriptEvent).setCommander(commander);
                    ((VoiceEvent) scriptEvent).setMute(mute);
                    break;
                }
                default: {
                    int id = byteBuffer.getShort(0x8) & 0xffff;
                    Commander commander = Commander.get(byteBuffer.get(0xb) & 0xff);
                    int voiceListIndex = byteBuffer.get(0xc) >> 4;
                    int activeness = byteBuffer.get(0xc) & 0xf;
                    int stationary = byteBuffer.get(0xd) & 0xff;
                    int intelligence = byteBuffer.get(0xe) & 0xff;
                    boolean channel = byteBuffer.get(0xf) == 1;
                    boolean boss = byteBuffer.get(0x10) == 1;
                    int rotation = byteBuffer.get(0x11) & 0xf8;
                    int entrance = byteBuffer.get(0x11) & 0x3;
                    float x = byteBuffer.getFloat(0x14);
                    float y = byteBuffer.getFloat(0x18);
                    float z = byteBuffer.getFloat(0x1c);
                    scriptEvent = new SpawnEvent(timer1, timer2, slot1, slot2);
                    ((SpawnEvent) scriptEvent).setId(id);
                    ((SpawnEvent) scriptEvent).setLevel(eventType);
                    ((SpawnEvent) scriptEvent).setCommander(commander);
                    ((SpawnEvent) scriptEvent).setVoiceListIndex(voiceListIndex);
                    ((SpawnEvent) scriptEvent).setActiveness(activeness);
                    ((SpawnEvent) scriptEvent).setStationary(stationary);
                    ((SpawnEvent) scriptEvent).setIntelligence(intelligence);
                    ((SpawnEvent) scriptEvent).setChannelBoolean(channel);
                    ((SpawnEvent) scriptEvent).setBoss(boss);
                    ((SpawnEvent) scriptEvent).setRotation(rotation);
                    ((SpawnEvent) scriptEvent).setEntrance(entrance);
                    ((SpawnEvent) scriptEvent).setPosition(new Point3D(x, y, z));
                }
            }
            scriptEvents.add(scriptEvent);
        }
        return scriptEvents;
    }

    public static void addStory(int index, int configurationAddress, List<Integer> scriptAddresses) throws IOException {
        RandomAccessFile raf = Utils.getRaf();

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

    private static void moveData(RandomAccessFile raf, long sourceOffset, long fileSize, Integer... newValues) throws IOException {
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