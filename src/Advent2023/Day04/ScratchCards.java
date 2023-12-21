package Advent2023.Day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static Tools.LaunchProgram.launchProgram;
import static Tools.LoadFile.inputFromFile;

public class ScratchCards {
    private static final List<String> originalCards = new ArrayList<>();
    private static final Map<String, List<String>> cardToCopies = new HashMap<>();


    /**
     * Launches the options in the console for which part the user wants to get the
     * answer.
     */
    public static void start() {
        launchProgram("1", "2", ScratchCards.class,
                "startDayOne", "startDayTwo");
    }

    /**
     * Entry point to the program for part 1, calls all methods to get the solution.
     */
    public static void startDayOne() {
        ScratchCards scratchCards = new ScratchCards();
        scratchCards.removePrefix();
        int runningTotal = scratchCards.getPointsAndCountCopies();
        System.out.println("The running total is : " + runningTotal);
    }

    /**
     * Entry point to the program for part 2, calls all methods to get the solution.
     */
    public static void startDayTwo() {
        ScratchCards scratchCards = new ScratchCards();
        scratchCards.removePrefix();
        scratchCards.getPointsAndCountCopies();
        int totalCards = scratchCards.getTotalCards();
        System.out.println("The card count is : " + totalCards);
    }

    /**
     * Removes the "Card #:" prefix from the input and adds the formatted data to
     * the originalCards list.
     */
    private void removePrefix() {
        final List<String> input = new ArrayList<>(inputFromFile());
        final Pattern pattern = Pattern.compile(
                "Card\\s[0-9]+:\\s", Pattern.CASE_INSENSITIVE);
        for (String card : input) {
            final Matcher matcher = pattern.matcher(card);
            String fixedCard = matcher.replaceAll("");
            originalCards.add(fixedCard);
        }
    }

    /**
     * Iterates through the originalCards list, keeping track of the
     * running total of points according to part one's rules. Adds value of
     * card as key in cardToCopies map with a list of the cards that will be
     * copied as the value, according to part two rules.
     * @return the running total of points for part one answer
     */
    private int getPointsAndCountCopies() {
        int runningTotalPoints = 0;
        for (String card : originalCards) {
            Set<String> matches = getMatches(card);
            int matchedNumbers = matches.size();
            int currentCountPoints = countPoints(matchedNumbers);
            updateCardToCopiesMap(card, matchedNumbers);
            runningTotalPoints += currentCountPoints;
        }
        return runningTotalPoints;
    }

    /**
     * Updates the map that contains the card as key and a  list of direct
     * copies as the value that are generated from that specific card.
     * @param card the card to update in the map
     * @param matchedNumbers the number of matches occurring on the card
     */
    private void updateCardToCopiesMap(String card, int matchedNumbers) {
        cardToCopies.put(card, new ArrayList<>());
        for (int i = 1; i <= matchedNumbers; i++) {
            List<String> currentCardList = cardToCopies.get(card);
            int currentIndex = originalCards.indexOf(card);
            currentCardList.add(originalCards.get(currentIndex + i));
        }
    }

    /**
     * Checks the chosen numbers for a card against the winning numbers
     * and removes the numbers that are not in common between the two.
     * @param card the card to evaluate for matches
     * @return a set of matching numbers between chosen and winning
     */
    private Set<String> getMatches(String card) {
        Set<String> chosenNumbers = getChosenNumbers(card);
        Set<String> winningNumbers = getWinningNumbers(card);
        chosenNumbers.retainAll(winningNumbers);
        return chosenNumbers;
    }

    /**
     * Calculates the points for the current card according to the rules
     * for part one. Each first match is worth 1 point and each match after
     * doubles the current number of points.
     * @param matches the number of matches on the current card to evaluate
     * @return the total points for the given card being evaluated
     */
    private int countPoints(int matches) {
        int currentCountOfPoints = 0;
        while (matches-- > 0) {
            if (currentCountOfPoints == 0) {
                currentCountOfPoints++;
                continue;
            }
            currentCountOfPoints *= 2;
        }
        return currentCountOfPoints;
    }

    /**
     * Separates the chosen numbers for the card from the winning numbers for the card.
     * Separates those numbers from a String into a List of String numbers.
     * @param card the card to check the numbers for
     * @return a Set of String numbers that were the chosen numbers on the card
     */
    private Set<String> getChosenNumbers(String card) {
        final Pattern chosenNumberPattern = Pattern.compile("([0-9]+(\\s+[0-9]+)+)", Pattern.CASE_INSENSITIVE);
        final Matcher chosenNumberMatcher = chosenNumberPattern.matcher(card);
        Set<String> chosenNumbers = new HashSet<>();
        
        if (chosenNumberMatcher.find()) {
            chosenNumbers = Arrays.stream(chosenNumberMatcher.group().split(" "))
                    .filter(number -> !number.equals(""))
                    .collect(Collectors.toSet());
        }
        return chosenNumbers;
    }

    /**
     * Separates the winning numbers for the card from the chosen numbers for the card.
     * Separates those numbers from a String into a List of String numbers.
     * @param card the card to check the numbers for
     * @return a Set of String numbers that were the winning numbers on the card
     */
    private Set<String> getWinningNumbers(String card) {
        final Pattern winningNumberPattern = Pattern.compile("\\|\\s+([0-9]+(\\s+[0-9]+)+)", Pattern.CASE_INSENSITIVE);
        final Matcher winningNumberMatcher = winningNumberPattern.matcher(card);
        Set<String> winningNumbers = new HashSet<>();
        
        if (winningNumberMatcher.find()) {
            winningNumbers = Arrays.stream(winningNumberMatcher.group().substring(2).split(" "))
                    .filter(number -> !number.equals(""))
                    .collect(Collectors.toSet());
        }
        return winningNumbers;
    }

    /**
     * Calculates the total number of cards and copies of cards that will
     * be generated according to part two rules. Each card will create a copy of
     * the next n number of cards, with n being the number of matching chosen and
     * winning numbers on the card.
     * @return the total count of cards the player will end up with
     */
    private int getTotalCards() {
        Queue<String> toVisit = new LinkedList<>();
        int totalCardCount = 0;
        for (String card : originalCards) {
            totalCardCount++;
            toVisit.addAll(cardToCopies.get(card));
            while (!toVisit.isEmpty()) {
                String cardCopy = toVisit.poll();
                toVisit.addAll(cardToCopies.get(cardCopy));
                totalCardCount++;
            }
        }
        return totalCardCount;
    }
}
