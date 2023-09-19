package com.crystalpixel.neogfutils.event.cutscene;

import java.nio.ByteBuffer;

public class InputNameEvent implements GameEvent {

    @Override
    public ByteBuffer getAsBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort(0, (short) 0x11f);
        return buffer;
    }
}