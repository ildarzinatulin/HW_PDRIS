package com.example.application.service;

import java.util.List;
import java.util.stream.IntStream;

import com.example.application.domain.Currency;
import com.example.application.domain.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.math3.stat.regression.SimpleRegression;

@Service
public class PredictCurrencyService {
    private final SimpleRegression regression;
    private final WeatherService weatherService;
    private final CurrencyService currencyService;

    private static final int NUMBER_PREVIOUS_DAYS_FOR_FIT = 7;

    @Autowired
    public PredictCurrencyService(WeatherService weatherService,
            CurrencyService currencyService) {
        this.weatherService = weatherService;
        this.currencyService = currencyService;
        regression = new SimpleRegression();
    }

    public void fit() {
        List<Weather> weathers = weatherService.getWeatherForLastDays(NUMBER_PREVIOUS_DAYS_FOR_FIT);
        List<Currency> currencies = currencyService.getDollarCurrencyForLastDays(NUMBER_PREVIOUS_DAYS_FOR_FIT);
        IntStream.range(0, Math.min(currencies.size(), weathers.size()))
                .forEach(i -> regression.addData(weathers.get(i).getAvgTemperature(), currencies.get(i).getValue()));
    }

    public double predict() throws JsonProcessingException {
        Weather weather = weatherService.getWeatherPrediction();
        return regression.predict(weather.getMaxTemperature());
    }
}
