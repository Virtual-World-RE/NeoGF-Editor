package com.crystalpixel.neogfutils.borg;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.crystalpixel.neogfutils.system.BorgSpecies;

public class Borg {

    private BorgSpecies species;
    private BorgColor color;
    private int level;
    private int timeObtained;
    private int exp;
    private boolean isNew;

    public Borg(BorgSpecies species) {
        this(species, BorgColor.N);
    }

    public Borg(BorgSpecies species, BorgColor color) {
        this(species, color, 0);
    }

    public Borg(BorgSpecies species, BorgColor color, int exp) {
        this.species = species;
        this.color = color;
        this.exp = exp;
    }

    public BorgSpecies getSpecies() {
        return species;
    }

    public void setSpecies(BorgSpecies species) {
        this.species = species;
    }

    public BorgColor getColor() {
        return color;
    }

    public void setColor(BorgColor color) {
        this.color = color;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTimeObtained() {
        return timeObtained;
    }

    public void setTimeObtained(int timeObtained) {
        this.timeObtained = timeObtained;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public int getCost() throws IOException {
        return species.getCosts().get(color);
    }

    public BorgLevelStats getBorgLevelStats() throws IOException {
        return species.getAllBorgLevelStats()[getLevel()];
    }

    public BorgRarity getRarity() throws IOException {
        return species.getRarities().get(color);
    }

    public int getGetValue() throws IOException {
        return species.getGetValues().get(color);
    }

    public int calculateLevel() {
        return 0;
    }

    @Override
    public String toString() {
        try {
            return color.name() + species.getNo();
        } catch (IOException e) {
            return null;
        }
    }

    public ByteBuffer getAsBoxBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.putShort(0x0, (short) species.getId());
        buffer.put(0x2, (byte) color.ordinal());
        buffer.put(0x3, (byte) level);
        buffer.putInt(0x4, timeObtained);
        buffer.putInt(0x8, exp);
        buffer.put(0xc, (byte) (isNew ? 1 : 0));
        return buffer;
    }

    public ByteBuffer getAsWarehouseBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putShort(0x0, (short) species.getId());
        buffer.put(0x2, (byte) color.ordinal());
        buffer.put(0x3, (byte) level);
        buffer.putInt(0x4, timeObtained);
        buffer.putInt(0x8, exp);
        buffer.put(0xc, (byte) (isNew ? 1 : 0));
        return buffer;
    }
}
