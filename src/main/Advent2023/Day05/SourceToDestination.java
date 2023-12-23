package main.Advent2023.Day05;

import java.util.*;

import static main.Advent2023.Day05.FoodProduction.seeds;

public class SourceToDestination {
    private final Map<Long, List<Long>> sourceToDestination = new HashMap<>();


    public SourceToDestination() {

    }

    public void addNewSource(long source, long destination, long range) {
        sourceToDestination.put(source, new ArrayList<>(List.of(destination, range)));
    }

    public long calculateDestination(long source) {
        Map<Long, Long> possibleSolutions = new HashMap<>();
        List<Long> sourceToDestKeySet = sourceToDestination.keySet().stream().toList();
        for (Long sourceId : sourceToDestKeySet) {
            long range = sourceToDestination.get(sourceId).get(1);
            long minVal = sourceToDestination.get(sourceId).get(0);
            long maxVal = minVal + range;
            if (source >= minVal && source < maxVal) {
                getPossibleSourceLocation(source, sourceId, possibleSolutions);
            }
        }
        if (possibleSolutions.containsKey(source)) {
            return possibleSolutions.get(source);
        }
        return source;
    }

    private void getPossibleSourceLocation(long source, long possibleSource, Map<Long, Long> possibleSolutions) {
        long rangeOfValuesToCheck = sourceToDestination.get(possibleSource).get(1);
        long destinationToFind = sourceToDestination.get(possibleSource).get(0);
        long increment = 0;
        for (long i = 0; i < rangeOfValuesToCheck; i++) {
            if (destinationToFind + increment == source) {
                possibleSolutions.put(destinationToFind + increment, possibleSource + increment);
            }
            increment++;
        }
    }

    private long getSeedRange(long source) {
        return seeds.get(source).getEndingRange();
    }

    public Map<Long, List<Long>> getSourceToDestination() {
        return sourceToDestination;
    }
}
