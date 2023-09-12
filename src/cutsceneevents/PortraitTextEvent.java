package cutsceneevents;

import java.nio.ByteBuffer;

public class PortraitTextEvent implements GameEvent {

    private int slot;
    private int text;

    public PortraitTextEvent(int slot, int text) {
        this.slot = slot;
        this.text = text;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
    }

    @Override
    public ByteBuffer getAsBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        buffer.putShort(0, (short) 0x21c);
        buffer.putShort(2, (short) slot);
        buffer.putShort(4, (short) text);
        return buffer;
    }
}