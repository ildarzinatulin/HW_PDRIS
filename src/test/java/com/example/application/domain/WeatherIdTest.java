package com.example.application.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherIdTest {

    @Test
    public void testEquals() {
        WeatherId weatherId1 = new WeatherId("25:11:1998", "Moscow");
        WeatherId weatherId2 = new WeatherId("25:11:1998", "Moscow");
        WeatherId weatherId3 = new WeatherId("25:11:1998", "London");
        WeatherId weatherId4 = new WeatherId("25:11:2000", "Moscow");
        assertEquals(weatherId1, weatherId2);
        assertNotEquals(weatherId1, weatherId3);
        assertNotEquals(weatherId1, weatherId4);
    }

    @Test
    public void testHashCode() {
        WeatherId weatherId1 = new WeatherId("25:11:1998", "Moscow");
        WeatherId weatherId2 = new WeatherId("25:11:1998", "Moscow");
        assertEquals(weatherId1.hashCode(), weatherId2.hashCode());
    }
}