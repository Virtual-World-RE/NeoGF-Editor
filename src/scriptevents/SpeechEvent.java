package scriptevents;

import enums.Commander;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class SpeechEvent extends ScriptEvent {

    private Commander commander;
    private int sound;
    private boolean visible;
    private boolean wait;

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

    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    @Override
    public ByteBuffer getAsBytes() {
        ByteBuffer buffer = super.getAsBytes();
        buffer.put(0xa, ((byte) 0x74));
        buffer.put(0xb, ((byte) getCommander().ordinal()));
        buffer.put(0xc, ((byte) getSound()));
        buffer.put(0xd, (byte) (isVisible() ? 1 : 0));
        buffer.put(0xe, (byte) (isWait() ? 1 : 0));
        return buffer;
    }
}
