/**
 * Created by: Alyson
 * Date: 12/20/22
 * Time: 9:40 AM
 * Project Name: Advent2022
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package Advent2022.DayFour;

import static Tools.LaunchProgram.launchProgram;
import java.util.ArrayList;
import static Tools.LoadFile.inputFromFile;

public class CampCleanup {
    private final ArrayList<String> cleanupAreas = new ArrayList<>(inputFromFile());
    private int elfOneAreaOne;
    private int elfOneAreaTwo;
    private int elfTwoAreaOne;
    private int elfTwoAreaTwo;
    private int areasCompletelyContained = 0;
    private int areasPartiallyContained = 0;

    public static void start() {
        launchProgram("one", "two", CampCleanup.class,
            "startDayOne", "startDayTwo");
    }

    public static void startDayOne() {
        CampCleanup campCleanup = new CampCleanup();
        campCleanup.splitAndCountInput();
        campCleanup.printCompletelyContainedTotal();
    }

    public static void startDayTwo() {
        CampCleanup campCleanup = new CampCleanup();
        campCleanup.splitAndCountInput();
        campCleanup.printAnyOverlapTotal();
    }

    private void splitAndCountInput() {
        for (String cleanupArea : cleanupAreas) {
            int indexA = cleanupArea.indexOf(",");
            String elfOne = cleanupArea.substring(0, indexA);
            String elfTwo = cleanupArea.substring(indexA + 1);
            int indexB = elfOne.indexOf("-");
            elfOneAreaOne = Integer.parseInt(elfOne.substring(0, indexB));
            elfOneAreaTwo = Integer.parseInt(elfOne.substring(indexB + 1));
            int indexC = elfTwo.indexOf("-");
            elfTwoAreaOne = Integer.parseInt(elfTwo.substring(0, indexC));
            elfTwoAreaTwo = Integer.parseInt(elfTwo.substring(indexC + 1));
            countAreasContained();
        }
    }

    private void countAreasContained() {
        if (elfOneAreaOne <= elfTwoAreaOne && elfOneAreaTwo >= elfTwoAreaTwo ||
            elfTwoAreaOne <= elfOneAreaOne && elfTwoAreaTwo >= elfOneAreaTwo) {
            areasCompletelyContained++;
        } else if (elfOneAreaOne <= elfTwoAreaOne && elfOneAreaTwo >= elfTwoAreaOne ||
            elfOneAreaOne <= elfTwoAreaTwo && elfOneAreaTwo >= elfTwoAreaTwo) {
            areasPartiallyContained++;
        }
    }

    private void printCompletelyContainedTotal() {
        System.out.println("The number of areas that are contained by another area is " +
            areasCompletelyContained);
    }

    private void printAnyOverlapTotal() {
        System.out.println("The number of areas that have any overlap is " +
            (areasPartiallyContained + areasCompletelyContained));
    }
}
