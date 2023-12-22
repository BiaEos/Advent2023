/**
 * Created by: Alyson
 * Date: 12/20/22
 * Time: 9:38 AM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package main.Advent2022.DayFour;

import main.Tools.LoadFile;

public class DayFour {
    public static void dayFour() {
        LoadFile.loadFile("src/main/Advent2022/DayFour/sectionAssignment.txt");
        CampCleanup.start();
    }
}
