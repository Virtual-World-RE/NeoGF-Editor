package enums;

public enum BorgColor {

    N("Normal Color"),
    A("Another Color"),
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
