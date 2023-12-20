package Advent2023.Day04;

import Advent2023.Day03.GondolaLift;

import java.util.ArrayList;
import java.util.List;

import static Tools.LaunchProgram.launchProgram;
import static Tools.LoadFile.inputFromFile;

public class ScratchCards {
    private static final List<String> input = new ArrayList<>(inputFromFile());

    public static void start() {
        launchProgram("1", "2", GondolaLift.class,
                "startDayOne", "startDayTwo");
    }

    public static void startDayOne() {
        ScratchCards scratchCards = new ScratchCards();
    }

    public static void startDayTwo() {
        ScratchCards scratchCards = new ScratchCards();
    }

}
