package main.Advent2023.Day05;

import static main.Tools.LoadFile.loadFile;

public class Day05 {
    public static void day05() {
        loadFile("src/main/Advent2023/Day05/Resources/test.txt");
        //loadFile("src/main/Advent2023/Day05/Resources/almanac.txt");
        FoodProduction.start();
    }
}