package Day06;

import java.util.List;

public class ActionPair {
    private char action;
    private int location;

    public ActionPair(char action, int location) {
        this.action = action;
        this.location = location;
    }

    public char getAction() {
        return action;
    }

    public void setAction(char action) {
        this.action = action;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public Long performOn(List<Long> vals) {
        Long returnVal = 0L;
        if (this.action == Day06.MULTIPLY) {
            returnVal = 1L;
            for (Long v : vals) {
                returnVal *= v; 
            }
        }
        if (this.action == Day06.ADD) {
            for (Long v : vals) {
                returnVal += v; 
            }
        }
        return returnVal;
    }
}
