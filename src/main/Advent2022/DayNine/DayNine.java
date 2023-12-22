/**
 * Created by: Alyson
 * Date: 1/31/23
 * Time: 12:32 PM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package main.Advent2022.DayNine;

import main.Tools.LoadFile;

public class DayNine {
    public static void dayNine() {
        LoadFile.loadFile("src/main/Advent2022/DayNine/seriesOfMotions.txt");
        RopeBridge.start();
    }
}
