package Day06;

public class ActionPair {
    private char action;
    private int index;

    public ActionPair(char action, int index) {
        this.action = action;
        this.index = index;
    }

    public char getAction() {
        return action;
    }

    public void setAction(char action) {
        this.action = action;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
