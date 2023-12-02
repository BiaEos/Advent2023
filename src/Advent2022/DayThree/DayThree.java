/**
 * Created by: Alyson
 * Date: 12/20/22
 * Time: 4:30 AM
 * Project Name: Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package Advent2022.DayThree;

import static Advent2022.DayThree.SortRucksack.*;
import static Tools.LoadFile.loadFile;

public class DayThree {
    public static void dayThree() {
        loadFile("src/Advent2022/DayThree/rucksackItems.txt");
        findPriority();
    }
}
