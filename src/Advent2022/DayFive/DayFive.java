/**
 * Created by: Alyson
 * Date: 12/22/22
 * Time: 4:07 AM
 * Project Name: Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package Advent2022.DayFive;

import static Advent2022.DayFive.ReorganizeCrates.start;
import static Tools.LoadFile.*;

public class DayFive {

    public static void dayFive() {
        loadFile("src/Advent2022/DayFive/cratesProcedure.txt");
        loadFileTo2DArray("src/Advent2022/DayFive/cratesStack.txt", 9, 4);
        start();
    }
}
