package com.example.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PredictCurrencyServiceTest {

    @Autowired
    private PredictCurrencyService predictCurrencyService;

    @Test
    public void testFit() throws JsonProcessingException {
        predictCurrencyService.fit();
        assertTrue(predictCurrencyService.predict() < 100);
    }

    @Test
    public void testPredict() throws JsonProcessingException {
        assertTrue(predictCurrencyService.predict() > 50 && predictCurrencyService.predict() < 100);
    }

}