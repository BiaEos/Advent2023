package Advent2023.Day04;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static Tools.LaunchProgram.launchProgram;
import static Tools.LoadFile.inputFromFile;

public class ScratchCards {
    private static final List<String> input = new ArrayList<>(inputFromFile());
    private static final List<String> cards = new ArrayList<>();

    public static void start() {
        launchProgram("1", "2", ScratchCards.class,
                "startDayOne", "startDayTwo");
    }

    public static void startDayOne() {
        ScratchCards scratchCards = new ScratchCards();
        scratchCards.removePrefixes();
        scratchCards.checkWinning();
    }
    // 11198 is too low
    // 49643 too high

    public static void startDayTwo() {
        ScratchCards scratchCards = new ScratchCards();
    }

    private void removePrefixes() {
        final Pattern pattern = Pattern.compile("Card\\s[0-9]+:\\s", Pattern.CASE_INSENSITIVE);
        for (String card : input) {
            final Matcher matcher = pattern.matcher(card);
            String fixedCard = matcher.replaceAll("");
            cards.add(fixedCard);
        }
        System.out.println(cards);
    }

    private void checkWinning() {
        int runningTotal = 0;
        final Pattern playedNumbers = Pattern.compile("([0-9]+(\\s+[0-9]+)+)", Pattern.CASE_INSENSITIVE);
        final Pattern winningNumbers = Pattern.compile("\\|\\s+([0-9]+(\\s+[0-9]+)+)", Pattern.CASE_INSENSITIVE);
        for (String card : cards) {
            final Matcher playedMatcher = playedNumbers.matcher(card);
            final Matcher winningMatcher = winningNumbers.matcher(card);
            String playedMatches = "";
            String winningMatches = "";
            if (playedMatcher.find()) {
                playedMatches = playedMatcher.group();
            }
            if (winningMatcher.find()) {
                winningMatches = winningMatcher.group().substring(2);
            }
            List<String> eachPlayedNumber = new ArrayList<>(List.of(playedMatches.split(" "))).stream()
                    .filter(number -> !number.equals("")).collect(Collectors.toList());
            List<String> eachWinningNumber = new ArrayList<>(List.of(winningMatches.split(" ")));
            eachPlayedNumber = eachPlayedNumber.stream().filter(number -> !number.equals("")).collect(Collectors.toList());
            eachWinningNumber = eachWinningNumber.stream().filter(number -> !number.equals("")).collect(Collectors.toList());

            int currentCount = 0;
            int currentMatches = 0;
            for (String number : eachPlayedNumber) {
                if (eachWinningNumber.contains(number) && currentCount == 0) {
                    currentCount = 1;
                    currentMatches++;
                    System.out.println("The matching numbers were : " + number + " and " + eachWinningNumber.get(eachWinningNumber.indexOf(number)));
                } else if (eachWinningNumber.contains(number) && currentCount > 0) {
                    currentCount *= 2;
                    currentMatches++;
                }
            }
            runningTotal += currentCount;
        }
        System.out.println("The running total is : " + runningTotal);
    }
}
