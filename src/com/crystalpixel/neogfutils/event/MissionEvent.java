package com.crystalpixel.neogfutils.event;

import com.crystalpixel.neogfutils.annotation.NotNull;
import com.crystalpixel.neogfutils.system.Serializable;

import java.nio.ByteBuffer;

public abstract class MissionEvent implements Serializable {

    public static final int ALLOCATION = 0x20;

    private int timer1;
    private int timer2;
    private int slot1;
    private int slot2;

    public MissionEvent(int timer1, int timer2, int slot1, int slot2) {
        this.timer1 = timer1;
        this.timer2 = timer2;
        this.slot1 = slot1;
        this.slot2 = slot2;
    }

    public int getTimer1() {
        return timer1;
    }

    public void setTimer1(int timer1) {
        this.timer1 = timer1;
    }

    public int getTimer2() {
        return timer2;
    }

    public void setTimer2(int timer2) {
        this.timer2 = timer2;
    }

    public int getSlot1() {
        return slot1;
    }

    public void setSlot1(int slot1) {
        this.slot1 = slot1;
    }

    public int getSlot2() {
        return slot2;
    }

    public void setSlot2(int slot2) {
        this.slot2 = slot2;
    }

    @Override
    public int getAllocation() {
        return ALLOCATION;
    }

    @Override
    @NotNull
    public byte[] getAsBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(getAllocation());
        buffer.putShort(0x0, ((short) getTimer1()));
        buffer.putShort(0x2, ((short) getTimer2()));
        buffer.putShort(0x4, ((short) getSlot1()));
        buffer.putShort(0x6, ((short) getSlot2()));
        return buffer.array();
    }
}
