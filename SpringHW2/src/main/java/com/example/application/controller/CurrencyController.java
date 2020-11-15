package com.example.application.controller;

import java.util.List;

import com.example.application.domain.Currency;
import com.example.application.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency")
    public List<Currency> getWeatherForLastDays(@RequestParam(required = false) Integer days) {
        int numberOfDays = 1;
        if (days != null) {
            numberOfDays = days;
        }
        return currencyService.getDollarCurrencyForLastDays(numberOfDays);
    }
}
