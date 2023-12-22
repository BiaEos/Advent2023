package main.Advent2023.Day02;

import main.Tools.LoadFile;

public class Day02 {
    public static void day02() {
        //loadFile("src/main/Advent2023/Day02/test.txt");
        LoadFile.loadFile("src/main/Advent2023/Day02/cubeInformation.txt");
        CubeGame.start();
    }
}