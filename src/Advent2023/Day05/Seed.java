package Advent2023.Day05;

public class Seed {

    private long seedId;
    private long soilId;
    private long fertilizerId;
    private long waterId;
    private long lightId;
    private long temperatureId;
    private long humidityId;
    private long locationId;

    public Seed(long seedId) {
        this.seedId = seedId;
    }
    public Seed(long seedId, long soilId, long fertilizerId, long waterId, long lightId,
                long temperatureId, long humidityId, long locationId) {
        this.seedId = seedId;
        this.soilId = soilId;
        this.fertilizerId = fertilizerId;
        this.waterId = waterId;
        this.lightId = lightId;
        this.temperatureId = temperatureId;
        this.humidityId = humidityId;
        this.locationId = locationId;
    }

    public long getSeedId() {
        return seedId;
    }

    public long getSoilId() {
        return soilId;
    }

    public void setSoilId(long soilId) {
        this.soilId = soilId;
    }

    public long getFertilizerId() {
        return fertilizerId;
    }

    public void setFertilizerId(long fertilizerId) {
        this.fertilizerId = fertilizerId;
    }

    public long getWaterId() {
        return waterId;
    }

    public void setWaterId(long waterId) {
        this.waterId = waterId;
    }

    public long getLightId() {
        return lightId;
    }

    public void setLightId(long lightId) {
        this.lightId = lightId;
    }

    public long getTemperatureId() {
        return temperatureId;
    }

    public void setTemperatureId(long temperatureId) {
        this.temperatureId = temperatureId;
    }

    public long getHumidityId() {
        return humidityId;
    }

    public void setHumidityId(long humidityId) {
        this.humidityId = humidityId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }
}
