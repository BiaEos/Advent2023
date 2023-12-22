/**
 * Created by: Alyson
 * Date: 12/27/22
 * Time: 10:51 AM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package main.Advent2022.DaySix;

import main.Tools.LaunchProgram;
import main.Tools.LoadFile;

import java.util.HashSet;
import java.util.List;

public class CommunicationSystem {
    private final String dataStreamInput = LoadFile.inputFromFile().get(0);
    private char[] individualLetters;

    public static void start() {
        LaunchProgram.launchProgram("one", "two", CommunicationSystem.class,
            "startDayOne", "startDayTwo");
    }

    public static void startDayOne() {
        CommunicationSystem communicationSystem = new CommunicationSystem();
        communicationSystem.getIndividualLetters();
        communicationSystem.hasNoMatchingLetter();
    }

    public static void startDayTwo() {
        CommunicationSystem communicationSystem = new CommunicationSystem();
        communicationSystem.getIndividualLetters();
        communicationSystem.fourteenIndividualLetters();
    }

    private void getIndividualLetters() {
        String[] dataStream = (dataStreamInput.split(""));
        individualLetters = new char[dataStream.length];
        for (int letter = 0; letter < dataStream.length; letter++) {
            individualLetters[letter] = dataStream[letter].charAt(0);
        }
    }

    private void hasNoMatchingLetter () {
        Integer[] values = new Integer[6];
        for (int letter = 0; letter < individualLetters.length - 3; letter++) {
            values[0] = Character.compare(individualLetters[letter], individualLetters[letter + 1]);
            values[1] = Character.compare(individualLetters[letter], individualLetters[letter + 2]);
            values[2] = Character.compare(individualLetters[letter], individualLetters[letter + 3]);
            values[3] = Character.compare(individualLetters[letter + 1], individualLetters[letter + 2]);
            values[4] = Character.compare(individualLetters[letter + 1], individualLetters[letter + 3]);
            values[5] = Character.compare(individualLetters[letter + 2], individualLetters[letter + 3]);
            if (!List.of(values).contains(0)) {
                System.out.println("The first marker is at: Letter " + (letter + 4));
                break;
            }
        }
    }

    private void fourteenIndividualLetters() {
        HashSet<Character> lettersToCheck = new HashSet<>();
        outerloop: for (int letter = 0; letter < individualLetters.length - 14; letter++) {
            for (int nextLetter = 0; nextLetter < 14; nextLetter++) {
                lettersToCheck.add(individualLetters[letter + nextLetter]);
                if (lettersToCheck.size() == 14) {
                    System.out.println("The first marker is at: Letter " + (letter + 14));
                    break outerloop;
                }
            }
            lettersToCheck.clear();
        }
    }
}
