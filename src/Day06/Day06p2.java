package Day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day06p2 {
    public static final char MULTIPLY = '*';
    public static final char ADD = '+';

    public static long getSolution() {
        long grandTotal = 0;

        List<String> lines = loadLinesFromFile("src/Day06/input.txt");
        int rightIndex = lines.getFirst().length() - 1;
        String actionsString = lines.getLast();
        List<ActionPair> actions = new ArrayList<>();

        for (int index = 0; index < actionsString.length(); index++){
            char action = actionsString.charAt(index);
            if (action == MULTIPLY || action == ADD) {
                actions.add(new ActionPair(action, index));
            }
        }

        actions = actions.reversed();
        lines.removeLast();

        for (int actionIndex = 0; actionIndex < actions.size(); actionIndex++) {
            ActionPair thisAction = actions.get(actionIndex);
            List<Long> values = new ArrayList<>();

            for (int i = rightIndex; i >= thisAction.getLocation(); i--) {
                String val = "";
                for (int row = 0; row < lines.size(); row++) {
                    char digit = lines.get(row).charAt(i);
                    if (digit != ' ') {
                        val += digit;
                    }                        
                }
                values.add(Long.parseLong(val));
            }

            grandTotal += thisAction.performOn(values);
            rightIndex = thisAction.getLocation() - 2;
        }

        return grandTotal;
    }

    public static List<String> loadLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
         return lines;
    }
}

/*
--- Part Two ---
The big cephalopods come back to check on how things are going. When they see that your grand total doesn't match the one expected by the worksheet, 
they realize they forgot to explain how to read cephalopod math.

Cephalopod math is written right-to-left in columns. Each number is given in its own column, with the most significant digit at the top and the least 
significant digit at the bottom. (Problems are still separated with a column consisting only of spaces, and the symbol at the bottom of the problem is still the operator to use.)

Here's the example worksheet again:

123 328  51 64 
 45 64  387 23 
  6 98  215 314
*   +   *   +  
Reading the problems right-to-left one column at a time, the problems are now quite different:

The rightmost problem is 4 + 431 + 623 = 1058
The second problem from the right is 175 * 581 * 32 = 3253600
The third problem from the right is 8 + 248 + 369 = 625
Finally, the leftmost problem is 356 * 24 * 1 = 8544
Now, the grand total is 1058 + 3253600 + 625 + 8544 = 3263827.

Solve the problems on the math worksheet again. What is the grand total found by adding together all of the answers to the individual problems? 9627174150897
*/
