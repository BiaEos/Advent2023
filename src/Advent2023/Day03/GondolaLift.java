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
    private static int resultingSum = 0;
    private static int gearRatio = 0;



    public static void start() {
        launchProgram("1", "2", GondolaLift.class,
                "startDayOne", "startDayTwo");
    }

    public static void startDayOne() {
        GondolaLift gondolaLift = new GondolaLift();
        gondolaLift.convertInputTo2DArrayAndSaveValidLocations();
        gondolaLift.checkNeighbors();
        System.out.println("The answer for part one is : " + resultingSum);
    }

    public static void startDayTwo() {
        GondolaLift gondolaLift = new GondolaLift();
        gondolaLift.convertInputTo2DArrayAndSaveValidLocations();
        gondolaLift.checkNeighbors();
        System.out.println("The answer for part two is : " + gearRatio);
    }

    public void convertInputTo2DArrayAndSaveValidLocations() {
        engineSchematic = new String[input.size()][input.get(0).length()];
        symbolLocations = new Boolean[engineSchematic.length][engineSchematic[0].length];
        starLocations = new Boolean[engineSchematic.length][engineSchematic[0].length];
        for (int row = 0; row < engineSchematic.length; row++) {
            for (int col = 0; col < engineSchematic[0].length; col++) {
                engineSchematic[row][col] = input.get(row).substring(col, col + 1);
                symbolLocations[row][col] = false;
                starLocations[row][col] = false;
                collectSymbolLocations(row, col);
                collectStarLocations(row, col);
            }
        }
    }

    public void collectSymbolLocations(int row, int col) {
        //String currentCell = inputFromFile2DArray[row][col];
        if (isValidSymbol(row, col)) {
            //System.out.println(currentCell);
            symbolLocations[row][col] = true;
        }
    }

    private void collectStarLocations(int row, int col) {
        String currentCell = engineSchematic[row][col];
        if (currentCell.charAt(0) == 42) {
            System.out.println(currentCell);
            starLocations[row][col] = true;
        }
    }

/*    private void checkNeighbors() {
        // if the symbol location is true check input2dArray
        for (int row = 0; row < symbolLocations.length; row++) {
            for (int col = 0; col < symbolLocations[0].length; col++) {
                int[] countOfPartNumbers = new int[] {0};
                List<Integer> resultingNumbers = new ArrayList<>();
                if(symbolLocations[row][col]) {
                    // one row up (center, left, right)
                    if (isValidIndex(row - 1, col - 1) && isValidNumber(row - 1, col - 1)) {
                        resultingNumbers.add(getPartNumber(row - 1, col - 1, countOfPartNumbers));
                    }
                    if (isValidIndex(row - 1, col) && isValidNumber(row - 1, col)) {
                        resultingNumbers.add(getPartNumber(row - 1, col, countOfPartNumbers));
                    }
                    if (isValidIndex(row - 1, col + 1) && isValidNumber(row - 1, col + 1)) {
                        resultingNumbers.add(getPartNumber(row - 1, col + 1, countOfPartNumbers));
                    }
                    // one row down (center, left, right)
                    if (isValidIndex(row + 1, col - 1) && isValidNumber(row + 1, col - 1)) {
                        resultingNumbers.add(getPartNumber(row + 1, col - 1, countOfPartNumbers));
                    }
                    if (isValidIndex(row + 1, col) && isValidNumber(row + 1, col)) {
                        resultingNumbers.add(getPartNumber(row + 1, col, countOfPartNumbers));
                    }
                    if (isValidIndex(row + 1, col + 1) && isValidNumber(row + 1, col + 1)) {
                        resultingNumbers.add(getPartNumber(row + 1, col + 1, countOfPartNumbers));
                    }
                    // cells to the left
                    if (isValidIndex(row, col - 1) && isValidNumber(row, col - 1)) {
                        resultingNumbers.add(getPartNumber(row, col - 1, countOfPartNumbers));
                    }
                    // cells to the right
                    if (isValidIndex(row, col + 1) && isValidNumber(row, col + 1)) {
                        resultingNumbers.add(getPartNumber(row, col + 1, countOfPartNumbers));
                    }
                    if (countOfPartNumbers[0] == 2 && starLocations[row][col]) {
                        gearRatio += resultingNumbers.get(0) * resultingNumbers.get(1);
                        System.out.println(resultingNumbers);
                    }
                }
            }
        }
    }*/

    private void checkNeighbors() {
        int[][] deltas = new int[][] {{-1, -1}, {-1, 0}, {-1, 1},
                                      { 0, -1},          { 0, 1},
                                      { 1, -1}, { 1, 0}, { 1, 1}};

        for (int row = 0; row < symbolLocations.length; row++) {
            for (int col = 0; col < symbolLocations[0].length; col++) {

                int[] countOfPartNumbers = new int[]{0};
                List<Integer> resultingNumbers = new ArrayList<>();

                if (symbolLocations[row][col]) {
                    for (int i = 0; i < deltas.length; i++) {
                        int[] coordinates = deltas[i];
                        int newRow = row + coordinates[0];
                        int newCol = col + coordinates[1];

                        if (isValidIndex(newRow, newCol) && isValidNumber(newRow, newCol)) {
                            resultingNumbers.add(getPartNumber(newRow, newCol, countOfPartNumbers));
                        }
                    }

                    if (countOfPartNumbers[0] == 2 && starLocations[row][col]) {
                        gearRatio += resultingNumbers.get(0) * resultingNumbers.get(1);
                        System.out.println(resultingNumbers);
                    }
                }
            }
        }
    }

    private boolean isValidIndex(int row, int col) {
        return row >= 0 && col >= 0 && row < engineSchematic.length && col < engineSchematic[0].length;
    }

    private boolean isValidNumber(int row, int col) {
        String currentCell = engineSchematic[row][col];
        return Character.isDigit(currentCell.charAt(0));
    }

    private boolean isValidSymbol(int row, int col) {
        String currentCell = engineSchematic[row][col];
        return currentCell.charAt(0) != '.' && !isValidNumber(row, col);
    }

    private int getPartNumber(int row, int col, int[] countOfPartNumbers) {
        int startingColumn = col;
        int endingColumn = col;
        int resultingNumber = 0;
        // check for start of possible 3-digit number and set as starting index to check later for size of number
        if (col > 1 && isValidNumber(row, col - 2) && isValidNumber(row, col - 1)) {
            startingColumn = col - 2;
            col = col - 2;
        } else if (col > 0 && isValidNumber(row, col - 1)) {
            startingColumn = col - 1;
            col = col - 1;
        }

        // get the ending index to figure out what length of number to store
        while (col < engineSchematic[0].length && Character.isDigit(input.get(row).charAt(col))) {
            endingColumn = col;
            col++;
        }
        if (endingColumn - startingColumn == 2) {
            resultingNumber = 100 * Integer.valueOf(engineSchematic[row][startingColumn]);
            engineSchematic[row][startingColumn] = ".";
            resultingNumber += 10 * Integer.valueOf(engineSchematic[row][startingColumn + 1]);
            engineSchematic[row][startingColumn + 1] = ".";
            resultingNumber += Integer.valueOf(engineSchematic[row][startingColumn + 2]);
            engineSchematic[row][startingColumn + 2] = ".";
        } else if (endingColumn - startingColumn == 1) {
            resultingNumber += 10 * Integer.valueOf(engineSchematic[row][startingColumn]);
            engineSchematic[row][startingColumn] = ".";
            resultingNumber += Integer.valueOf(engineSchematic[row][startingColumn + 1]);
            engineSchematic[row][startingColumn + 1] = ".";
        } else if (endingColumn == startingColumn) {
            resultingNumber += Integer.valueOf(engineSchematic[row][startingColumn]);
            engineSchematic[row][startingColumn] = ".";
        }

        System.out.println("This number is : " + resultingNumber);
        resultingSum += resultingNumber;
        countOfPartNumbers[0] = countOfPartNumbers[0] + 1;
        return resultingNumber;
    }
}
