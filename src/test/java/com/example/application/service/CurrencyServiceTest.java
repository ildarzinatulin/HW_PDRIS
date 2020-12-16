package com.example.application.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyServiceTest {

    @Autowired
    CurrencyService currencyService;

    @Test
    public void getDollarCurrencyForLastDays() {
        assertEquals(currencyService.getDollarCurrencyForLastDays(7).size(), 7);
        assertEquals(currencyService.getDollarCurrencyForLastDays(2).size(), 2);
        assertTrue(currencyService.getDollarCurrencyForLastDays(7).stream()
                .allMatch(c -> c.getValue() < 100 && c.getValue() > 50));
    }
}