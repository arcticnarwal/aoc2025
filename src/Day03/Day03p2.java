package Day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day03p2 {
    public static long getSolution() {
        String filePath = "src/Day03/input.txt";
        long maximumJoltage = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                maximumJoltage += Long.parseLong(getBatteryBankJoltage(line, "", 12));
            }   
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return maximumJoltage;
    }

    private static String getBatteryBankJoltage(String batteryBank, String currentJoltage, int joltageDigitsLeft) {
        int maxIndex = batteryBank.length() - joltageDigitsLeft;
        int usedIndex = 0;
        int usedDigit = Character.getNumericValue(batteryBank.charAt(usedIndex));
        String newCurrentJoltage = currentJoltage + batteryBank.charAt(usedIndex);

        if (maxIndex <= 0) {
            return currentJoltage + batteryBank;
        }
        
        for (int index = 0; index <= maxIndex; index++) {
            int newDigit = Character.getNumericValue(batteryBank.charAt(index));

            if ( newDigit > usedDigit) {
                usedIndex = index;
                usedDigit = newDigit;
                newCurrentJoltage = newCurrentJoltage.substring(0, newCurrentJoltage.length() - 1) + batteryBank.charAt(index);
            }
        }

        if (joltageDigitsLeft > 1) {
            return getBatteryBankJoltage(batteryBank.substring(usedIndex + 1), newCurrentJoltage, joltageDigitsLeft - 1);
        }

        return newCurrentJoltage;
    }
}

/*
--- Part Two ---
The escalator doesn't move. The Elf explains that it probably needs more joltage to overcome the static friction of the system and hits the big red "joltage limit safety override" button. 
You lose count of the number of times she needs to confirm "yes, I'm sure" and decorate the lobby a bit while you wait.

Now, you need to make the largest joltage by turning on exactly twelve batteries within each bank.

The joltage output for the bank is still the number formed by the digits of the batteries you've turned on; the only difference is that now there will be 12 digits in each bank's joltage output 
instead of two.

Consider again the example from before:

987654321111111
811111111111119
234234234234278
818181911112111
Now, the joltages are much larger:

In 987654321111111, the largest joltage can be found by turning on everything except some 1s at the end to produce 987654321111.
In the digit sequence 811111111111119, the largest joltage can be found by turning on everything except some 1s, producing 811111111119.
In 234234234234278, the largest joltage can be found by turning on everything except a 2 battery, a 3 battery, and another 2 battery near the start to produce 434234234278.
In 818181911112111, the joltage 888911112111 is produced by turning on everything except some 1s near the front.
The total output joltage is now much larger: 987654321111 + 811111111119 + 434234234278 + 888911112111 = 3121910778619.

What is the new total output joltage? 172119830406258
*/