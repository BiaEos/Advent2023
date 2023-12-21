package Advent2023.Day05;

public enum MapType {

    SEED_TO_SOIL("seed-to-soil map:"),
    SOIL_TO_FERTILIZER("soil-to-fertilizer map:"),
    FERTILIZER_TO_WATER("fertilizer-to-water map:"),
    WATER_TO_LIGHT("water-to-light map:"),
    LIGHT_TO_TEMPERATURE("light-to-temperature map:"),
    TEMPERATURE_TO_HUMIDITY("temperature-to-humidity map:"),
    HUMIDITY_TO_LOCATION("humidity-to-location map:");

    private final String str;

    MapType(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }

    public boolean equals(String mapType) {
        return this.toString().equals(mapType);
    }

}
