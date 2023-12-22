/**
 * Created by: Alyson
 * Date: 12/20/22
 * Time: 4:34 AM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package main.Advent2022.DayThree;

import main.Tools.LoadFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SortRucksack {
    private final ArrayList<String> rucksackItems = new ArrayList<>(LoadFile.inputFromFile());
    private final ArrayList<String> duplicateLetters = new ArrayList<>();
    private final HashMap<String, Integer> letterValues = new HashMap<>();
    private int sumPriorityValue;

    public static void findPriority() {
        SortRucksack sortRucksack = new SortRucksack();
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want priority of Item or Badge?");
        String answer = input.next();
        if (answer.equalsIgnoreCase("Item")) {
            sortRucksack.findDuplicateLetters();
            sortRucksack.assignNumberValueToLetter();
            sortRucksack.lettersToSum();
        } else if (answer.equalsIgnoreCase("Badge")) {
            sortRucksack.groupOfThreeElves();
            sortRucksack.assignNumberValueToLetter();
            sortRucksack.lettersToSum();
        } else if (answer.equalsIgnoreCase("Exit")) {
            System.exit(0);
        } else {
            System.out.println("Please enter Item, Badge, or Exit");
            findPriority();
        }

    }

    private void lettersToSum() {
        for (String duplicateLetter : duplicateLetters) {
            if (letterValues.containsKey(duplicateLetter)) {
                sumPriorityValue += letterValues.get(duplicateLetter);
            }
        }
        System.out.println("The sum of priorities is: " + sumPriorityValue);
    }

    private void findDuplicateLetters() {
        for (String rucksackItem : rucksackItems) {
            String bagOne = rucksackItem.substring(0, rucksackItem.length() / 2);
            String bagTwo = rucksackItem.substring(rucksackItem.length() / 2);

            int index = -1;
            outerloop:
            for (int i = 0; i < bagOne.length(); i++) {
                for (int j = 0; j < bagOne.length(); j++) {
                    if (bagOne.charAt(i) == bagTwo.charAt(j)) {
                        index++;
                        duplicateLetters.add(index, String.valueOf(bagOne.charAt(i)));
                        break outerloop;
                    }
                }
            }
        }
    }

    private void assignNumberValueToLetter() {
        int i = 1;
        for (char letter = 'a'; letter <= 'z'; letter++) {
            letterValues.put(String.valueOf(letter), i);
            i++;
        }
        for (char capLetter = 'A'; capLetter <= 'Z'; capLetter++) {
            letterValues.put(String.valueOf(capLetter), i);
            i++;
        }
    }

    private void groupOfThreeElves() {
        for (int i = 0; i < rucksackItems.size() - 2; i += 3) {
            String rucksackOne = rucksackItems.get(i);
            String rucksackTwo = rucksackItems.get(i + 1);
            String rucksackThree = rucksackItems.get(i + 2);

            int index = -1;
            outerloop:
            for (char oneLetters : rucksackOne.toCharArray()) {
                for (char twoLetters : rucksackTwo.toCharArray()) {
                    for (char threeLetters : rucksackThree.toCharArray()) {
                        if (oneLetters == twoLetters && oneLetters == threeLetters) {
                            index++;
                            duplicateLetters.add(index, String.valueOf(oneLetters));
                            break outerloop;
                        }
                    }
                }
            }
        }
    }
}
