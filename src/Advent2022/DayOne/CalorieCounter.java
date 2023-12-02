/**
 * Created by: Alyson
 * Date: 12/19/22
 * Time: 12:58 PM
 * Project Name: Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/

package Advent2022.DayOne;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static Tools.LoadFile.inputFromFile;

public class CalorieCounter {
    private final List<String> calories = new ArrayList<>(inputFromFile());
    private final List<String> perElfCalories = new ArrayList<>();
    private int mostCalories = 0;

    public static void whichElf(){
        CalorieCounter calorieCounter = new CalorieCounter();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want only the top elf? Yes or No");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("yes")) {
            calorieCounter.elfWithMostCalories();
        } else if (answer.equalsIgnoreCase("no")) {
            calorieCounter.topThreeElvesCalories();
        } else if (answer.equalsIgnoreCase("exit")) {
            System.exit(0);
        } else {
            System.out.println("Please enter Yes, No, or Exit");
            whichElf();
        }
    }

    private void countCalories() {
        int totalCalPerElf = 0;
        for (String calorie : calories) {
            if (!calorie.equals("")) {
                totalCalPerElf += Integer.parseInt(calorie);
            } else {
                perElfCalories.add(String.valueOf(totalCalPerElf));
                totalCalPerElf = 0;
            }
        }
    }

    private void elfWithMostCalories() {
        countCalories();
        for (int i = 1; i < perElfCalories.size() - 1; i++) {
            int currentElfCal = Integer.parseInt(perElfCalories.get(i - 1));
            int nextElfCal  = Integer.parseInt(perElfCalories.get(i));
            if (currentElfCal < nextElfCal && nextElfCal > mostCalories) {
                mostCalories = nextElfCal;
            } else if (currentElfCal > nextElfCal && currentElfCal > mostCalories){
                mostCalories = currentElfCal;
            }
        }
        System.out.println("The elf with the most calories has " + mostCalories +
            " calories.");
    }

    private void topThreeElvesCalories() {
        countCalories();
        int firstElf = 0;
        int secondElf = 0;
        int thirdElf = 0;
        for (int i = 1; i < perElfCalories.size() - 1; i++) {
            int currentElfCal = Integer.parseInt(perElfCalories.get(i - 1));
            if (currentElfCal > firstElf) {
                thirdElf = secondElf;
                secondElf = firstElf;
                firstElf = currentElfCal;
            } else if (currentElfCal > secondElf && currentElfCal != firstElf) {
                thirdElf = secondElf;
                secondElf = currentElfCal;
            } else if (currentElfCal > thirdElf && currentElfCal != secondElf) {
                thirdElf = currentElfCal;
            }
        }
        System.out.println("The top three elves combined calories are " +
            (firstElf + secondElf + thirdElf));
    }
}
