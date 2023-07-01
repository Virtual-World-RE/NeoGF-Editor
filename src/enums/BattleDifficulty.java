package enums;

public enum BattleDifficulty {

    YELLOW("Yellow"),
    RED("Red"),
    TUFF_STUFF("Tuff Stuff");

    private String name;

    BattleDifficulty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BattleDifficulty get(int index) {
        return index > values().length || index < 0 ? TUFF_STUFF : values()[index];
    }
}
