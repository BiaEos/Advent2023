package main.Advent2023.Day05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SourceToDestination {
    private final Map<Long, List<Long>> sourceToDestination = new HashMap<>();

    public SourceToDestination() {

    }

    public void addNewSource(long source, long destination, long range) {
        sourceToDestination.put(source, new ArrayList<>(List.of(destination, range)));
    }

    public long calculateDestination(long source) {
        Map<Long, Long> possibleSolutions = new HashMap<>();
        //System.out.println("Printing source to destination : " + sourceToDestination);
        // iterate through the created maps
        for (Map.Entry<Long, List<Long>> possibleSource : sourceToDestination.entrySet()) {
            // get the range of values needed to be checked
            long range = possibleSource.getValue().get(1);
            //System.out.println("Range is equal to : " + range);
            long minimum = possibleSource.getKey();
            //System.out.println("Minimum is equal to : " + minimum);
            long matchingValue = possibleSource.getValue().get(0);
            long increment = 0;
            for (long j = 0; j < range; j++) {
                possibleSolutions.put(matchingValue + increment, minimum + increment);
                //System.out.println("Possible solutions map : " + possibleSolutions);
                increment++;
            }
        }
        if (possibleSolutions.containsKey(source)) {
            return possibleSolutions.get(source);
        }
        return source;
    }

    public Map<Long, List<Long>> getSourceToDestination() {
        return sourceToDestination;
    }

}
