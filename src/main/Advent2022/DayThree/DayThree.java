/**
 * Created by: Alyson
 * Date: 12/20/22
 * Time: 4:30 AM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package main.Advent2022.DayThree;

import main.Tools.LoadFile;

import static main.Advent2022.DayThree.SortRucksack.*;

public class DayThree {
    public static void dayThree() {
        LoadFile.loadFile("src/main/Advent2022/DayThree/rucksackItems.txt");
        findPriority();
    }
}
