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

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherControllerTest {

    @Autowired
    private WeatherController weatherController;

    @Test
    public void getWeatherForLastDays() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/weather");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.weatherController).build().perform(requestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        resultActions.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));

        requestBuilder = MockMvcRequestBuilders.get("/weather?days=0");
        resultActions = MockMvcBuilders.standaloneSetup(this.weatherController).build().perform(requestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        resultActions.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));

        requestBuilder = MockMvcRequestBuilders.get("/weather?days=-9");
        resultActions = MockMvcBuilders.standaloneSetup(this.weatherController).build().perform(requestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        resultActions.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

}