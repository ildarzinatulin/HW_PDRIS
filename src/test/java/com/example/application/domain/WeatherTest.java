package com.example.application.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherTest {

    private final String date = "25:11:1998 03:21";
    private final String city = "Moscow";
    private final double maxTemperature = 25.1;
    private final double minTemperature = 20.2;
    private final double avgTemperature = 22.7;
    private final double maxWind = 7.8;
    private final double avgVisibility = 213.12;
    private final Weather weather = new Weather(date, city, maxTemperature, minTemperature, avgTemperature,
            maxWind, avgVisibility);

    @Test
    public void getDate() {
        assertEquals(weather.getDate(), date);
    }

    @Test
    public void getCity() {
        assertEquals(weather.getCity(), city);
    }

    @Test
    public void getMaxTemperature() {
        assertEquals(weather.getMaxTemperature(), maxTemperature, 0.01);
    }

    @Test
    public void getMinTemperature() {
        assertEquals(weather.getMinTemperature(), minTemperature, 0.01);
    }

    @Test
    public void getAvgTemperature() {
        assertEquals(weather.getAvgTemperature(), avgTemperature, 0.01);
    }

    @Test
    public void getMaxWind() {
        assertEquals(weather.getMaxWind(), maxWind, 0.01);
    }

    @Test
    public void getAvgVisibility() {
        assertEquals(weather.getAvgVisibility(), avgVisibility, 0.01);
    }
}