package com.crystalpixel.neogfutils.battle;

public enum BattleOptions {
    ALLY_GF_ENERGY(0x01),
    ENEMY_GF_ENERGY(0x02),
    ALLY_SCORE(0x04),
    ENEMY_SCORE(0x08),
    TIME_UP_ALLY_WIN(0x10),
    TIME_UP_ENEMY_WIN(0x20),
    FACE_YOUR_OPPONENT(0x40);

    private final int value;

    BattleOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
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
