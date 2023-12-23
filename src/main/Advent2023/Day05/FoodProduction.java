package main.Advent2023.Day05;

import main.Tools.LaunchProgram;
import main.Tools.LoadFile;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FoodProduction {

    private static final List<String> input = new ArrayList<>(LoadFile.inputFromFile());
    static final Map<Long, Seed> seeds = new HashMap<>();
    private final SourceToDestination seedToSoil = new SourceToDestination();
    private final SourceToDestination soilToFertilizer = new SourceToDestination();
    private final SourceToDestination fertilizerToWater = new SourceToDestination();
    private final SourceToDestination waterToLight = new SourceToDestination();
    private final SourceToDestination lightToTemperature = new SourceToDestination();
    private final SourceToDestination temperatureToHumidity = new SourceToDestination();
    private final SourceToDestination humidityToLocation = new SourceToDestination();
    private static final List<Long> locations = new ArrayList<>();

    private final Pattern seedIdsPattern = Pattern
            .compile("[A-Za-z0-9]+:(\\s+([0-9]+\\s+)+)[0-9]+");
    private final Pattern typeToMapPattern = Pattern
            .compile("([a-z]+(-[a-z]+)+)\\s+[a-z]+:");
    private static boolean isPartOne = false;
    private static boolean isPartTwo = false;

    /**
     * Launches the options in the console for which part the user wants to get the
     * answer.
     */
    public static void start() {
        LaunchProgram.launchProgram("1", "2", FoodProduction.class,
                "startDayOne", "startDayTwo");
    }

    /**
     * Entry point to the program for part 1, calls all methods to get the solution.
     */
    public static void startDayOne() {
        isPartOne = true;
        FoodProduction foodProduction = new FoodProduction();
        input.removeIf(str -> str.equals(""));
        foodProduction.generateSeedsAndMapsFromInput();
        foodProduction.getSpecificSeedInfo();
        Collections.sort(locations);
        System.out.println("The answer for part one is : " + locations.get(0));
    }

    /**
     * Entry point to the program for part 2, calls all methods to get the solution.
     */
    public static void startDayTwo() {
        isPartTwo = true;
        FoodProduction foodProduction = new FoodProduction();
        input.removeIf(str -> str.equals(""));
        foodProduction.generateSeedsAndMapsFromInput();



    }

    /**
     * Checks the line in the input to determine if it is the beginning
     * of seed id numbers or if it is the beginning of the type of another
     * number that needs to be added to the map storing source and destination.
     * Stores the values according to what it is. Adds ending range field if
     * called from part two.
     */
    private void generateSeedsAndMapsFromInput() {
        for (int line = 0; line < input.size(); line++) {
            if (isSeedIdLine(line) && isPartOne) {
                generateSeeds(line);
            }
            if (isSeedIdLine(line) && isPartTwo) {
                generateSeedWithEndingRange(line);
            }
            if (isTypeLine(line)) {
                generateMapTo(line);
            }
        }
    }

    /**
     * Determines if the line passed in is the beginning of the seed number
     * input.
     * @param line the line to be evaluated
     * @return a boolean true if it is the start of the seed ids, false
     * otherwise
     */
    private boolean isSeedIdLine(int line) {
        Matcher seedIdMatcher = seedIdsPattern.matcher(input.get(line));
        return seedIdMatcher.find();
    }

    /**
     * Determines if the line passed in is the beginning of source and
     * destinations for another type being evaluated, such as soil or
     * humidity.
     * @param line the line to be evaluated
     * @return a boolean true if it is the beginning of a type other
     * than seed id, false otherwise
     */
    private boolean isTypeLine(int line) {
        Matcher typeToMapMatcher = typeToMapPattern.matcher(input.get(line));
        return typeToMapMatcher.find();
    }

    /**
     * Removes the type description for the numbers and removes any blank
     * spaces from the resulting list.
     * @param line the line to be parsed into a seed number list
     * @return a list of seed ids
     */
    private List<String> parseSeeds(int line) {
        return Arrays
                .stream(input.get(line)
                        .replaceAll("[A-Za-z]+:\\s", "")
                        .split(" "))
                .toList();
    }

    /**
     * Creates a new seed for each seed in the seed list and adds them
     * to the map of seeds storing seed id to seed.
     * @param line the line to be evaluated
     */
    private void generateSeeds(int line) {
        List<String> seedList = parseSeeds(line);
        for (String seedId : seedList) {
            Seed seed = new Seed(Long.parseLong(seedId));
            seeds.put(Long.valueOf(seedId), seed);
        }
    }

    /**
     * Generates a new seed with ending range field set for part two.
     * Adds original seed to the seed map.
     * @param line the line to be evaluated
     */
    private void generateSeedWithEndingRange(int line) {
        List<String> seedList = parseSeeds(line);
        for (int i = 0; i < seedList.size(); i += 2) {
            Seed seed = new Seed(Long.parseLong(seedList.get(i)),
                                 Long.parseLong(seedList.get(i + 1)));
            seeds.put(seed.getSeedId(), seed);
        }
    }

    /**
     * Determines what type of input needs to be stored according to the
     * type of values the line is within. Calls createMaps to store that
     * data in the sourceToDestination map with key source and value as
     * a list of (0) destination and (1) range.
     * @param line the line to be evaluated
     */
    private void generateMapTo(int line) {
        final Pattern mapTypePattern = Pattern
                .compile("([A-Za-z0-9]+(-[A-Za-z0-9]+)+)\\s[A-Za-z0-9]+:");
        final Matcher mapTypeMatcher = mapTypePattern
                .matcher(input.get(line));
        String prefix = "";
        if (mapTypeMatcher.find()) {
            prefix = mapTypeMatcher.group();
        }
        line++;
        if (MapType.SEED_TO_SOIL.equals(prefix)) {
            createMaps(line, seedToSoil);
        } else if (MapType.SOIL_TO_FERTILIZER.equals(prefix)) {
            createMaps(line, soilToFertilizer);
        } else if (MapType.FERTILIZER_TO_WATER.equals(prefix)) {
            createMaps(line, fertilizerToWater);
        } else if (MapType.WATER_TO_LIGHT.equals(prefix)) {
            createMaps(line, waterToLight);
        } else if (MapType.LIGHT_TO_TEMPERATURE.equals(prefix)) {
            createMaps(line, lightToTemperature);
        } else if (MapType.TEMPERATURE_TO_HUMIDITY.equals(prefix)) {
            createMaps(line, temperatureToHumidity);
        } else if (MapType.HUMIDITY_TO_LOCATION.equals(prefix)) {
            createMaps(line, humidityToLocation);
        }
    }

    /**
     * Stores data in the sourceToDestination map with key source and value as
     * a list of (0) destination and (1) range.
     * @param line the line to be evaluated
     * @param sourceToDestination the specific instance to store the data in,
     *                            depending on what type of information it is
     */
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

    /**
     * Determines if the line contains numbers that relate to one of the types,
     * such as a seed type or soil type etc.
     * @param line the line to be evaluated
     * @return a boolean true if the line is numeric, false otherwise
     */
    private boolean isValidNumericLine(int line) {
        return line < input.size() &&
                input.get(line).matches("[0-9]+\\s+[0-9]+\\s+[0-9]+");
    }

    /**
     * Parses the line into a list of String numbers.
     * @param line the line to be evaluated
     * @return a list of String numbers
     */
    private List<String> getNumbersForLine(int line) {
        return Arrays.stream(input.get(line).split(" ")).toList();
    }

    /**
     * Calls the methods to retrieve the seed locations for each seed in the
     * map of existing seeds.
     */
    private void getSpecificSeedInfo() {
        for (Seed seed : seeds.values()) {
            getSeedLocation(seed);
        }
    }

    /**
     * Calls the methods that will trace their way through the various types
     * of sources and destinations resulting in a location for the seed.
     * @param seed the seed to be evaluated
     */
    private void getSeedLocation(Seed seed) {
        getSoilForSeed(seed);
        getFertilizerForSoil(seed);
        getWaterForFertilizer(seed);
        getLightForWater(seed);
        getTemperatureForLight(seed);
        getHumidityForTemperature(seed);
        getLocationForHumidity(seed);
    }

    /**
     * The soil destination for the seed passed in.
     * @param seed the seed to be evaluated
     */
    private void getSoilForSeed(Seed seed) {
        seeds.get(seed.getSeedId()).setSoilId(
                seedToSoil.calculateDestination(seed.getSeedId()));
    }

    /**
     * The fertilizer destination for the seed passed in.
     * @param seed the seed to be evaluated
     */
    private void getFertilizerForSoil(Seed seed) {
        seeds.get(seed.getSeedId()).setFertilizerId(
                soilToFertilizer.calculateDestination(seed.getSoilId()));
    }

    /**
     * The water destination for the seed passed in.
     * @param seed the seed to be evaluated
     */
    private void getWaterForFertilizer(Seed seed) {
        seeds.get(seed.getSeedId()).setWaterId(
                fertilizerToWater.calculateDestination(seed.getFertilizerId()));
    }

    /**
     * The light destination for the seed passed in.
     * @param seed the seed to be evaluated
     */
    private void getLightForWater(Seed seed) {
        seeds.get(seed.getSeedId()).setLightId(
                waterToLight.calculateDestination(seed.getWaterId()));
    }

    /**
     * The temperature destination for the seed passed in.
     * @param seed the seed to be evaluated
     */
    private void getTemperatureForLight(Seed seed) {
        seeds.get(seed.getSeedId()).setTemperatureId(
                lightToTemperature.calculateDestination(seed.getLightId()));
    }

    /**
     * The humidity destination for the seed passed in.
     * @param seed the seed to be evaluated
     */
    private void getHumidityForTemperature(Seed seed) {
        seeds.get(seed.getSeedId()).setHumidityId(
                temperatureToHumidity.calculateDestination(seed.getTemperatureId()));
    }

    /**
     * The location destination for the seed passed in. Adds the location
     * to the location list.
     * @param seed the seed to be evaluated
     */
    private void getLocationForHumidity(Seed seed) {
        seeds.get(seed.getSeedId()).setLocationId(
                humidityToLocation.calculateDestination(seed.getHumidityId()));
        locations.add(seed.getLocationId());
    }
}















