package main.Advent2022.DayTen;

import main.Tools.LoadFile;

public class DayTen {
    public static void dayTen() {
        LoadFile.loadFile("src/main/Advent2022/DayTen/sampleSignalStrength.txt.txt");
        CathodeRayTube.start();
    }
}
