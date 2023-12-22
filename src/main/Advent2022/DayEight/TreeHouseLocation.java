/**
 * Created by: Alyson
 * Date: 1/3/23
 * Time: 7:12 PM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package main.Advent2022.DayEight;

import main.Tools.LaunchProgram;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;

public class TreeHouseLocation {
    private final int[][] heightOfTrees = new int[99][99];
    private final List<Integer> treeScore = new ArrayList<>();
    private final List<String> alreadyMarkedTrees = new ArrayList<>();
    private int visible = 0;

    public static void start() {
        LaunchProgram.launchProgram("One", "Two", TreeHouseLocation.class,
            "startDayOne", "startDayTwo");
    }

    public static void startDayOne() {
        TreeHouseLocation treeHouseLocation = new TreeHouseLocation();
        treeHouseLocation.getTreeMap();
        treeHouseLocation.visibleTrees();
        treeHouseLocation.printDayOne();
    }

    public static void startDayTwo() {
        TreeHouseLocation treeHouseLocation = new TreeHouseLocation();
        treeHouseLocation.getTreeMap();
        treeHouseLocation.getScenicScore();
        treeHouseLocation.getHighestScenicScore();
    }

    private void getTreeMap() {
        Scanner scanner = null;
        try {
            Reader reader = new FileReader("src/main/Advent2022/DayEight/mapHeightOfTrees.txt");
            scanner = new Scanner(reader);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        for (int row = 1; row < 100; row++) {
            String line = Objects.requireNonNull(scanner).nextLine();
            for (int col = 1; col < 100; col++) {
                int number = Integer.parseInt(line.substring(col - 1, col));
                heightOfTrees[row - 1][col - 1] = number;
            }
        }
    }

    private void visibleTrees() {
        // get visible from left side
        visible = (99 * 2) + (97 * 2);
        int tallestTreeInRow;
        int currentTree;
        for (int row = 1; row < 98; row++) {
            tallestTreeInRow = heightOfTrees[row][0];
            for (int col = 1; col < 98; col++) {
                currentTree = heightOfTrees[row][col];
                if (currentTree > tallestTreeInRow) {
                    tallestTreeInRow = currentTree;
                    if (!alreadyMarkedTrees.contains(row + "/" + col)) {
                        alreadyMarkedTrees.add(row + "/" + col);
                        visible++;
                    }
                }
            }
        }
        // get visible from right side
        for (int row = 97; row > 0; row--) {
            tallestTreeInRow = heightOfTrees[row][98];
            for (int col = 97; col > 0; col--) {
                currentTree = heightOfTrees[row][col];
                if (currentTree > tallestTreeInRow) {
                    tallestTreeInRow = currentTree;
                    if (!alreadyMarkedTrees.contains(row + "/" + col)) {
                        alreadyMarkedTrees.add(row + "/" + col);
                        visible++;
                    }
                }
            }
        }
        // get visible from top side
        for (int col = 1; col < 98; col++) {
            tallestTreeInRow = heightOfTrees[0][col];
            for (int row = 1; row < 98; row++) {
                currentTree = heightOfTrees[row][col];
                if (currentTree > tallestTreeInRow) {
                    tallestTreeInRow = currentTree;
                    if (!alreadyMarkedTrees.contains(row + "/" + col)) {
                        alreadyMarkedTrees.add(row + "/" + col);
                        visible++;
                    }
                }
            }
        }
        // get visible from bottom side
        for (int col = 97; col > 0; col--) {
            tallestTreeInRow = heightOfTrees[98][col];
            for (int row = 97; row > 0; row--) {
                currentTree = heightOfTrees[row][col];
                if (currentTree > tallestTreeInRow) {
                    tallestTreeInRow = currentTree;
                    if (!alreadyMarkedTrees.contains(row + "/" + col)) {
                        alreadyMarkedTrees.add(row + "/" + col);
                        visible++;
                    }
                }
            }
        }
    }

    private void printDayOne() {
        System.out.println("The number of visible trees: " + visible);
    }

    private void getScenicScore() {
        for (int row = 0; row < 99; row++) {
            for (int col = 0; col < 99; col++) {
                if (!isEndTree(row, col)) {
                    int left = checkLeft(row, col);
                    int right = checkRight(row, col);
                    int up = checkUp(row, col);
                    int down = checkDown(row, col);
                    treeScore.add(left * right * down * up);
                }
            }
        }
    }

    private boolean isEndTree(int row, int col) {
        return row == 0 || row == 98 || col == 0 || col == 98;
    }

    private int checkLeft(int row, int col) {
        int leftCount = 1;
        int treeHeight = heightOfTrees[row][col];
        int nextTreeHeight = 0;
        while (nextTreeHeight < treeHeight) {
            if ((col - 1) > 0) {
                col -= 1;
                nextTreeHeight = heightOfTrees[row][col];
                if (nextTreeHeight < treeHeight) {
                    leftCount++;
                }
            }
            else { break; }
        }
        return leftCount;
    }

    private int checkRight(int row, int col) {
        int rightCount = 1;
        int treeHeight = heightOfTrees[row][col];
        int nextTreeHeight = 0;
        while (nextTreeHeight < treeHeight) {
            if ((col + 1) < 98) {
                col += 1;
                nextTreeHeight = heightOfTrees[row][col];
                if (nextTreeHeight < treeHeight) {
                    rightCount++;
                }
            }
            else { break; }
        }
        return rightCount;
    }

    private int checkUp(int row, int col) {
        int upCount = 1;
        int treeHeight = heightOfTrees[row][col];
        int nextTreeHeight = 0;
        while (nextTreeHeight < treeHeight) {
            if ((row - 1) > 0) {
                row -= 1;
                nextTreeHeight = heightOfTrees[row][col];
                if (nextTreeHeight < treeHeight) {
                    upCount++;
                }
            }
            else { break; }
        }
        return upCount;
    }

    private int checkDown(int row, int col) {
        int downCount = 1;
        int treeHeight = heightOfTrees[row][col];
        int nextTreeHeight = 0;
        while (nextTreeHeight < treeHeight) {
            if ((row + 1) < 98) {
                row += 1;
                nextTreeHeight = heightOfTrees[row][col];
                if (nextTreeHeight < treeHeight) {
                    downCount++;
                }
            }
            else { break; }
        }
        return downCount;
    }

    private void getHighestScenicScore() {
        System.out.println("Max scenic score is: " + Collections.max(treeScore));
    }
}
