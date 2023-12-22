/**
 * Created by: Alyson
 * Date: 12/19/22
 * Time: 2:42 PM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package main.Advent2022.DayTwo;

import main.Tools.LoadFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RockPaperScissors {
    private final List<String> roundsOfRPS = new ArrayList<>(LoadFile.inputFromFile());
    private int valueOfElfPlay;
    private int valueOfMyPlay;
    private int valueOfElfPlayWLD;
    private int valueOfMyPlayWLD;
    private char elfPlay;
    private char myPlay;
    private int myScore = 0;
    private int elfScore = 0;

    public static void playGame() {
        RockPaperScissors rockPaperScissors = new RockPaperScissors();
        System.out.println("Do you want Part 1 or Part 2: (Type either One or Two)");
        Scanner pickPart = new Scanner(System.in);
        String partChosen = pickPart.next();
        if (partChosen.equalsIgnoreCase("one")) {
            rockPaperScissors.winGame();
        } else if (partChosen.equalsIgnoreCase("two")) {
            rockPaperScissors.winOrLoseGame();
        } else if (partChosen.equalsIgnoreCase("exit")) {
            System.exit(0);
        } else {
            System.out.println("Please choose 1, 2, or Exit");
            playGame();
        }
    }

    private void winGame() {
        for (String roundOfRPS : roundsOfRPS) {
            elfPlay = roundOfRPS.charAt(0);
            myPlay = roundOfRPS.charAt(roundOfRPS.length() - 1);
            scoreIndividualRoundPtOne();

            int win = 6;
            if (valueOfElfPlay == valueOfMyPlay) {
                int draw = 3;
                myScore += valueOfMyPlay + draw;
                elfScore += valueOfElfPlay + draw;
            } else if (valueOfElfPlay == 1 && valueOfMyPlay == 2) {
                myScore += valueOfMyPlay + win;
                elfScore += valueOfElfPlay;
            } else if (valueOfElfPlay == 1 && valueOfMyPlay == 3) {
                myScore += valueOfMyPlay;
                elfScore += valueOfElfPlay + win;
            } else if (valueOfElfPlay == 2 && valueOfMyPlay == 1) {
                myScore += valueOfMyPlay;
                elfScore += valueOfElfPlay + win;
            } else if (valueOfElfPlay == 2 && valueOfMyPlay == 3) {
                myScore += valueOfMyPlay + win;
                elfScore += valueOfElfPlay;
            } else if (valueOfElfPlay == 3 && valueOfMyPlay == 1) {
                myScore += valueOfMyPlay + win;
                elfScore += valueOfElfPlay;
            } else if (valueOfElfPlay == 3 && valueOfMyPlay == 2) {
                myScore += valueOfMyPlay;
                elfScore += valueOfElfPlay + win;
            }
        }
        System.out.println("My score is: " + myScore);
        System.out.println("Elf score is: " + elfScore);
    }

    private void scoreIndividualRoundPtOne() {
        switch (elfPlay) {
            case 'A' -> valueOfElfPlay = 1;
            case 'B' -> valueOfElfPlay = 2;
            case 'C' -> valueOfElfPlay = 3;
            default -> System.out.println("Incorrect entry");
        }
        switch (myPlay) {
            case 'X' -> valueOfMyPlay = 1;
            case 'Y' -> valueOfMyPlay = 2;
            case 'Z' -> valueOfMyPlay = 3;
            default -> System.out.println("Incorrect entry");
        }
    }

    private void winOrLoseGame() {
        for (String roundOfRPS : roundsOfRPS) {
            elfPlay = roundOfRPS.charAt(0);
            myPlay = roundOfRPS.charAt(roundOfRPS.length() - 1);
            scoreIndividualRoundPtTwo();
            int rock = 1;
            int paper = 2;
            int scissors = 3;

            if (valueOfElfPlay == 1 && valueOfMyPlayWLD == 0) {
                myScore += scissors + valueOfMyPlayWLD;
                elfScore += rock + valueOfElfPlayWLD;
            } else if (valueOfElfPlay == 1 && valueOfMyPlayWLD == 3) {
                myScore += rock + valueOfMyPlayWLD;
                elfScore += rock + valueOfElfPlayWLD;
            } else if (valueOfElfPlay == 1 && valueOfMyPlayWLD == 6) {
                myScore += paper + valueOfMyPlayWLD;
                elfScore += rock + valueOfElfPlayWLD;
            } else if (valueOfElfPlay == 2 && valueOfMyPlayWLD == 0) {
                myScore += rock + valueOfMyPlayWLD;
                elfScore += paper + valueOfElfPlayWLD;
            } else if (valueOfElfPlay == 2 && valueOfMyPlayWLD == 3) {
                myScore += paper + valueOfMyPlayWLD;
                elfScore += paper + valueOfElfPlayWLD;
            } else if (valueOfElfPlay == 2 && valueOfMyPlayWLD == 6) {
                myScore += scissors + valueOfMyPlayWLD;
                elfScore += paper + valueOfElfPlayWLD;
            } else if (valueOfElfPlay == 3 && valueOfMyPlayWLD == 0) {
                myScore += paper + valueOfMyPlayWLD;
                elfScore += scissors + valueOfElfPlayWLD;
            } else if (valueOfElfPlay == 3 && valueOfMyPlayWLD == 3) {
                myScore += scissors + valueOfMyPlayWLD;
                elfScore += scissors + valueOfElfPlayWLD;
            } else if (valueOfElfPlay == 3 && valueOfMyPlayWLD == 6) {
                myScore += rock + valueOfMyPlayWLD;
                elfScore += scissors + valueOfElfPlayWLD;
            }
        }
        System.out.println("My score is: " + myScore);
        System.out.println("Elf score is: " + elfScore);
    }

    private void scoreIndividualRoundPtTwo() {
        switch (elfPlay) {
            case 'A' -> valueOfElfPlay = 1;
            case 'B' -> valueOfElfPlay = 2;
            case 'C' -> valueOfElfPlay = 3;
            default -> System.out.println("Incorrect entry");
        }
        switch (myPlay) {
            case 'X' -> {
                valueOfMyPlayWLD = 0;
                valueOfElfPlayWLD = 6;
            }
            case 'Y' -> {
                valueOfMyPlayWLD = 3;
                valueOfElfPlayWLD = 3;
            }
            case 'Z' -> {
                valueOfMyPlayWLD = 6;
                valueOfElfPlayWLD = 0;
            }
            default -> System.out.println("Incorrect entry");
        }
    }
}
