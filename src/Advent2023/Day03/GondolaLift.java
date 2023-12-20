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



    public static void start() {
        launchProgram("1", "2", GondolaLift.class,
                "startDayOne", "startDayTwo");
    }

    public static void startDayOne() {
        GondolaLift gondolaLift = new GondolaLift();
        gondolaLift.convertInputTo2DArrayAndSaveValidLocations();
        gondolaLift.checkNeighbors();
        System.out.println("The answer for part one is : " + sumOfPartNumbers);
    }

    public static void startDayTwo() {
        GondolaLift gondolaLift = new GondolaLift();
        gondolaLift.convertInputTo2DArrayAndSaveValidLocations();
        gondolaLift.checkNeighbors();
        System.out.println("The answer for part two is : " + sumOfGearRatios);
    }

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

    public void collectSymbolLocations(int row, int col) {
        symbolLocations[row][col] = isValidSymbol(row, col);
    }

    private void collectStarLocations(int row, int col) {
        starLocations[row][col] = engineSchematic[row][col].charAt(0) == 42;
    }

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

    private boolean isValidIndex(int row, int col) {
        return row >= 0 && col >= 0 && row < engineSchematic.length && col < engineSchematic[0].length;
    }

    private boolean isValidNumber(int row, int col) {
        return Character.isDigit(engineSchematic[row][col].charAt(0));
    }

    private boolean isValidSymbol(int row, int col) {
        return engineSchematic[row][col].charAt(0) != '.' && !isValidNumber(row, col);
    }

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

    private void clearProcessedPartNumbers(int row, int startingColumn, int lengthOfNumber) {
        for (int i = 0; i <= lengthOfNumber; i++) {
            engineSchematic[row][startingColumn + i] = ".";
        }
    }

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

    private int getEndingColumn(int row, int col) {
        int endingColumn = 0;
        while (col < engineSchematic[0].length && Character.isDigit(input.get(row).charAt(col))) {
            endingColumn = col;
            col++;
        }
        return endingColumn;
    }

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
}
