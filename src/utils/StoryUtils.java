package utils;

import enums.*;
import javafx.geometry.Point3D;
import scriptevents.*;
import system.BattleConfiguration;
import system.BorgSpecies;
import structures.Opponent;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StoryUtils {

    private static final int BATTLE_ADDRESSES_START_ADDRESS = 0x35fa04;
    private static final int BATTLE_SCRIPTS_START_ADDRESS = 0x3832c8;
    private static final int BATTLE_CONFIGURATION_START_ADDRESS = 0x3864c8;
    private static final int OPPONENT_START_ADDRESS = 0x3867e8;

    public static Opponent getOpponent(int index) throws IOException {
        if (index == -1) return null;
        RandomAccessFile raf = Utils.getRaf();
        raf.seek(OPPONENT_START_ADDRESS + index * 20);
        byte[] magic = new byte[20];
        raf.readFully(magic);
        ByteBuffer buffer = ByteBuffer.wrap(magic);
        Opponent opponent = new Opponent();
        opponent.setCommander(Commander.get(buffer.get(0)));
        opponent.setDifficulty(buffer.get(0x1));
        opponent.setBorgListId(buffer.get(0x2) & 0xFF);
        opponent.setLevel(buffer.get(0x3) & 0xFF);
        opponent.setCoordinates(new Point3D(buffer.getFloat(0x4), buffer.getFloat(0x8), buffer.getFloat(0xC)));
        opponent.setEntrance(buffer.get(0x10) & 0xFF);
        return opponent;
    }

    // Returns an IntBuffer of four words, each representing a script's address.
    public static int getBattleConfigurationAddress(int battle) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        raf.seek(BATTLE_CONFIGURATION_START_ADDRESS + battle * 4);
        byte[] magic = new byte[4];
        raf.readFully(magic);
        return ByteBuffer.wrap(magic).asIntBuffer().get();
    }

    public static BattleConfiguration getBattleConfiguration(int startAddress) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        raf.seek(startAddress & 0xFFFFFF);
        byte[] magic = new byte[48];
        raf.readFully(magic);
        ByteBuffer buffer = ByteBuffer.wrap(magic);
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
        RandomAccessFile raf = Utils.getRaf();
        raf.seek(BATTLE_ADDRESSES_START_ADDRESS + battle * 28);
        byte[] magic = new byte[28];
        raf.readFully(magic);
        return ByteBuffer.wrap(magic).asIntBuffer();
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

    public static void readBattleConfiguration(int startAddress) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        raf.seek(startAddress);
        byte[] magic = new byte[18];
        raf.readFully(magic);
        ByteBuffer buffer = ByteBuffer.wrap(magic);
        MapLocation mapLocation = MapLocation.get(buffer.get(0x0));
        int battleLocation = buffer.get(0x1); //TODO: convert to enum value
        int pillarFormation = buffer.get(0x2);
        BattleDifficulty difficulty = BattleDifficulty.get(buffer.get(0x3));
        int mapScene = buffer.getShort(0x4); //TODO: convert to enum value
        String battleName = mapLocation.getBattleNames()[buffer.get(0x6)];
        BitSet playAllowed = new BitSet(buffer.get(0x7) & 0xFF);
        int gfEnergyOverride = buffer.getInt(0x8);
        int gRedHint = buffer.get(0xC); //TODO: convert to enum value
        MapObjective objective = MapObjective.get(buffer.get(0xD));
        int allyOption = buffer.get(0xE); //TODO: convert to enum value
        Commander allyCommander = Commander.get(buffer.get(0xF));
        int allyIntelligence = buffer.get(0x10);
        Commander enemyCommander1 = Commander.get(buffer.get(0x11));
        Commander enemyCommander2 = Commander.get(buffer.get(0x12));
    }

    // Returns the Battle name, along with the Map location.
    public static List<String> getMissionStringIdentifiers(int startAddress) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        raf.seek(startAddress & 0x00FFFFFF);
        byte[] magic = new byte[19];
        raf.readFully(magic);
        ByteBuffer byteBuffer = ByteBuffer.wrap(magic);
        MapLocation mapLocation = MapLocation.get(byteBuffer.get(0));
        String battle = mapLocation.getBattleNames()[byteBuffer.get(6)];
        return Arrays.asList(battle, mapLocation.getName());
    }

    public static List<String> getMissionStringIdentifiersByIndex(int index) throws IOException {
        return getMissionStringIdentifiers(getStoryBattleAddresses(index).get(0));
    }

    // Returns an IntBuffer of four words, each representing a script's address.
    public static IntBuffer getBattleScriptAddresses(int battle) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        raf.seek(BATTLE_SCRIPTS_START_ADDRESS + battle * 16);
        byte[] magic = new byte[16];
        raf.readFully(magic);
        return ByteBuffer.wrap(magic).asIntBuffer();
    }

    // Returns an List of ScriptEvents for a given script address.
    public static List<ScriptEvent> readBattleScript(int startAddress) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        raf.seek(startAddress & 0xFFFFFF);
        List<ScriptEvent> scriptEvents = new ArrayList<>();

        while (true) {
            byte[] magic = new byte[32];
            raf.readFully(magic);
            ByteBuffer byteBuffer = ByteBuffer.wrap(magic);
            if (byteBuffer.getShort(0) == (short) 0xFFFF || byteBuffer.getShort(0) == 0x7FFF) break;
            int timer1 = byteBuffer.getShort(0) & 0xFFFF;
            int timer2 = byteBuffer.getShort(2) & 0xFFFF;
            int slot1 = byteBuffer.getShort(4) & 0xFFFF;
            int slot2 = byteBuffer.getShort(6) & 0xFFFF;
            int eventType = byteBuffer.get(10) & 0xFF;
            ScriptEvent scriptEvent;
            switch (eventType) {
                case 112:
                case 113: continue;
                case 114: {
                    Music music = Music.get(byteBuffer.get(11) & 0xFF);
                    scriptEvent = new MusicEvent(timer1, timer2, slot1, slot2);
                    ((MusicEvent) scriptEvent).setMusic(music);
                    break;
                }
                case 115: {
                    BorgSpecies borgSpecies = BorgSpecies.getBorgSpecies(byteBuffer.getShort(8) & 0xFFFF);
                    boolean pause = byteBuffer.get(11) == 1;
                    int focalPoint = byteBuffer.get(16) & 0xFF;
                    float distance = byteBuffer.getFloat(20);
                    float duration = byteBuffer.getFloat(24);
                    scriptEvent = new FocusEvent(timer1, timer2, slot1, slot2);
                    ((FocusEvent) scriptEvent).setBorgSpecies(borgSpecies);
                    ((FocusEvent) scriptEvent).setPause(pause);
                    ((FocusEvent) scriptEvent).setFocalPoint(focalPoint);
                    ((FocusEvent) scriptEvent).setDistance(distance);
                    ((FocusEvent) scriptEvent).setDuration(duration);
                    break;
                }
                case 116: {
                    Commander commander = Commander.get(byteBuffer.get(11) & 0xFF);
                    int voice = byteBuffer.get(12) & 0xFF;
                    boolean visible = byteBuffer.get(13) == 1;
                    boolean wait = byteBuffer.get(14) == 1;
                    scriptEvent = new SpeechEvent(timer1, timer2, slot1, slot2);
                    ((SpeechEvent) scriptEvent).setCommander(commander);
                    ((SpeechEvent) scriptEvent).setSound(voice);
                    ((SpeechEvent) scriptEvent).setVisible(visible);
                    ((SpeechEvent) scriptEvent).setWait(wait);
                    break;
                }
                case 117:
                case 118: {
                    Commander commander = Commander.get(byteBuffer.get(11) & 0xFF);
                    boolean mute = eventType == 118;
                    scriptEvent = new VoiceEvent(timer1, timer2, slot1, slot2);
                    ((VoiceEvent) scriptEvent).setCommander(commander);
                    ((VoiceEvent) scriptEvent).setMute(mute);
                    break;
                }
                default: {
                    int borgId = byteBuffer.getShort(8) & 0xFFFF;
                    Commander commander = Commander.get(byteBuffer.get(11) & 0xFF);
                    int aggressiveness = byteBuffer.get(12);
                    int stationary = byteBuffer.get(13);
                    int intelligence = byteBuffer.get(14) & 0xFF;
                    boolean primary = byteBuffer.get(15) == 1;
                    boolean flicker = byteBuffer.get(16) == 1;
                    int entrance = byteBuffer.get(17) & 0xFF;
                    float x = byteBuffer.getFloat(20);
                    float y = byteBuffer.getFloat(24);
                    float z = byteBuffer.getFloat(28);
                    scriptEvent = new BorgEvent(timer1, timer2, slot1, slot2);
                    ((BorgEvent) scriptEvent).setId(borgId);
                    ((BorgEvent) scriptEvent).setLevel(eventType);
                    ((BorgEvent) scriptEvent).setCommander(commander);
                    ((BorgEvent) scriptEvent).setAggressiveness(aggressiveness);
                    ((BorgEvent) scriptEvent).setStationary(stationary);
                    ((BorgEvent) scriptEvent).setIntelligence(intelligence);
                    ((BorgEvent) scriptEvent).setChannelBoolean(primary);
                    ((BorgEvent) scriptEvent).setBoss(flicker);
                    ((BorgEvent) scriptEvent).setEntrance(entrance);
                    ((BorgEvent) scriptEvent).setX(x);
                    ((BorgEvent) scriptEvent).setY(y);
                    ((BorgEvent) scriptEvent).setZ(z);
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
