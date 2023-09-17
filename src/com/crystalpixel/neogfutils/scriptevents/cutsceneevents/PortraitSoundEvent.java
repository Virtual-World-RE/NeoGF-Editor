package com.crystalpixel.neogfutils.scriptevents.cutsceneevents;

import java.nio.ByteBuffer;

import com.crystalpixel.neogfutils.battle.Commander;

public class PortraitSoundEvent implements GameEvent {

    private Commander commander;
    private int sound;

    public PortraitSoundEvent(Commander commander, int sound) {
        this.commander = commander;
        this.sound = sound;
    }

    public Commander getCommander() {
        return commander;
    }

    public void setCommander(Commander commander) {
        this.commander = commander;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    @Override
    public ByteBuffer getAsBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        buffer.putShort(0, (short) 0x220);
        buffer.putShort(2, (short) commander.ordinal());
        buffer.putShort(4, (short) sound);
        return buffer;
    }
}