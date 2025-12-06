package Day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day05 {
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
            while ((line = reader.readLine()) != null) {
                for ( Range range : newFreshIngredientRanges ) {
                    if (range.contains(Long.parseLong(line))) {
                        freshIngredientCount++;
                    }
                }
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
--- Day 5: Cafeteria ---
As the forklifts break through the wall, the Elves are delighted to discover that there was a cafeteria on the other side after all.

You can hear a commotion coming from the kitchen. "At this rate, we won't have any time left to put the wreaths up in the dining hall!" Resolute in your quest, you investigate.

"If only we hadn't switched to the new inventory management system right before Christmas!" another Elf exclaims. You ask what's going on.

The Elves in the kitchen explain the situation: because of their complicated new inventory management system, they can't figure out which of their ingredients are fresh and which are spoiled. When you ask how it works, 
they give you a copy of their database (your puzzle input).

The database operates on ingredient IDs. It consists of a list of fresh ingredient ID ranges, a blank line, and a list of available ingredient IDs. For example:

3-5
10-14
16-20
12-18

1
5
8
11
17
32
The fresh ID ranges are inclusive: the range 3-5 means that ingredient IDs 3, 4, and 5 are all fresh. The ranges can also overlap; an ingredient ID is fresh if it is in any range.

The Elves are trying to determine which of the available ingredient IDs are fresh. In this example, this is done as follows:

Ingredient ID 1 is spoiled because it does not fall into any range.
Ingredient ID 5 is fresh because it falls into range 3-5.
Ingredient ID 8 is spoiled.
Ingredient ID 11 is fresh because it falls into range 10-14.
Ingredient ID 17 is fresh because it falls into range 16-20 as well as range 12-18.
Ingredient ID 32 is spoiled.
So, in this example, 3 of the available ingredient IDs are fresh.

Process the database file from the new inventory management system. How many of the available ingredient IDs are fresh? 862
*/