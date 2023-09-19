package com.crystalpixel.neogfutils.system;

import java.nio.ByteBuffer;
import java.util.List;

import com.crystalpixel.neogfutils.event.MissionEvent;

public class MissionScript implements Exportable {

    List<MissionEvent> missionEvents;
    int eventRepeatCount;

    public MissionScript(List<MissionEvent> missionEvents) {
        this(missionEvents, 0);
    }

    public MissionScript(List<MissionEvent> missionEvents, int eventRepeatCount) {
        this.missionEvents = missionEvents;
        this.eventRepeatCount = eventRepeatCount;
    }

    @Override
    public byte[] getAsBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(32 * missionEvents.size() + 1);
        for (MissionEvent missionEvent : missionEvents) {
            buffer.put(missionEvent.getAsBytes());
        }
//        buffer.putShort(eventRepeatCount > 0 ? 0x7fff : 0xffff);
//        buffer.putShort(eventRepeatCount);
        return new byte[0];
    }
}
