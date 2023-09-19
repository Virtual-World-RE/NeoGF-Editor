package com.crystalpixel.neogfutils.event;

import java.nio.ByteBuffer;

import com.crystalpixel.neogfutils.system.BorgSpecies;

<<<<<<< HEAD:src/com/crystalpixel/neogfutils/event/FocusEvent.java
public class FocusEvent extends Event {
=======
public class FocusEvent extends MissionEvent {
>>>>>>> e57a79eb2b65ee68408796d9e7e13417bd9edc69:src/com/crystalpixel/neogfutils/scriptevents/FocusEvent.java

    private BorgSpecies borgSpecies;
    private boolean pause;
    private int joint;
    private float distance;
    private float duration;

    public FocusEvent(int timer1, int timer2, int slot1, int slot2) {
        super(timer1, timer2, slot1, slot2);
    }

    public BorgSpecies getBorgSpecies() {
        return borgSpecies;
    }

    public void setBorgSpecies(BorgSpecies borgSpecies) {
        this.borgSpecies = borgSpecies;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public int getJoint() {
        return joint;
    }

    public void setJoint(int joint) {
        this.joint = joint;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public ByteBuffer getAsBytes() {
        ByteBuffer buffer = super.getAsBytes();
        buffer.putShort(0x8, (short) getBorgSpecies().getId());
        buffer.put(0xa, (byte) 0x73);
        buffer.put(0xb, (byte) (isPause() ? 1 : 0));
        buffer.put(0x10, (byte) getJoint());
        buffer.putFloat(0x14, getDistance());
        buffer.putFloat(0x18, getDuration());
        return buffer;
    }
}
