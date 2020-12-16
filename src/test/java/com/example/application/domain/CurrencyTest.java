package com.example.application.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyTest {
    private final String date = "25:11:1998 03:21";
    private final double value = 72.6;
    private Currency currency;

    @Before
    public void setUp() throws Exception {
        currency = new Currency(date, value);
    }

    @Test
    public void getDate() {
        assertEquals(currency.getDate(), date);
    }

    @Test
    public void getValue() {
        assertEquals(currency.getValue(), value, 0.01);
    }
}