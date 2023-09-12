package enums;

public enum BattleObjective {

    DEFEAT_ENEMY_GOTCHA_BORGS("Defeat Enemy Gotcha Borgs!"),
    DEFEAT_THE_BOSS("Defeat The Boss!"),
    FACE_YOUR_OPPONENT("Face Your Opponent!");

    private String description;

    BattleObjective(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
