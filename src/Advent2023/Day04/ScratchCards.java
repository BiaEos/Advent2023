package Advent2023.Day04;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Tools.LaunchProgram.launchProgram;
import static Tools.LoadFile.inputFromFile;

public class ScratchCards {
    private static final List<String> input = new ArrayList<>(inputFromFile());
    private static final List<String> cards = new ArrayList<>();
    final Pattern playedNumbers = Pattern.compile("([0-9]+(\\s+[0-9]+)+)", Pattern.CASE_INSENSITIVE);
    final Pattern winningNumbers = Pattern.compile("\\|\\s+([0-9]+(\\s+[0-9]+)+)", Pattern.CASE_INSENSITIVE);

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
        scratchCards.removePrefixes();
        scratchCards.checkWinning();
    }
    // 134205 too low

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
        for (String card : cards) {
            List<String> playedMatches = getPossibleMatches(card);
            List<String> winningMatches = getWinningNumbers(card);
            int currentCount = 0;
            for (String number : playedMatches) {
                if (winningMatches.contains(number) && currentCount == 0) {
                    currentCount = 1;
                } else if (winningMatches.contains(number) && currentCount > 0) {
                    currentCount *= 2;
                }
            }
            runningTotal += currentCount;
        }
        System.out.println("The running total is : " + runningTotal);
    }

    private List<String> getPossibleMatches(String card) {
        List<String> possibleMatches = new ArrayList<>();
        final Matcher playedMatcher = playedNumbers.matcher(card);
        if (playedMatcher.find()) {
            possibleMatches = Arrays.stream(playedMatcher.group().split(" "))
                    .filter(number -> !number.equals(""))
                    .toList();
        }
        return possibleMatches;
    }

    private List<String> getWinningNumbers(String card) {
        List<String> winningMatches = new ArrayList<>();
        final Matcher winningMatcher = winningNumbers.matcher(card);
        if (winningMatcher.find()) {
            winningMatches = Arrays.stream(winningMatcher.group().substring(2).split(" "))
                    .filter(number -> !number.equals(""))
                    .toList();
        }
        return winningMatches;
    }
}
