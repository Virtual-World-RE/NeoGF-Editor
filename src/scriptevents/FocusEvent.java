package scriptevents;

import system.BorgSpecies;

import java.nio.ByteBuffer;

public class FocusEvent extends ScriptEvent {

    private BorgSpecies borgSpecies;
    private boolean pause;
    private int focalPoint;
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

    public int getFocalPoint() {
        return focalPoint;
    }

    public void setFocalPoint(int focalPoint) {
        this.focalPoint = focalPoint;
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
        return super.getAsBytes();
    }
}
