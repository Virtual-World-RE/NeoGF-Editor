package com.crystalpixel.neogfutils.event;

import java.nio.ByteBuffer;

import com.crystalpixel.neogfutils.annotation.NotNull;
import com.crystalpixel.neogfutils.battle.entity.Commander;
import com.crystalpixel.neogfutils.battle.entity.Position;

public class SpawnEvent extends MissionEvent {

    private int id;
    private int level;
    private Commander commander;
    private int voiceListIndex;
    private int difficulty;
    private int stationary;
    private int intelligence;
    private boolean channelBoolean;
    private boolean boss;
    private int rotation;
    private int entrance;
    private Position position;

    public SpawnEvent(int timer1, int timer2, int slot1, int slot2) {
        super(timer1, timer2, slot1, slot2);
    }

    public SpawnEvent(int timer1, int timer2, int slot1, int slot2, int id, int level, Commander commander,
            int voiceListIndex, int difficulty, int stationary, int intelligence, boolean channelBoolean,
            boolean boss, int rotation, int entrance, Position position) {
        super(timer1, timer2, slot1, slot2);
        this.id = id;
        this.level = level;
        this.commander = commander;
        this.voiceListIndex = voiceListIndex;
        this.difficulty = difficulty;
        this.stationary = stationary;
        this.intelligence = intelligence;
        this.channelBoolean = channelBoolean;
        this.boss = boss;
        this.rotation = rotation;
        this.entrance = entrance;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Commander getCommander() {
        return commander;
    }

    public void setCommander(Commander commander) {
        this.commander = commander;
    }

    public int getVoiceListIndex() {
        return voiceListIndex;
    }

    public void setVoiceListIndex(int voiceListIndex) {
        this.voiceListIndex = voiceListIndex;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getStationary() {
        return stationary;
    }

    public void setStationary(int stationary) {
        this.stationary = stationary;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public boolean isChannelBoolean() {
        return channelBoolean;
    }

    public void setChannelBoolean(boolean channelBoolean) {
        this.channelBoolean = channelBoolean;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    @NotNull
    public byte[] getAsBytes() {
        ByteBuffer buffer = ByteBuffer.wrap(super.getAsBytes());
        buffer.putShort(0x8, (short) getId());
        buffer.put(0xa, (byte) getLevel());
        buffer.put(0xb, (byte) getCommander().ordinal());
        buffer.put(0xc, (byte) (getDifficulty() | getVoiceListIndex() << 4));
        buffer.put(0xd, (byte) getStationary());
        buffer.put(0xe, (byte) getIntelligence());
        buffer.put(0xf, (byte) (isChannelBoolean() ? 1 : 0));
        buffer.put(0x10, (byte) (isBoss() ? 1 : 0));
        buffer.put(0x11, (byte) (getRotation() | getEntrance()));
        buffer.putFloat(0x14, (float) getPosition().getX());
        buffer.putFloat(0x18, (float) getPosition().getY());
        buffer.putFloat(0x1c, (float) getPosition().getZ());
        return buffer.array();
    }
}
