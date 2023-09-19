package com.crystalpixel.neogfutils.event;

import java.nio.ByteBuffer;

import com.crystalpixel.neogfutils.game.Music;

<<<<<<< HEAD:src/com/crystalpixel/neogfutils/event/MusicEvent.java
public class MusicEvent extends Event {
=======
public class MusicEvent extends MissionEvent {
>>>>>>> e57a79eb2b65ee68408796d9e7e13417bd9edc69:src/com/crystalpixel/neogfutils/scriptevents/MusicEvent.java

    private Music music;

    public MusicEvent(int timer1, int timer2, int slot1, int slot2) {
        super(timer1, timer2, slot1, slot2);
    }

    public Music getMusic() {
        return music;
    }
    
    public void setMusic(Music music) {
        this.music = music;
    }

    @Override
    public ByteBuffer getAsBytes() {
        ByteBuffer buffer = super.getAsBytes();
        buffer.put(0xa, ((byte) 0x72));
        buffer.put(0xb, (byte) getMusic().ordinal());

        return buffer;
    }
}