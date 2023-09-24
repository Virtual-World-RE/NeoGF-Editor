package com.crystalpixel.neogfutils.battle;

public enum BattleOptions {
    ALLY_GF_ENERGY(0x01, "Ally GF Energy"),
    ENEMY_GF_ENERGY(0x02, "Enemy GF Energy"),
    ALLY_SCORE(0x04, "Ally Score"),
    ENEMY_SCORE(0x08, "Enemy Score"),
    TIME_UP_ALLY_WIN(0x10, "Time UP Ally Win"),
    TIME_UP_ENEMY_WIN(0x20, "Time UP Enemy Win"),
    FACE_YOUR_OPPONENT(0x40, "Face Your Opponent!");

    private final int value;
    private final String name;

    BattleOptions(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static int enableOption(int currentOptions, BattleOptions option) {
        return currentOptions | option.getValue();
    }

    public static int disableOption(int currentOptions, BattleOptions option) {
        return currentOptions & ~option.getValue();
    }

    public static boolean isOptionEnabled(int currentOptions, BattleOptions option) {
        return (currentOptions & option.getValue()) != 0;
    }

    public static BattleOptions get(int index) {
        return index > values().length || index < 0 ? null : values()[index];
    }

}
