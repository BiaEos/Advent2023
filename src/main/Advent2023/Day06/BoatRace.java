package main.Advent2023.Day06;

import main.Tools.LaunchProgram;

public class BoatRace {
    /**
     * Launches the options in the console for which part the user wants to get the
     * answer.
     */
    public static void start() {
        LaunchProgram.launchProgram("1", "2", BoatRace.class,
                "startDayOne", "startDayTwo");
    }

    /**
     * Entry point to the program for part 1, calls all methods to get the solution.
     */
    public static void startDayOne() {
        BoatRace boatRace = new BoatRace();
        boatRace.doThings();

    }

    /**
     * Entry point to the program for part 2, calls all methods to get the solution.
     */
    public static void startDayTwo() {
        BoatRace boatRace = new BoatRace();

    }

    private void doThings() {
        System.out.println("Setup Complete");
    }
}
