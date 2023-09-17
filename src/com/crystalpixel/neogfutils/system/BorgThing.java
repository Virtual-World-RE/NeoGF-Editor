package com.crystalpixel.neogfutils.system;

import com.crystalpixel.neogfutils.borg.BorgColor;

public class BorgThing {

    private BorgSpecies species;
    private BorgColor color;
    private int count;
    private int memoryAddress;

    public BorgThing(BorgSpecies species, BorgColor color) {
        this(species, color, 0);
    }

    public BorgThing(BorgSpecies species, BorgColor color, int count) {
        this(species, color, count, 0);
    }

    public BorgThing(BorgSpecies species, BorgColor color, int count, int memoryAddress) {
        this.species = species;
        this.color = color;
        this.count = count;
        this.memoryAddress = memoryAddress;
    }

    public BorgSpecies getSpecies() {
        return species;
    }

    public BorgColor getColor() {
        return color;
    }

    public int getCount() {
        return count;
    }

    public int getMemoryAddress() {
        return memoryAddress;
    }

    @Override
    public String toString() {
        return species.toString() + "," + color + "," + count;
    }
}
