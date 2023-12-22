/**
 * Created by: Alyson
 * Date: 12/27/22
 * Time: 10:47 AM
 * Project Name: main.Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package main.Advent2022.DaySix;

import main.Tools.LoadFile;

public class DaySix {
    public static void daySix() {
        LoadFile.loadFile("src/main/Advent2022/DaySix/data-streamBuffer.txt");
        CommunicationSystem.start();
    }
}
