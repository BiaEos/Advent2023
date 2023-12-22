package main.Advent2023.Day03;

import main.Tools.LoadFile;

public class Day03 {
    public static void day03() {
        //loadFile("src/main/Advent2023/Day03/test.txt");
        LoadFile.loadFile("src/main/Advent2023/Day03/engineSchematic.txt");
        GondolaLift.start();
    }
}