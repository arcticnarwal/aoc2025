package Day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Day04 {
    public static long getSolution() {
        String filePath = "src/Day04/input.txt";
        int accessibleRolls = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            AdjacentRollResult rollResult = getAccessibleRollsFromRow(line, new ArrayList<Integer>());
            while ((line = reader.readLine()) != null) {
                rollResult = getAccessibleRollsFromRow(line, rollResult.getCurrentRowResult());
                accessibleRolls += rollResult.getAccessibleRollCount();
            }
            AdjacentRollResult finalRollResult = new AdjacentRollResult(rollResult.getCurrentRowResult(), null);
            accessibleRolls += finalRollResult.getAccessibleRollCount();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
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
--- Day 4: Printing Department ---
You ride the escalator down to the printing department. They're clearly getting ready for Christmas; they have lots of large rolls of paper everywhere, and there's even a massive printer in the corner (to handle the really big print jobs).

Decorating here will be easy: they can make their own decorations. What you really need is a way to get further into the North Pole base while the elevators are offline.

"Actually, maybe we can help with that," one of the Elves replies when you ask for help. "We're pretty sure there's a cafeteria on the other side of the back wall. If we could break through the wall, you'd be able to keep moving. It's too bad all of our forklifts are so busy moving those big rolls of paper around."

If you can optimize the work the forklifts are doing, maybe they would have time to spare to break through the wall.

The rolls of paper (@) are arranged on a large grid; the Elves even have a helpful diagram (your puzzle input) indicating where everything is located.

For example:

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
The forklifts can only access a roll of paper if there are fewer than four rolls of paper in the eight adjacent positions. If you can figure out which rolls of paper the forklifts can access, they'll spend less time looking and more time breaking down the wall to the cafeteria.

In this example, there are 13 rolls of paper that can be accessed by a forklift (marked with x):

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

Consider your complete diagram of the paper roll locations. How many rolls of paper can be accessed by a forklift? 1478
*/