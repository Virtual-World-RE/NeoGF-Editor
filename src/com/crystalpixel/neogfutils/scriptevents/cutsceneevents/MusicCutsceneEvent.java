package com.crystalpixel.neogfutils.scriptevents.cutsceneevents;

import java.nio.ByteBuffer;

public class MusicCutsceneEvent implements GameEvent {

    private int music;

    public MusicCutsceneEvent(int music) {
        this.music = music;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    @Override
    public ByteBuffer getAsBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putShort(0, (short) 0x11f);
        buffer.putShort(2, (short) music);
        return buffer;
    }
}