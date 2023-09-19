package com.crystalpixel.neogfutils.event;

import javafx.geometry.Point3D;

import java.nio.ByteBuffer;

import com.crystalpixel.neogfutils.battle.Commander;

public class SpawnEvent extends Event {

    private int id;
    private int level;
    private Commander commander;
    private int voiceListIndex;
    private int activeness;
    private int stationary;
    private int intelligence;
    private boolean channelBoolean;
    private boolean boss;
    private int rotation;
    private int entrance;
    private Point3D position;

    public SpawnEvent(int timer1, int timer2, int slot1, int slot2) {
        super(timer1, timer2, slot1, slot2);
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

    public int getActiveness() {
        return activeness;
    }

    public void setActiveness(int activeness) {
        this.activeness = activeness;
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

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    @Override
    public ByteBuffer getAsBytes() {
        ByteBuffer buffer = super.getAsBytes();
        buffer.putShort(0x8, (short) getId());
        buffer.put(0xa, (byte) getLevel());
        buffer.put(0xb, (byte) getCommander().ordinal());
        buffer.put(0xc, (byte) (getActiveness() | getVoiceListIndex() << 4));
        buffer.put(0xd, (byte) getStationary());
        buffer.put(0xe, (byte) getIntelligence());
        buffer.put(0xf, (byte) (isChannelBoolean() ? 1 : 0));
        buffer.put(0x10, (byte) (isBoss() ? 1 : 0));
        buffer.put(0x11, (byte) (getRotation() | getEntrance()));
        buffer.putFloat(0x14, (float) getPosition().getX());
        buffer.putFloat(0x18, (float) getPosition().getY());
        buffer.putFloat(0x1c, (float) getPosition().getZ());
        return buffer;
    }
}
