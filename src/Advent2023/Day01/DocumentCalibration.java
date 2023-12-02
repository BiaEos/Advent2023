package Advent2023.Day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static Tools.LaunchProgram.launchProgram;
import static Tools.LoadFile.inputFromFile;

public class DocumentCalibration {
    private static final List<String> calibrationDocument = new ArrayList<>(inputFromFile());
    private static final List<String> results = new ArrayList<>();
    List<Integer> resultingNumbers = new ArrayList<>();
    private static Integer sumOfCalibration = null;



    public static void start() {
        launchProgram("1", "2", DocumentCalibration.class,
                "startDayOne", "startDayTwo");
    }

    public static void startDayOne() {
        DocumentCalibration documentCalibration = new DocumentCalibration();
        documentCalibration.removeLetters();
        documentCalibration.removeMiddleNumbers();
        documentCalibration.doubleSingleDigits();
        documentCalibration.sumAllDigits();
        System.out.println("The answer to day one is : " + sumOfCalibration);
    }

    public static void startDayTwo() {
        DocumentCalibration documentCalibration = new DocumentCalibration();
        documentCalibration.allNumbersInOrder();
        documentCalibration.sumAllDigitsDayTwo();
    }

    private void removeLetters() {
        for (int line = 0; line < calibrationDocument.size(); line++) {
            StringBuilder builder = new StringBuilder();
            for (int ch = 0; ch < calibrationDocument.get(line).length(); ch++) {
                char current = calibrationDocument.get(line).charAt(ch);
                if (Character.isDigit(current)) {
                    builder.append(current);
                }
            }
            results.add(String.valueOf(builder));
        }
    }

    private void removeMiddleNumbers() {
        for (int line = 0; line < results.size(); line++) {
            StringBuilder builder = new StringBuilder();
            if (results.get(line).length() < 3) {
                continue;
            }
            builder.append(results.get(line).charAt(0));
            builder.append(results.get(line).charAt(results.get(line).length() - 1));
            results.set(line, String.valueOf(builder));
            System.out.println(results);
        }
    }

    private void doubleSingleDigits() {
        for (int line = 0; line < results.size(); line++) {
            StringBuilder builder = new StringBuilder();
            if (results.get(line).length() == 1) {
                builder.append(results.get(line).charAt(0));
                builder.append(results.get(line).charAt(0));
                results.set(line, String.valueOf(builder));
            }
        }
    }

    private void sumAllDigits() {
        sumOfCalibration = results.stream()
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void sumAllDigitsDayTwo() {
        sumOfCalibration = resultingNumbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void allNumbersInOrder() {
        String[] numbers = {"one", "1", "two", "2", "three","3", "four", "4", "five", "5",
                "six", "6", "seven", "7", "eight", "8", "nine", "9"};

        for (String line : calibrationDocument) {
            String[] numbersOrdered = new String[100];
            for (String number : numbers) {
                int startingIndex = 0;
                int endingIndex = 0;
                String newNumber = "";
                int i = line.indexOf(number);
                while (i >= 0) {
                    startingIndex = line.indexOf(number, i);
                    endingIndex = startingIndex + number.length();
                    newNumber = line.substring(startingIndex, endingIndex);
                    numbersOrdered[startingIndex] = (checkAndReplaceSubstring(newNumber));
                    i = line.indexOf(number, i + 1);
                }
            }
            List<String> index = Arrays.stream(numbersOrdered).filter(Objects::nonNull).toList();
            resultingNumbers.add(Integer.valueOf(index.get(0) + index.get(index.size() - 1)));
        }
    }

    private String checkAndReplaceSubstring(String substring) {
        if (substring.startsWith("one") && substring.contains("one")) {
            return substring.replace("one", "1");
        }
        if (substring.startsWith("two") && substring.contains("two")) {
            return substring.replace("two", "2");
        }
        if (substring.startsWith("three") && substring.contains("three")) {
            return substring.replace("three", "3");
        }
        if (substring.startsWith("four") && substring.contains("four")) {
            return substring.replace("four", "4");
        }
        if (substring.startsWith("five") && substring.contains("five")) {
            return substring.replace("five", "5");
        }
        if (substring.startsWith("six") && substring.contains("six")) {
            return substring.replace("six", "6");
        }
        if (substring.startsWith("seven") && substring.contains("seven")) {
            return substring.replace("seven", "7");
        }
        if (substring.startsWith("eight") && substring.contains("eight")) {
            return substring.replace("eight", "8");
        }
        if (substring.startsWith("nine") && substring.contains("nine")) {
            return substring.replace("nine", "9");
        }
        return substring;
    }
}