package main.Advent2023.Day04;

import main.Tools.LoadFile;

public class Day04 {
    public static void day04() {
        //loadFile("src/main/Advent2023/Day04/test.txt");
        LoadFile.loadFile("src/main/Advent2023/Day04/possibleWinners.txt");
        ScratchCards.start();
    }
}