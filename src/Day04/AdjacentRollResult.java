package Day04;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdjacentRollResult {
    private List<Integer> previousRowResult;
    private List<Integer> currentRowResult;

    public AdjacentRollResult(List<Integer> previousRowResult, List<Integer> currentRowResult) {
        this.previousRowResult = previousRowResult;
        this.currentRowResult = currentRowResult;
    }

    public int getAccessibleRollCount() {
        if (this.previousRowResult == null) {
            return 0;
        }
        List<Integer> temp = new ArrayList<Integer>(this.previousRowResult);
        temp.removeIf(rollCount -> rollCount >= 4 || rollCount < 0);
        return temp.size();
    }

    public String getRemovedAccessibleRollRow() {
        List<Integer> temp = new ArrayList<Integer>(this.previousRowResult);
        temp.replaceAll(rollCount -> rollCount < 4 ? 0 : 1);
        return temp.stream().map(Object::toString).collect(Collectors.joining("")).replace("0", ".").replace("1", "@");
    }

    public List<Integer> getPreviousRowResult() {
        return this.previousRowResult;
    }

    public List<Integer> getCurrentRowResult() {
        if (this.currentRowResult == null) {
            return new ArrayList<Integer>();
        }
        return this.currentRowResult;
    }

    public void reset() {
        this.previousRowResult = null;
        this.currentRowResult = null;
    }
}
