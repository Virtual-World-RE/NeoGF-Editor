package scriptevents;

import enums.Music;

import java.nio.ByteBuffer;

public class MusicEvent extends ScriptEvent {

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
        return super.getAsBytes();
    }
}