/**
 * Created by: Alyson
 * Date: 12/19/22
 * Time: 2:52 PM
 * Project Name: Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package Advent2022.DayTwo;

import static Advent2022.DayTwo.RockPaperScissors.playGame;
import static Tools.LoadFile.*;

public class DayTwo {

    public static void dayTwo() {
        loadFile("src/Advent2022/DayTwo/rockPaperScissors.txt");
        playGame();

    }
}
