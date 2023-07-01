package system;

public class BorgComboCost {

    private String text;
    private int cost;

    public BorgComboCost(String text, int cost) {
        this.text = text;
        this.cost = cost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
