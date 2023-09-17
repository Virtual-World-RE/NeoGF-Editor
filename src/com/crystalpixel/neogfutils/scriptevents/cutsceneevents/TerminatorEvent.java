package com.crystalpixel.neogfutils.scriptevents.cutsceneevents;

import java.nio.ByteBuffer;

public class TerminatorEvent implements GameEvent {

    @Override
    public ByteBuffer getAsBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort(0, (short) 0x2e);
        return buffer;
    }
}