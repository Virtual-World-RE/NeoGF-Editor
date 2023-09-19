package com.crystalpixel.neogfutils.event;

import java.nio.ByteBuffer;

import com.crystalpixel.neogfutils.battle.Commander;

<<<<<<< HEAD:src/com/crystalpixel/neogfutils/event/SpeechEvent.java
public class SpeechEvent extends Event {
=======
public class SpeechEvent extends MissionEvent {
>>>>>>> e57a79eb2b65ee68408796d9e7e13417bd9edc69:src/com/crystalpixel/neogfutils/scriptevents/SpeechEvent.java

    private Commander commander;
    private int sound;
    private boolean visible;
    private boolean queue;

    public SpeechEvent(int timer1, int timer2, int slot1, int slot2) {
        super(timer1, timer2, slot1, slot2);
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isQueue() {
        return queue;
    }

    public void setQueue(boolean queue) {
        this.queue = queue;
    }

    @Override
    public ByteBuffer getAsBytes() {
        ByteBuffer buffer = super.getAsBytes();
        buffer.put(0xa, ((byte) 0x74));
        buffer.put(0xb, ((byte) getCommander().ordinal()));
        buffer.put(0xc, ((byte) getSound()));
        buffer.put(0xd, (byte) (isVisible() ? 1 : 0));
        buffer.put(0xe, (byte) (isQueue() ? 1 : 0));
        return buffer;
    }
}
