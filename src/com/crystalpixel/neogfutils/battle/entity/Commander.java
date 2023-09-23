package com.crystalpixel.neogfutils.battle.entity;

public enum Commander {

    KOU("Kou"),
    KAKERU("Kakeru"),
    MANA("Mana"),
    SHO("Sho"),
    TETSUYA("Tetsuya"),
    KOTARO("Kotaro"),
    TSUTOMU("Tsutomu"),
    NEKOBE("Nekobe"),
    KITSUNE("Kitsune"),
    MET("Met"),
    TAMA("Tama"),
    USAGI("Usagi"),
    YUJI("Yuji"),
    OROCHI("Orochi"),
    NONE("N/A");

    private String name;

    Commander(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Commander get(int index) {
        return index > values().length || index < 0 ? null : values()[index];
    }
}