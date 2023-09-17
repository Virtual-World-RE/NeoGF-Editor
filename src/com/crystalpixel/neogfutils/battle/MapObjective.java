package com.crystalpixel.neogfutils.battle;

public enum MapObjective {

    DEFEAT_ENEMY_GOTCHA_BORGS("Defeat Enemy Gotcha Borgs!"),
    FACE_YOUR_OPPONENT("Face Your Opponent!"),
    DEFEAT_THE_BOSS("Defeat The Boss!!"),
    DEFEAT_THE_FLAME_DRAGON("Defeat The Boss!! (Flame Dragon)"),
    DEFEAT_THE_DARK_KNIGHT("Defeat The Boss!! (Dark Knight)"),
    DEFEAT_THE_CYBER_DEATH_DRAGON("Defeat The Boss!! (Cyber Death Dragon)"),
    DEFEAT_THE_DEATH_WING("Defeat The Boss!! (Death Wing)"),
    DEFEAT_THE_COSMIC_DRAGON("Defeat The Boss!! (Cosmic Dragon)"),
    DEFEAT_THE_GALACTIC_EMPEROR("Defeat The Boss!! (Galactic Emperor)");

    private String description;

    MapObjective(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static MapObjective get(int index) {
        return index > values().length || index < 0 ? DEFEAT_THE_BOSS : values()[index];
    }
}
