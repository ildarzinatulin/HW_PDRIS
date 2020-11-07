package com.example.application;

public class Weather {
    private double maxTemperature;
    private double minTemperature;
    private double avgTemperature;
    private double maxWind;
    private double totalPrecipitation;
    private double avgVisibility;
    private double avgHumidity;

    public Weather(double maxTemperature, double minTemperature, double avgTemperature, double maxWind,
            double totalPrecipitation, double avgVisibility, double avgHumidity) {
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.avgTemperature = avgTemperature;
        this.maxWind = maxWind;
        this.totalPrecipitation = totalPrecipitation;
        this.avgVisibility = avgVisibility;
        this.avgHumidity = avgHumidity;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getAvgTemperature() {
        return avgTemperature;
    }

    public double getMaxWind() {
        return maxWind;
    }

    public double getTotalPrecipitation() {
        return totalPrecipitation;
    }

    public double getAvgVisibility() {
        return avgVisibility;
    }

    public double getAvgHumidity() {
        return avgHumidity;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                ", avgTemperature=" + avgTemperature +
                ", maxWind=" + maxWind +
                ", totalPrecipitation=" + totalPrecipitation +
                ", avgVisibility=" + avgVisibility +
                ", avgHumidity=" + avgHumidity +
                '}';
    }
}
