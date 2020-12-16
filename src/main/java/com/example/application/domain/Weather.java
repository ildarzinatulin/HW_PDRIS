package com.example.application.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(WeatherId.class)
public class Weather {
    @Id
    private String date;
    @Id
    private String city;
    private double maxTemperature;
    private double minTemperature;
    private double avgTemperature;
    private double maxWind;
    private double avgVisibility;

    public Weather(String date, String city, double maxTemperature, double minTemperature, double avgTemperature,
            double maxWind, double avgVisibility) {
        this.date = date;
        this.city = city;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.avgTemperature = avgTemperature;
        this.maxWind = maxWind;
        this.avgVisibility = avgVisibility;
    }

    public Weather() {}

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
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

    public double getAvgVisibility() {
        return avgVisibility;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                ", avgTemperature=" + avgTemperature +
                ", maxWind=" + maxWind +
                ", avgVisibility=" + avgVisibility +
                '}';
    }
}
