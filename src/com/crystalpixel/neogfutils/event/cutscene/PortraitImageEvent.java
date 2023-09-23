package com.crystalpixel.neogfutils.event.cutscene;

import java.nio.ByteBuffer;

import com.crystalpixel.neogfutils.battle.entity.Commander;

public class PortraitImageEvent implements GameEvent {

    private int slot;
    private Commander commander;

    public PortraitImageEvent(int slot, Commander commander) {
        this.slot = slot;
        this.commander = commander;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Commander getCommander() {
        return commander;
    }

    public void setCommander(Commander commander) {
        this.commander = commander;
    }

    @Override
    public ByteBuffer getAsBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        buffer.putShort(0, (short) 0x218);
        buffer.putShort(2, (short) slot);
        buffer.putShort(4, (short) commander.ordinal());
        return buffer;
    }
}