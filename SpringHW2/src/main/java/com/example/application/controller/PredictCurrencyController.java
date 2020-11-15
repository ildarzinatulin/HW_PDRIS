package com.example.application.controller;

import com.example.application.service.PredictCurrencyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictCurrencyController {
    private final PredictCurrencyService predictCurrencyService;

    @Autowired
    public PredictCurrencyController(PredictCurrencyService predictCurrencyService) {
        this.predictCurrencyService = predictCurrencyService;
    }

    @GetMapping("/predict-currency")
    public double getWeatherForLastDays() throws JsonProcessingException {
        return predictCurrencyService.predict();
    }
}
