/**
 * Created by: Alyson
 * Date: 12/22/22
 * Time: 4:22 AM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package main.Advent2022.DayFive;

import main.Tools.LaunchProgram;
import main.Tools.LoadFile;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Pattern;

public class ReorganizeCrates {
    private String[][] crateStack;
    private String[][] stackOrganization;
    private int moveAmount;
    private int fromColumn;
    private int toColumn;

    public static void start() {
        LaunchProgram.launchProgram("one", "two", ReorganizeCrates.class,
            "startDayOne", "startDayTwo");
    }

    public static void startDayOne() {
        ReorganizeCrates reorganizeCrates = new ReorganizeCrates();
        reorganizeCrates.makeSpaceForMoves();
        reorganizeCrates.correctStackArrangement();
        reorganizeCrates.makeIndividualMoves();
        reorganizeCrates.printResults();
    }

    public static void startDayTwo() {
        ReorganizeCrates reorganizeCrates = new ReorganizeCrates();
        reorganizeCrates.makeSpaceForMoves();
        reorganizeCrates.correctStackArrangement();
        reorganizeCrates.makeGroupMoves();
        reorganizeCrates.printResults();
    }

    private void makeSpaceForMoves() {
        crateStack = LoadFile.inputFromFile2DArray();
        stackOrganization = new String[81][9];
        for (int i = 0; i < 81; i++) {
            for (int j = 0; j < 9; j++) {
                stackOrganization[i][j] = "Empty";
            }
        }
    }

    private void correctStackArrangement() {
        int changeIndex = 7;
        for (int row = 0; row < 8; row++) {
            if (changeIndex > -1) {
                System.arraycopy(crateStack[row], 0, stackOrganization[changeIndex], 0, 9);
                changeIndex--;
            }
        }
    }

    private void makeIndividualMoves() {
        List<String> movesToMake = LoadFile.inputFromFile();
        for (String moveToMake : movesToMake) {
            Pattern movePattern = Pattern.compile("\\D+\\s");
            String[] moves = moveToMake.split(String.valueOf(movePattern));
            moveAmount = Integer.parseInt(moves[1]);
            fromColumn = Integer.parseInt(moves[2]) - 1;
            toColumn = Integer.parseInt(moves[3]) - 1;
            moveIndividualCrates();
        }
    }

    private void moveIndividualCrates() {
        int emptyToRow = findTopCell(toColumn) + 1;
        int emptyFromRow = findTopCell(fromColumn) + 1;
        int count = 0;
        for (int move = moveAmount; move > 0; move--) {
            if (findTopCell(toColumn) == -1) {
                emptyToRow = 0;
            }
            stackOrganization[emptyToRow + count][toColumn] =
                stackOrganization[emptyFromRow - 1 - count][fromColumn];
            stackOrganization[emptyFromRow - 1 - count][fromColumn] = "Empty";
            count++;
        }
    }

    private void makeGroupMoves() {
        List<String> movesToMake = LoadFile.inputFromFile();
        for (String moveToMake : movesToMake) {
            Pattern movePattern = Pattern.compile("\\D+\\s");
            String[] moves = moveToMake.split(String.valueOf(movePattern));
            moveAmount = Integer.parseInt(moves[1]);
            fromColumn = Integer.parseInt(moves[2]) - 1;
            toColumn = Integer.parseInt(moves[3]) - 1;
            moveGroupCrates();
        }
    }

    private void moveGroupCrates() {
        int emptyToRow = findTopCell(toColumn) + 1;
        int emptyFromRow = findTopCell(fromColumn) + 1;
        int count = 0;
        for (int move = moveAmount; move > 0; move--) {
            if (findTopCell(toColumn) == -1) {
                emptyToRow = 0;
            }
            stackOrganization[emptyToRow + count][toColumn] =
                stackOrganization[emptyFromRow - move][fromColumn];
            stackOrganization[emptyFromRow - move][fromColumn] = "Empty";
            count++;
        }
    }

    private int findTopCell(int column) {
        int topCell = 0;
        for (int row = 80; row > 0; row--) {
            if (!isEmpty(stackOrganization[row][column])) {
                topCell = row;
                break;
            } else if (isEmpty(stackOrganization[0][column])) {
                topCell = -1;
                break;
            }
        }
        return topCell;
    }

    private boolean isEmpty(@NotNull String testEmpty) {
        return testEmpty.equals("Empty")  || testEmpty.equals(" ");
    }

    private void printResults() {
        StringBuilder answer = new StringBuilder();
        for (int col = 0; col < 9; col++) {
            int topCell = findTopCell(col);
            answer.append(stackOrganization[topCell][col]);
        }
        System.out.print("The answer is: " + answer);
    }
}
