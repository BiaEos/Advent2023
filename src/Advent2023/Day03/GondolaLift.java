package Advent2023.Day03;

import java.util.ArrayList;
import java.util.List;

import static Tools.LaunchProgram.launchProgram;
import static Tools.LoadFile.inputFromFile;

public class GondolaLift {
    private static final List<String> input = new ArrayList<>(inputFromFile());
    private static String[][] engineSchematic;
    private Boolean[][] symbolLocations;
    private Boolean[][] starLocations;
    private static int sumOfPartNumbers = 0;
    private static int sumOfGearRatios = 0;


    /**
     * Launches the options in the console for which part the user wants to get the
     * answer.
     */
    public static void start() {
        launchProgram("1", "2", GondolaLift.class,
                "startDayOne", "startDayTwo");
    }

    /**
     * Entry point to the program for part 1, calls all methods to get the solution.
     */
    public static void startDayOne() {
        GondolaLift gondolaLift = new GondolaLift();
        gondolaLift.convertInputTo2DArrayAndSaveValidLocations();
        gondolaLift.checkNeighbors();
        System.out.println("The answer for part one is : " + sumOfPartNumbers);
    }

    /**
     * Entry point to the program for part 2, calls all methods to get the solution.
     */
    public static void startDayTwo() {
        GondolaLift gondolaLift = new GondolaLift();
        gondolaLift.convertInputTo2DArrayAndSaveValidLocations();
        gondolaLift.checkNeighbors();
        System.out.println("The answer for part two is : " + sumOfGearRatios);
    }

    /**
     * Takes the input created in the LoadFile class and turns it into a
     * 2d array storing the values. Calls the methods to collect the symbol
     * locations and the star locations into additional 2d arrays.
     */
    public void convertInputTo2DArrayAndSaveValidLocations() {
        int rowLength = input.size();
        int colLength = input.get(0).length();

        engineSchematic = new String[rowLength][colLength];
        symbolLocations = new Boolean[rowLength][colLength];
        starLocations = new Boolean[rowLength][colLength];

        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < colLength; col++) {
                engineSchematic[row][col] = input.get(row).substring(col, col + 1);
                collectSymbolLocations(row, col);
                collectStarLocations(row, col);
            }
        }
    }

    /**
     * Creates a 2d array of booleans that have a value of true if the index
     * being checked contains a valid symbol.
     * @param row the current row to check
     * @param col the current column to check
     */
    public void collectSymbolLocations(int row, int col) {
        symbolLocations[row][col] = isValidSymbol(row, col);
    }

    /**
     * Creates a 2d array of booleans that have a value of true if the index
     * being checked contains a "*" symbol.
     * @param row the current row to check
     * @param col the current index to check
     */
    private void collectStarLocations(int row, int col) {
        starLocations[row][col] = engineSchematic[row][col].charAt(0) == 42;
    }

    /**
     * Checks all neighbors (including diagonal) of each index in the 2d array. Calls
     * the method to calculate the sum of parts.
     * Keeps a list of part numbers that are connected to the same gear (star) and
     * calculates the sum of Gear Ratios when there is exactly 2 parts connected to the
     * same star.
     */
    private void checkNeighbors() {
        int[][] deltas = new int[][] {{-1, -1}, {-1, 0}, {-1, 1},
                                      { 0, -1},          { 0, 1},
                                      { 1, -1}, { 1, 0}, { 1, 1}};

        for (int row = 0; row < symbolLocations.length; row++) {
            for (int col = 0; col < symbolLocations[0].length; col++) {

                int[] countOfPartNumbers = new int[]{0};
                List<Integer> partNumbers = new ArrayList<>();

                if (symbolLocations[row][col]) {
                    for (int[] delta : deltas) {
                        int newRow = row + delta[0];
                        int newCol = col + delta[1];

                        if (isValidIndex(newRow, newCol) && isValidNumber(newRow, newCol)) {
                            partNumbers.add(calculateSumOfParts(newRow, newCol, countOfPartNumbers));
                        }
                    }

                    // check if there are exactly 2 parts connected to the star for
                    // valid gear ratio computation
                    if (countOfPartNumbers[0] == 2 && starLocations[row][col]) {
                        sumOfGearRatios += partNumbers.get(0) * partNumbers.get(1);
                    }
                }
            }
        }
    }

    /**
     * Checks if the index is a valid index within the Engine Schematic.
     * @param row the current row to check
     * @param col the current column to check
     * @return a boolean true if the row and column are valid, false otherwise
     */
    private boolean isValidIndex(int row, int col) {
        return row >= 0 && col >= 0 && row < engineSchematic.length && col < engineSchematic[0].length;
    }

    /**
     * Checks if the current value is a valid number.
     * @param row the current row the value to check is in
     * @param col the current column the value to check is in
     * @return a boolean true if the number is valid, false otherwise
     */
    private boolean isValidNumber(int row, int col) {
        return Character.isDigit(engineSchematic[row][col].charAt(0));
    }

    /**
     * Determines if the symbol is valid, meaning not a '.' and not a number.
     * @param row the current row the value to check is in
     * @param col the current column the value to check is in
     * @return a boolean true if the symbol is valid, false otherwise
     */
    private boolean isValidSymbol(int row, int col) {
        return engineSchematic[row][col].charAt(0) != '.' && !isValidNumber(row, col);
    }

    /**
     * Calculates the part number based on what column the number begins and ends.
     * Adds the current part number to the current sum of part numbers and increments
     * the count of part numbers that are attached to the current gear (star).
     * @param row the row the current number is in
     * @param col a column that contains part of the number
     * @param countOfPartNumbers the current count of parts connected to the same star
     * @return the complete part number
     */
    private int calculateSumOfParts(int row, int col, int[] countOfPartNumbers) {
        int startingColumn = getStartingColumn(row, col);
        int endingColumn = getEndingColumn(row, col);
        int lengthOfNumber = endingColumn - startingColumn;
        int partNumber = getPartNumber(row, startingColumn, lengthOfNumber);

        clearProcessedPartNumbers(row, startingColumn, lengthOfNumber);
        sumOfPartNumbers += partNumber;
        countOfPartNumbers[0] = countOfPartNumbers[0] + 1;
        return partNumber;
    }

    /**
     * Gets the starting column of the current number.
     * @param row the current numbers row
     * @param col the current numbers column
     * @return the column that the first digit of the number is in
     */
    private int getStartingColumn(int row, int col) {
        int startingColumn = col;
        // Possible max length of 3-digit part number given this specific input
        if (col > 1 && isValidNumber(row, col - 2) && isValidNumber(row, col - 1)) {
            startingColumn = col - 2;
            col = col - 2;
        } else if (col > 0 && isValidNumber(row, col - 1)) {
            startingColumn = col - 1;
            col = col - 1;
        }
        return startingColumn;
    }

    /**
     * Gets the ending column of the current number.
     * @param row the current numbers row
     * @param col the current numbers column
     * @return the column that the last digit of the number is in
     */
    private int getEndingColumn(int row, int col) {
        int endingColumn = 0;
        while (col < engineSchematic[0].length && Character.isDigit(input.get(row).charAt(col))) {
            endingColumn = col;
            col++;
        }
        return endingColumn;
    }

    /**
     * Calculates the part number based on the length of the number, multiplying
     * to account for the different digit places the number is located.
     * @param row the current row the number is in
     * @param startingColumn the column the number starts in
     * @param lengthOfNumber the length of the current number
     * @return returns the complete part number
     */
    private int getPartNumber(int row, int startingColumn, int lengthOfNumber) {
        int partNumber = 0;
        if (lengthOfNumber == 2) {
            partNumber = 100 * Integer.parseInt(engineSchematic[row][startingColumn]);
            partNumber += 10 * Integer.parseInt(engineSchematic[row][startingColumn + 1]);
            partNumber += Integer.parseInt(engineSchematic[row][startingColumn + 2]);
        } else if (lengthOfNumber == 1) {
            partNumber += 10 * Integer.parseInt(engineSchematic[row][startingColumn]);
            partNumber += Integer.parseInt(engineSchematic[row][startingColumn + 1]);
        } else if (lengthOfNumber == 0) {
            partNumber += Integer.parseInt(engineSchematic[row][startingColumn]);
        }
        return partNumber;
    }

    /**
     * Clears the processed part number so that it is not counted multiple times, sets
     * the value to ".".
     * @param row the current row that the number is in
     * @param startingColumn the column that starts the number
     * @param lengthOfNumber the length of the current number
     */
    private void clearProcessedPartNumbers(int row, int startingColumn, int lengthOfNumber) {
        for (int i = 0; i <= lengthOfNumber; i++) {
            engineSchematic[row][startingColumn + i] = ".";
        }
    }
}
