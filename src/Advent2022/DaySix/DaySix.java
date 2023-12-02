/**
 * Created by: Alyson
 * Date: 12/27/22
 * Time: 10:47 AM
 * Project Name: Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package Advent2022.DaySix;

import static Advent2022.DaySix.CommunicationSystem.start;
import static Tools.LoadFile.loadFile;

public class DaySix {
    public static void daySix() {
        loadFile("src/Advent2022/DaySix/data-streamBuffer.txt");
        start();
    }
}
