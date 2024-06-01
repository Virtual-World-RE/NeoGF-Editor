package com.crystalpixel.neogfutils.event;

import com.crystalpixel.neogfutils.annotation.NotNull;
import com.crystalpixel.neogfutils.game.Music;

import java.nio.ByteBuffer;

public class BlankEvent extends MissionEvent {

    public BlankEvent(int timer1, int timer2, int slot1, int slot2) {
        super(timer1, timer2, slot1, slot2);
    }

    @Override
    @NotNull
    public byte[] getAsBytes() {
        ByteBuffer buffer = ByteBuffer.wrap(super.getAsBytes());
        buffer.put(0xa, ((byte) 0x71));
        return buffer.array();
    }
}