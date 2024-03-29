package com.crystalpixel.neogfutils.event;

import java.nio.ByteBuffer;

import com.crystalpixel.neogfutils.annotation.NotNull;
import com.crystalpixel.neogfutils.game.Music;

public class MusicEvent extends MissionEvent {

    private Music music;

    public MusicEvent(int timer1, int timer2, int slot1, int slot2) {
        super(timer1, timer2, slot1, slot2);
    }

    public MusicEvent(int timer1, int timer2, int slot1, int slot2, Music music) {
        super(timer1, timer2, slot1, slot2);
        this.music = music;
    }

    public Music getMusic() {
        return music;
    }
    
    public void setMusic(Music music) {
        this.music = music;
    }

    @Override
    @NotNull
    public byte[] getAsBytes() {
        ByteBuffer buffer = ByteBuffer.wrap(super.getAsBytes());
        buffer.put(0xa, ((byte) 0x72));
        buffer.put(0xb, (byte) getMusic().ordinal());

        return buffer.array();
    }
}