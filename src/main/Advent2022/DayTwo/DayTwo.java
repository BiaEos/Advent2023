/**
 * Created by: Alyson
 * Date: 12/19/22
 * Time: 2:52 PM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package main.Advent2022.DayTwo;

import main.Tools.LoadFile;

public class DayTwo {

    public static void dayTwo() {
        LoadFile.loadFile("src/main/Advent2022/DayTwo/rockPaperScissors.txt");
        RockPaperScissors.playGame();

    }
}
