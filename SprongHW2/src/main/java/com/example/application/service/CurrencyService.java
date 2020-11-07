package com.example.application.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {
    public List<Double> getDollarCurrencyForLastDays(int numberOfDays) {
        return IntStream.range(0, numberOfDays)
                .mapToObj(this::getInstantForDaysBefore)
                .map(this::getDollarCurrency)
                .collect(Collectors.toList());
    }

    private Instant getInstantForDaysBefore(int days) {
        return Instant.now().minus(days, ChronoUnit.DAYS);
    }

    private Double getDollarCurrency(Instant time) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(createUrlForInstant(time), String.class);
        return getCurrencyByResponse(response);
    }

    private Double getCurrencyByResponse(ResponseEntity<String> response) {
        String body = response.getBody();
        System.out.println(body);
        String startPattern = "<Valute ID=\"R01235\">"
                + "<NumCode>840</NumCode>"
                + "<CharCode>USD</CharCode>"
                + "<Nominal>1</Nominal>"
                + "<Name>Доллар США</Name>"
                + "<Value>";
        int valueStartIndex = body.indexOf(startPattern) + startPattern.length();
        body = body.substring(valueStartIndex);
        int valueEndIndex = body.indexOf("</Value></Valute>");
        return new Double(body.substring(0, valueEndIndex).replace(",", "."));
    }

    private String createUrlForInstant(Instant time) {
        DateFormat dateFormatDay = new SimpleDateFormat("dd/MM/yyyy");
        return "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + dateFormatDay.format(Date.from(time));
    }
}
