package main.Advent2023.Day02;

import main.Tools.LaunchProgram;
import main.Tools.LoadFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CubeGame {
    private static final List<String> GAME_RECORDS = new ArrayList<>(LoadFile.inputFromFile());
    Map<Integer, List<String>> gameResults = new HashMap<>();
    List<String> cubeLooks = new ArrayList<>();

    public static void start() {
        LaunchProgram.launchProgram("1", "2", CubeGame.class,
                "startDayOne", "startDayTwo");
    }

    public static void startDayOne() {
        CubeGame cubeGame = new CubeGame();
        cubeGame.parseInput();
        cubeGame.findIncompatibleGames();
        cubeGame.sumEligibleGames();
    }

    public static void startDayTwo() {
        CubeGame cubeGame = new CubeGame();
        cubeGame.parseInput();
        cubeGame.simplifyParsedInput();
        cubeGame.calculateBlockCombinationPower();
    }

    private void parseInput() {
        for (String record : GAME_RECORDS) {
            String[] splitGameResult = record.split(":");
            int gameNumber = Integer.parseInt(splitGameResult[0].replace("Game ", ""));
            cubeLooks = Arrays.stream(splitGameResult)
                    .filter(str -> !str.contains("Game"))
                    .map(str -> str.replace(",", ""))
                    .map(str -> str.split("(?<=;)"))
                    .flatMap(Arrays::stream)
                    .map(str -> str.replace(";", ""))
                    .toList();
            gameResults.put(gameNumber, new ArrayList<>(cubeLooks));
        }
    }

    private void findIncompatibleGames() {
        for (Map.Entry<Integer, List<String>> entry : gameResults.entrySet()) {
            int index = 0;
            for (String cubeLook : entry.getValue()) {
                String result = Arrays.stream(cubeLook.replace(" ", "")
                        .split("(?<=\\D)"))
                        .filter(str -> str.length() > 2)
                        .filter(this::isIneligibleCubeBag)
                        .collect(Collectors.joining(","));
                entry.getValue().set(index, result);
                index++;
            }
        }
    }

    private boolean isIneligibleCubeBag(String numAndColorValue) {
        if (numAndColorValue.contains("r")) {
            int valToCheck = Integer.parseInt(numAndColorValue.replaceAll("\\D", ""));
            return valToCheck > 12; // max for red
        }
        if (numAndColorValue.contains("g")) {
            int valToCheck = Integer.parseInt(numAndColorValue.replaceAll("\\D", ""));
            return valToCheck > 13; // max for green
        }
        if (numAndColorValue.contains("b")) {
            int valToCheck = Integer.parseInt(numAndColorValue.replaceAll("\\D", ""));
            return valToCheck > 14; // max for blue
        }
        return true;
    }

    private void sumEligibleGames() {
        int sum = 0;
        for (Map.Entry<Integer, List<String>> entry : gameResults.entrySet()) {
            List<String> distinctStrings = entry.getValue().stream().distinct().toList();
            if (distinctStrings.size() <= 1) {
                sum += entry.getKey();
            }
        }
        System.out.println("The answer to part one is : " + sum);
    }

    private void simplifyParsedInput() {
        for (Map.Entry<Integer, List<String>> entry : gameResults.entrySet()) {
            int index = 0;
            for (String cubeLook : entry.getValue()) {
                String result = Arrays.stream(cubeLook.replace(" ", "")
                        .split("(?<=\\D)"))
                        .filter(str -> str.length() > 1)
                        .collect(Collectors.joining(","));
                entry.getValue().set(index, result);
                index++;
            }
        }
    }

    private void calculateBlockCombinationPower() {
        int product;
        int total = 0;
        for (Map.Entry<Integer, List<String>> entry : gameResults.entrySet()) {
            int highestR = 1;
            int highestG = 1;
            int highestB = 1;
            for (String peek : entry.getValue()) {
                String[] values = peek.split(",");
                for (String value : values) {
                    if (value.contains("r")) {
                        highestR = Math.max(highestR, Integer.parseInt(value.replaceAll("\\D", "")));
                    }
                    if (value.contains("g")) {
                        highestG = Math.max(highestG, Integer.parseInt(value.replaceAll("\\D", "")));
                    }
                    if (value.contains("b")) {
                        highestB = Math.max(highestB, Integer.parseInt(value.replaceAll("\\D", "")));
                    }
                }
            }
            product = highestR * highestG * highestB;
            total += product;
        }
        System.out.println("The total is : " + total);
    }
}