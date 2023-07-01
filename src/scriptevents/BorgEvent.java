package scriptevents;

import enums.Commander;

import java.nio.ByteBuffer;

public class BorgEvent extends ScriptEvent {

    private int id;
    private int level;
    private Commander commander;
    private int aggressiveness;
    private int stationary;
    private int intelligence;
    private boolean channelBoolean;
    private boolean boss;
    private int entrance;
    private float x;
    private float y;
    private float z;

    public BorgEvent(int timer1, int timer2, int slot1, int slot2) {
        super(timer1, timer2, slot1, slot2);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Commander getCommander() {
        return commander;
    }

    public void setCommander(Commander commander) {
        this.commander = commander;
    }

    public int getAggressiveness() {
        return aggressiveness;
    }

    public void setAggressiveness(int aggressiveness) {
        this.aggressiveness = aggressiveness;
    }

    public int getStationary() {
        return stationary;
    }

    public void setStationary(int stationary) {
        this.stationary = stationary;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public boolean isChannelBoolean() {
        return channelBoolean;
    }

    public void setChannelBoolean(boolean channelBoolean) {
        this.channelBoolean = channelBoolean;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public ByteBuffer getAsBytes() {
        return super.getAsBytes();
    }
}
