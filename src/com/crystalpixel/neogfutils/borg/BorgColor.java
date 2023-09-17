package com.crystalpixel.neogfutils.borg;

public enum BorgColor {

    N("Normal Color"),
    A("Alternate Color"),
    G("Gold"),
    S("Silver"),
    C("Crystal"),
    B("Black");

    private String name;

    BorgColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
