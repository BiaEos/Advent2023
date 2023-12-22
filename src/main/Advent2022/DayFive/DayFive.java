/**
 * Created by: Alyson
 * Date: 12/22/22
 * Time: 4:07 AM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package main.Advent2022.DayFive;

import main.Tools.LoadFile;

import static main.Advent2022.DayFive.ReorganizeCrates.start;

public class DayFive {

    public static void dayFive() {
        LoadFile.loadFile("src/main/Advent2022/DayFive/cratesProcedure.txt");
        LoadFile.loadFileTo2DArray("src/main/Advent2022/DayFive/cratesStack.txt", 9, 4);
        start();
    }
}
