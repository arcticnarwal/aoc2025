package Day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day05p2 {
    public static long getSolution() {
        String filePath = "src/Day05/input.txt";

        List<Range> freshIngredientRanges = new ArrayList<Range>();
        long freshIngredientCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 0) {
                    break;
                }
                String[] rangeString = line.split("-");
                freshIngredientRanges.add(new Range(Long.parseLong(rangeString[0]), Long.parseLong(rangeString[1])));
            }
            Collections.sort(freshIngredientRanges);
            Range condensedRange = freshIngredientRanges.remove(0);
            List<Range> newFreshIngredientRanges = condenseFreshIngredientRanges(new ArrayList<Range>(), freshIngredientRanges, condensedRange);
            for (Range range : newFreshIngredientRanges) {
                freshIngredientCount += range.count();
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return freshIngredientCount;
    }

    public static ArrayList<Range> condenseFreshIngredientRanges(ArrayList<Range> newList, List<Range> remainingList, Range condensedRange) {
        if (remainingList.isEmpty()) {
            newList.add(condensedRange);
            return newList;
        }
        Range nextRange = remainingList.remove(0);
        Range newCondensedRange = null;
        if ( condensedRange != null) {
            newCondensedRange = condensedRange.merge(nextRange);
        } 
        if (newCondensedRange == null) {
            newList.add(condensedRange);
            return condenseFreshIngredientRanges(newList, remainingList, nextRange);
        }   
        return condenseFreshIngredientRanges(newList, remainingList, newCondensedRange);
    }
}

/*
--- Part Two ---
The Elves start bringing their spoiled inventory to the trash chute at the back of the kitchen.

So that they can stop bugging you when they get new inventory, the Elves would like to know all of the IDs that the fresh ingredient ID ranges consider to be fresh. An ingredient ID is still considered fresh if it is 
in any range.

Now, the second section of the database (the available ingredient IDs) is irrelevant. Here are the fresh ingredient ID ranges from the above example:

3-5
10-14
16-20
12-18
The ingredient IDs that these ranges consider to be fresh are 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, and 20. So, in this example, the fresh ingredient ID ranges consider a total of 14 ingredient IDs to be fresh.

Process the database file again. How many ingredient IDs are considered to be fresh according to the fresh ingredient ID ranges? 357907198933892
*/