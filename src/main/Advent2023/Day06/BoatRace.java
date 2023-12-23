package main.Advent2023.Day06;

import main.Tools.LaunchProgram;
import main.Tools.LoadFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoatRace {

    private static final List<String> input = new ArrayList<>(LoadFile.inputFromFile());
    private final List<Integer> timeForRace = new ArrayList<>();
    private final List<Integer> distanceForRace = new ArrayList<>();
    private static int productOfWaysToWin = 1;
    private int waysToWinForRace = 0;

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
        boatRace.parseTimeForRace();
        boatRace.parseDistanceForRace();
        boatRace.runAllRaces();
        System.out.println("The solution for part one is : " + productOfWaysToWin);
    }

    /**
     * Entry point to the program for part 2, calls all methods to get the solution.
     */
    public static void startDayTwo() {
        BoatRace boatRace = new BoatRace();

    }

    /**
     * Separates the individual time for the races that will be run and stores them
     * in the List of Integer timeForRace.
     */
    private void parseTimeForRace() {
        List<Integer> time = Arrays.stream(input.get(0).split(" "))
                .filter(entry -> !entry.matches(""))
                .filter(entry -> !entry.contains(":"))
                .map(Integer::parseInt)
                .toList();
        timeForRace.addAll(time);
    }

    /**
     * Separates the individual winning distances for the races that will be run and
     * stores them in the List of Integer distanceForRace.
     */
    private void parseDistanceForRace() {
        List<Integer> distance = Arrays.stream(input.get(1).split(" "))
                .filter(entry -> !entry.matches(""))
                .filter(entry -> !entry.contains(":"))
                .map(Integer::parseInt)
                .toList();
        distanceForRace.addAll(distance);
    }

    /**
     * Runs all races that have times saved.
     */
    private void runAllRaces() {
        for (int i = 0; i < timeForRace.size(); i++) {
            runSingleRace(i);
        }
    }

    /**
     * Runs a single race tracking the metrics for travel time, distance traveled,
     * and if it is a winning record. Increments the ways to win product for the
     * solution to part one.
     * @param raceNumber the current iteration of race being run
     */
    private void runSingleRace(int raceNumber) {
        waysToWinForRace = 0;
        for (int i = 0; i < timeForRace.get(raceNumber); i++) {
            int travelTime = getTravelTime(raceNumber, i);
            int distanceTraveled = getDistanceTraveled(i, travelTime);
            isWinningRecord(raceNumber, distanceTraveled);
        }
        increaseWaysToWinProduct(waysToWinForRace);
    }

    /**
     * Gets the travel time based on the amount of time the boat is held
     * @param raceNumber the current iteration of the race being run
     * @param holdTime the amount of time the boat is being held
     * @return the travel time the boat has after being held
     */
    private int getTravelTime(int raceNumber, int holdTime) {
        return timeForRace.get(raceNumber) - holdTime;
    }

    /**
     * Gets the distance traveled based on the amount of time the boat is held
     * and the amoutn of time the boat has to travel.
     * @param holdTime the amount of time the boat is being held
     * @param travelTime the amount of time the boat will travel
     * @return the distance the boat will travel
     */
    private int getDistanceTraveled(int holdTime, int travelTime) {
        return holdTime * travelTime;
    }

    /**
     * Checks if the travel distance beats a current record. If it beats
     * a record currently stored in the distanceForRace list the
     * waysToWinForRace variable will be incremented by one.
     * @param raceNumber the current iteration of race being run
     * @param distanceTraveled the distance the boat traveled
     */
    private void isWinningRecord(int raceNumber, int distanceTraveled) {
        if (distanceForRace.get(raceNumber) < distanceTraveled) {
            waysToWinForRace++;
        }
    }

    /**
     * The final product of the races being run is calculated by multiplying
     * the current product of ways to win by the additional ways to win from
     * the current race being run.
     * @param waysToWin the current race's ways to win that beat records
     */
    private void increaseWaysToWinProduct(int waysToWin) {
        productOfWaysToWin *= waysToWin;
    }
}
