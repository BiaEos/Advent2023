package Advent2022.DayTen;

import java.util.ArrayList;
import java.util.List;

import static Tools.LaunchProgram.launchProgram;
import static Tools.LoadFile.inputFromFile;

public class CathodeRayTube {
    private final List<String> instructionsToFollow = new ArrayList<>(inputFromFile());
    private int cycleCount = 0;
    private int signalStrength = 0;
    private int x = 1;
    private char[][] spriteDrawing = new char[6][40];
    int row = 5;
    int pixelLocation = 0;

    public static void start() {
        launchProgram("1", "2", CathodeRayTube.class,
            "partOne", "partTwo");
    }

    public static void partOne() {
        CathodeRayTube cathodeRayTube = new CathodeRayTube();
        cathodeRayTube.iterateThroughInstructions();
        cathodeRayTube.printAnswer();
    }

    public static void partTwo() {
        CathodeRayTube cathodeRayTube = new CathodeRayTube();
        cathodeRayTube.iterateThroughInstructions();
        cathodeRayTube.printAnswerD2();
    }

    private void iterateThroughInstructions() {
        for (int i = 0; i < instructionsToFollow.size(); i++) {
            if (isAddx(i)) {
                drawSprite();
                cycleCount++;
                checkSignalStrengthCycle();
                drawSprite();
                cycleCount++;
                checkSignalStrengthCycle();
                makeSignalStrengthMods(i);
            } else {
                drawSprite();
                cycleCount++;
                checkSignalStrengthCycle();
            }
        }
    }

    private boolean isAddx(int instruction) {
        return (instructionsToFollow.get(instruction).contains("addx"));
    }

    private void checkSignalStrengthCycle() {
        if (cycleCount == 20 || (cycleCount - 20) % 40 == 0) {
            int currentSignalStrength = cycleCount * x;
            signalStrength += currentSignalStrength;
        }
    }

    private void makeSignalStrengthMods(int instruction) {
        int xValue = Integer.parseInt(instructionsToFollow.get(instruction).substring(5));
        x += xValue;
    }

    private void drawSprite() {
        int[] sprite = new int[] {x - 1, x, x + 1};
        if (sprite[0] == pixelLocation || sprite[1] == pixelLocation || sprite[2] == pixelLocation) {
            spriteDrawing[row][pixelLocation] = '#';
        } else {
            spriteDrawing[row][pixelLocation] = ' ';
        }
        if (isLastPixelInRow(pixelLocation)) {
            row--;
            pixelLocation = 0;
        } else {
            pixelLocation++;
        }
    }

    private boolean isLastPixelInRow(int pixelLocation) {
        return pixelLocation == 39 || pixelLocation == 79 || pixelLocation == 119 ||
            pixelLocation == 159 || pixelLocation == 199 || pixelLocation == 239;
    }

    private void printAnswer() {
        System.out.println("The signal strength is " + signalStrength);
    }

    private void printAnswerD2() {
        int row = 5;
        while (row >= 0) {
            printRow(row);
            System.out.println();
            row--;
        }
    }

    private void printRow(int row) {
        for (int i = 0; i < 40; i++) {
            System.out.print(spriteDrawing[row][i]);
        }
    }
}
