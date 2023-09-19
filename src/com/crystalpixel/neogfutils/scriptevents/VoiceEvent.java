package com.crystalpixel.neogfutils.scriptevents;

import java.nio.ByteBuffer;

import com.crystalpixel.neogfutils.battle.Commander;

public class VoiceEvent extends MissionEvent {

    private Commander commander;
    private boolean mute;

    public VoiceEvent(int timer1, int timer2, int slot1, int slot2) {
        super(timer1, timer2, slot1, slot2);
    }

    public Commander getCommander() {
        return commander;
    }

    public void setCommander(Commander commander) {
        this.commander = commander;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    @Override
    public ByteBuffer getAsBytes() {
        ByteBuffer buffer = super.getAsBytes();
        buffer.put(0xa, (byte) (isMute() ? 0x76 : 0x75));
        buffer.put(0xb, (byte) (getCommander() == null ? -1 : getCommander().ordinal()));
        return buffer;
    }
}
