package com.crystalpixel.neogfutils.event;

import java.nio.ByteBuffer;

import com.crystalpixel.neogfutils.annotation.NotNull;
import com.crystalpixel.neogfutils.battle.entity.Commander;
public class VoiceEvent extends MissionEvent {

    private Commander commander;
    private boolean mute;

    public VoiceEvent(int timer1, int timer2, int slot1, int slot2) {
        super(timer1, timer2, slot1, slot2);
    }

    public VoiceEvent(int timer1, int timer2, int slot1, int slot2, Commander commander, boolean mute) {
        super(timer1, timer2, slot1, slot2);
        this.commander = commander;
        this.mute = mute;
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
    @NotNull
    public byte[] getAsBytes() {
        ByteBuffer buffer = ByteBuffer.wrap(super.getAsBytes());
        buffer.put(0xa, (byte) (isMute() ? 0x76 : 0x75));
        buffer.put(0xb, (byte) (getCommander() == null ? -1 : getCommander().ordinal()));
        return buffer.array();
    }
}
