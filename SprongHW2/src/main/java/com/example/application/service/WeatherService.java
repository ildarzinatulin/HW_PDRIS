package com.example.application.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import com.example.application.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${api.weather.key}")
    private String apiKey;
    private String urlForPrediction;
    private final DateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
    private final ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        urlForPrediction = "http://api.weatherapi.com/v1/forecast.json?key=" + apiKey + "&q=Moscow&days=2";
    }

    public Weather getWeatherPrediction() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(urlForPrediction, String.class);
        return getTomorrowWeatherByResponse(response);
    }

    private Weather getTomorrowWeatherByResponse(ResponseEntity<String> response) throws JsonProcessingException {
        JsonNode root = mapper.readTree(response.getBody());
        root = root.findValue("forecastday").get(1);
        return new Weather(root.findValue("maxtemp_c").asDouble(),
                root.findValue("mintemp_c").asDouble(),
                root.findValue("avgtemp_c").asDouble(),
                root.findValue("maxwind_kph").asDouble(),
                root.findValue("totalprecip_mm").asDouble(),
                root.findValue("avgvis_km").asDouble(),
                root.findValue("avghumidity").asDouble());
    }

    public List<Weather> getWeatherForLastDays(int numberOfDays) {
        return getWeatherForLastDays(numberOfDays, "Moscow");
    }

    public List<Weather> getWeatherForLastDays(int numberOfDays, String city) {
        return IntStream.range(0, numberOfDays)
                .mapToObj(this::getInstantForDaysBefore)
                .map(i -> getWeather(i, city))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Weather getWeather(Instant date, String city) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(createUrlForInstantAndCity(date, city),
                String.class);
        try {
            return getWeatherByResponse(response);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private Instant getInstantForDaysBefore(int days) {
        return Instant.now().minus(days, ChronoUnit.DAYS);
    }

    private String createUrlForInstantAndCity(Instant date, String city) {
        return "http://api.weatherapi.com/v1/history.json?key=" + apiKey + "&q=" + city + "&dt=" +
                dateFormatDay.format(Date.from(date));
    }

    private Weather getWeatherByResponse(ResponseEntity<String> response) throws JsonProcessingException {
        JsonNode root = mapper.readTree(response.getBody());
        return new Weather(root.findValue("maxtemp_c").asDouble(),
                root.findValue("mintemp_c").asDouble(),
                root.findValue("avgtemp_c").asDouble(),
                root.findValue("maxwind_kph").asDouble(),
                root.findValue("totalprecip_mm").asDouble(),
                root.findValue("avgvis_km").asDouble(),
                root.findValue("avghumidity").asDouble());
    }
}
