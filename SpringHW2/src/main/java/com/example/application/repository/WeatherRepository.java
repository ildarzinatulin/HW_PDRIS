package com.example.application.repository;

import com.example.application.domain.Weather;
import com.example.application.domain.WeatherId;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<Weather, WeatherId> {
}
