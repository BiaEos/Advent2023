package Advent2023.Day05;

import java.util.ArrayList;
import java.util.List;

import static Tools.LaunchProgram.launchProgram;
import static Tools.LoadFile.inputFromFile;

public class FoodProduction {

    final List<String> input = new ArrayList<>(inputFromFile());

    /**
     * Launches the options in the console for which part the user wants to get the
     * answer.
     */
    public static void start() {
        launchProgram("1", "2", Advent2023.Day04.ScratchCards.class,
                "startDayOne", "startDayTwo");
    }

    /**
     * Entry point to the program for part 1, calls all methods to get the solution.
     */
    public static void startDayOne() {
        FoodProduction foodProduction = new FoodProduction();

    }

    /**
     * Entry point to the program for part 2, calls all methods to get the solution.
     */
    public static void startDayTwo() {
        FoodProduction foodProduction = new FoodProduction();

    }

}