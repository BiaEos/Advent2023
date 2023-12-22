/**
 * Created by: Alyson
 * Date: 12/19/22
 * Time: 7:53 AM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/

package main.Advent2022.DayOne;

import main.Tools.LoadFile;

import static main.Advent2022.DayOne.CalorieCounter.*;

public class DayOne {

    public static void dayOne() {
        LoadFile.loadFile("src/main/Advent2022/DayOne/calories.txt");
        whichElf();
    }
}
