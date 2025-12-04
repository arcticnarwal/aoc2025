package Day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day04p2 {
    public static long getSolution() {
        String filePath = "src/Day04/input.txt";
        int accessibleRolls = 0;
        int oldAccessibleRolls = -1;
        List<String> fileLines = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        AdjacentRollResult rollResult = new AdjacentRollResult(null, null);
        List<String> newFileLines = new ArrayList<String>();

        while (accessibleRolls > oldAccessibleRolls) {
            oldAccessibleRolls = accessibleRolls;
            for (String line : fileLines) {
                rollResult = getAccessibleRollsFromRow(line, rollResult.getCurrentRowResult());
                accessibleRolls += rollResult.getAccessibleRollCount();
                newFileLines.add(rollResult.getRemovedAccessibleRollRow());
            }

            AdjacentRollResult finalRollResult = new AdjacentRollResult(rollResult.getCurrentRowResult(), null);
            accessibleRolls += finalRollResult.getAccessibleRollCount();
            newFileLines.add(finalRollResult.getRemovedAccessibleRollRow());
            newFileLines.remove(0);
            fileLines = new ArrayList<String>(newFileLines);
            newFileLines.clear();
            rollResult.reset();
        }

        return accessibleRolls;
    }

    private static AdjacentRollResult getAccessibleRollsFromRow(String rowData, List<Integer> previousRow) {
        char rollIndicator = '@';
        List<Integer> currentRow = new ArrayList<Integer>();
        for (int index = 0; index < rowData.length(); index++) {
            if (rowData.charAt(index) != rollIndicator) {
                currentRow.add(-9);
                continue;
            }
            if (index == 0) {
                currentRow.add(0);
            } else if (currentRow.get(index - 1) >= 0) {
                currentRow.add(index, 1);
            } else {
                currentRow.add(index, 0);
            }
            if (index > 0) {
                currentRow.set(index - 1, currentRow.get(index - 1) + 1);
            }
            if (previousRow.isEmpty()) {
                continue;
            }
            if (index > 0) {
                previousRow.set(index - 1, previousRow.get(index - 1) + 1);
                if (previousRow.get(index - 1) >= 0) {
                    currentRow.set(index, currentRow.get(index) + 1);
                }
            }
            if (index < previousRow.size() - 1) {
                previousRow.set(index + 1, previousRow.get(index + 1) + 1);
                if (previousRow.get(index + 1) >= 0) {
                    currentRow.set(index, currentRow.get(index) + 1);
                }
            }
            previousRow.set(index, previousRow.get(index) + 1);
            if (previousRow.get(index) >= 0) {
                currentRow.set(index, currentRow.get(index) + 1);
            }
        }
        return new AdjacentRollResult(previousRow, currentRow);
    }
}

/*
The first half of this puzzle is complete! It provides one gold star: *

--- Part Two ---
Now, the Elves just need help accessing as much of the paper as they can.

Once a roll of paper can be accessed by a forklift, it can be removed. Once a roll of paper is removed, the forklifts might be able to access more rolls of paper, which they might also be able to remove. How many total rolls of paper could the Elves remove if they keep repeating this process?

Starting with the same example as above, here is one way you could remove as many rolls of paper as possible, using highlighted @ to indicate that a roll of paper is about to be removed, and using x to indicate that a roll of paper was just removed:

Initial state:
..@@.@@@@.
@@@.@.@.@@
@@@@@.@.@@
@.@@@@..@.
@@.@@@@.@@
.@@@@@@@.@
.@.@.@.@@@
@.@@@.@@@@
.@@@@@@@@.
@.@.@@@.@.

Remove 13 rolls of paper:
..xx.xx@x.
x@@.@.@.@@
@@@@@.x.@@
@.@@@@..@.
x@.@@@@.@x
.@@@@@@@.@
.@.@.@.@@@
x.@@@.@@@@
.@@@@@@@@.
x.x.@@@.x.

Remove 12 rolls of paper:
.......x..
.@@.x.x.@x
x@@@@...@@
x.@@@@..x.
.@.@@@@.x.
.x@@@@@@.x
.x.@.@.@@@
..@@@.@@@@
.x@@@@@@@.
....@@@...

Remove 7 rolls of paper:
..........
.x@.....x.
.@@@@...xx
..@@@@....
.x.@@@@...
..@@@@@@..
...@.@.@@x
..@@@.@@@@
..x@@@@@@.
....@@@...

Remove 5 rolls of paper:
..........
..x.......
.x@@@.....
..@@@@....
...@@@@...
..x@@@@@..
...@.@.@@.
..x@@.@@@x
...@@@@@@.
....@@@...

Remove 2 rolls of paper:
..........
..........
..x@@.....
..@@@@....
...@@@@...
...@@@@@..
...@.@.@@.
...@@.@@@.
...@@@@@x.
....@@@...

Remove 1 roll of paper:
..........
..........
...@@.....
..x@@@....
...@@@@...
...@@@@@..
...@.@.@@.
...@@.@@@.
...@@@@@..
....@@@...

Remove 1 roll of paper:
..........
..........
...x@.....
...@@@....
...@@@@...
...@@@@@..
...@.@.@@.
...@@.@@@.
...@@@@@..
....@@@...

Remove 1 roll of paper:
..........
..........
....x.....
...@@@....
...@@@@...
...@@@@@..
...@.@.@@.
...@@.@@@.
...@@@@@..
....@@@...

Remove 1 roll of paper:
..........
..........
..........
...x@@....
...@@@@...
...@@@@@..
...@.@.@@.
...@@.@@@.
...@@@@@..
....@@@...
Stop once no more rolls of paper are accessible by a forklift. In this example, a total of 43 rolls of paper can be removed.

Start with your original diagram. How many rolls of paper in total can be removed by the Elves and their forklifts? 9120
*/
