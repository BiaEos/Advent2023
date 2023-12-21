package Advent2023.Day05;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Advent2023.Day05.MapType.*;
import static Tools.LaunchProgram.launchProgram;
import static Tools.LoadFile.inputFromFile;

public class FoodProduction {

    static final List<String> input = new ArrayList<>(inputFromFile());
    final Map<Long, Seed> seeds = new HashMap<>();
    final List<String> parsedInput = new ArrayList<>();
    SourceToDestination seedToSoil = new SourceToDestination();
    SourceToDestination soilToFertilizer = new SourceToDestination();
    SourceToDestination fertilizerToWater = new SourceToDestination();
    SourceToDestination waterToLight = new SourceToDestination();
    SourceToDestination lightToTemperature = new SourceToDestination();
    SourceToDestination temperatureToHumidity = new SourceToDestination();
    SourceToDestination humidityToLocation = new SourceToDestination();
    static List<Long> locations = new ArrayList<>();

    /**
     * Launches the options in the console for which part the user wants to get the
     * answer.
     */
    public static void start() {
        launchProgram("1", "2", FoodProduction.class,
                "startDayOne", "startDayTwo");
    }

    /**
     * Entry point to the program for part 1, calls all methods to get the solution.
     */
    public static void startDayOne() {
        FoodProduction foodProduction = new FoodProduction();
        /*foodProduction.createSeeds();*/
        input.removeIf(str -> str.equals(""));
        //System.out.println(input);
        foodProduction.parseInput();
        foodProduction.getSeedLocation();
        Collections.sort(locations);
        System.out.println("The answer for part one is : " + locations.get(0));
    }

    /**
     * Entry point to the program for part 2, calls all methods to get the solution.
     */
    public static void startDayTwo() {
        FoodProduction foodProduction = new FoodProduction();

    }

    private void parseInput() {
        /*final Pattern seedIdsPattern = Pattern.compile("[A-Za-z]+:\\s");*/
        final Pattern seedIdsPattern = Pattern
                .compile("[A-Za-z0-9]+:(\s+([0-9]+\s+)+)[0-9]+");
        final Pattern typeToMapPattern = Pattern
                .compile("([a-z]+(-[a-z]+)+)\\s+[a-z]+:");
        for (int line = 0; line < input.size(); line++) {
            final Matcher seedIdMatcher = seedIdsPattern.matcher(input.get(line));
            final Matcher typeToMapMatcher = typeToMapPattern.matcher(input.get(line));
            if (seedIdMatcher.find()) {
                generateSeeds(line);
            } else if (typeToMapMatcher.find()) {
                generateMapTo(line);
            } else {
                continue;
            }
        }
    }

    private void generateSeeds(int line) {
        List<String> seedList = Arrays
                .stream(input.get(line)
                        .replaceAll("[A-Za-z]+:\\s", "")
                        .split(" "))
                .toList();
        for (String seedId : seedList) {
            Seed seed = new Seed(Long.parseLong(seedId));
            seeds.put(Long.valueOf(seedId), seed);
        }
    }

    private void generateMapTo(int line) {
        final Pattern mapTypePattern = Pattern.compile("([A-Za-z0-9]+(-[A-Za-z0-9]+)+)\\s[A-Za-z0-9]+:");
        final Matcher mapTypeMatcher = mapTypePattern.matcher(input.get(line));
        String prefix = "";
        if (mapTypeMatcher.find()) {
            prefix = mapTypeMatcher.group();
        }
        line++;
        if (SEED_TO_SOIL.equals(prefix)) {
            createMaps(line, seedToSoil);
        } else if (SOIL_TO_FERTILIZER.equals(prefix)) {
            createMaps(line, soilToFertilizer);
        } else if (FERTILIZER_TO_WATER.equals(prefix)) {
            createMaps(line, fertilizerToWater);
        } else if (WATER_TO_LIGHT.equals(prefix)) {
            createMaps(line, waterToLight);
        } else if (LIGHT_TO_TEMPERATURE.equals(prefix)) {
            createMaps(line, lightToTemperature);
        } else if (TEMPERATURE_TO_HUMIDITY.equals(prefix)) {
            createMaps(line, temperatureToHumidity);
        } else if (HUMIDITY_TO_LOCATION.equals(prefix)) {
            createMaps(line, humidityToLocation);
        }
    }

    private void createMaps(int line, SourceToDestination sourceToDestination) {
        List<String> numbers;
        while (isValidNumericLine(line)) {
            numbers = getNumbersForLine(line);
            sourceToDestination.addNewSource(Long.parseLong(numbers.get(0)),
                                    Long.parseLong(numbers.get(1)),
                                    Long.parseLong(numbers.get(2)));
            line++;
        }
    }

    private boolean isValidNumericLine(int line) {
        return line < input.size() && input.get(line).matches("[0-9]+\\s+[0-9]+\\s+[0-9]+");
    }

    private List<String> getNumbersForLine(int line) {
        return Arrays.stream(input.get(line).split(" ")).toList();
    }

    private void getSeedLocation() {
        for (Map.Entry<Long, Seed> seed : seeds.entrySet()) {
            //System.out.println("Final seed : " + seed);
            long soil = seedToSoil.calculateDestination(seed.getKey());
            //System.out.println("Final soil : " + soil);
            long fertilizer = soilToFertilizer.calculateDestination(soil);
            //System.out.println("Final fertilizer : " + fertilizer);
            long water = fertilizerToWater.calculateDestination(fertilizer);
            //System.out.println("Final water : " + water);
            long light = waterToLight.calculateDestination(water);
            //System.out.println("Final light : " + light);
            long temperature = lightToTemperature.calculateDestination(light);
            //System.out.println("Final temperature : " + temperature);
            long humidity = temperatureToHumidity.calculateDestination(temperature);
            //System.out.println("Final humidity : " + humidity);
            long location = humidityToLocation.calculateDestination(humidity);
            //System.out.println("Final Location : " + location);
            locations.add(location);
        }
    }

}