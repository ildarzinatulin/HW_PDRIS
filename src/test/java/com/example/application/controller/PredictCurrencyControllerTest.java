package com.example.application.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PredictCurrencyControllerTest {

    @Autowired
    private PredictCurrencyController predictCurrencyController;

    @Test
    public void getWeatherForLastDays() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/predict-currency");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.predictCurrencyController).build().perform(requestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        resultActions.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$").isNumber());
    }
}