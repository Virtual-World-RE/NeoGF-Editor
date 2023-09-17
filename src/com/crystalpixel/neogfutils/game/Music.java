package com.crystalpixel.neogfutils.game;

public enum Music {

    INTRODUCTION("Kou"),
    STORY_1("Kakeru"),
    STORY_2("Mana"),
    STORY_3("Sho"),
    EDIT_FORCE("Tetsuya"),
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

    Music(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Music get(int index) {
        return index > values().length ? null : values()[index];
    }
}
