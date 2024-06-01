package com.crystalpixel.neogfutils.system;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.crystalpixel.neogfutils.event.MissionEvent;

public class MissionScript implements Serializable {

    List<MissionEvent> missionEvents = new ArrayList<>();
    int eventRepeatCount;

    public MissionScript() {
    }

    public MissionScript(List<MissionEvent> missionEvents) {
        this(missionEvents, 0);
    }

    public MissionScript(List<MissionEvent> missionEvents, int eventRepeatCount) {
        this.missionEvents = missionEvents;
        this.eventRepeatCount = eventRepeatCount;
    }

    public List<MissionEvent> getMissionEvents() {
        return missionEvents;
    }

    public void setMissionEvents(List<MissionEvent> missionEvents) {
        this.missionEvents = missionEvents;
    }

    public int getEventRepeatCount() {
        return eventRepeatCount;
    }

    public void setEventRepeatCount(int eventRepeatCount) {
        this.eventRepeatCount = eventRepeatCount;
    }

    @Override
    public int getAllocation() {
        return MissionEvent.ALLOCATION * (missionEvents.size() + 1);
    }

    @Override
    public byte[] getAsBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(getAllocation());
        for (MissionEvent missionEvent : missionEvents) {
            buffer.put(missionEvent.getAsBytes());
        }
        buffer.putShort((short) (eventRepeatCount > 0 ? 0x7fff : 0xffff));
        buffer.putShort((short) eventRepeatCount);
        return buffer.array();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MissionScript)) return false;
        return Arrays.equals(this.getAsBytes(), ((MissionScript) o).getAsBytes());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
