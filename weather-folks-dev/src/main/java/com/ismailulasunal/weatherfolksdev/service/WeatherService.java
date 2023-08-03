package com.ismailulasunal.weatherfolksdev.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ismailulasunal.weatherfolksdev.constants.Constants;
import com.ismailulasunal.weatherfolksdev.dto.WeatherDto;
import com.ismailulasunal.weatherfolksdev.dto.WeatherResponse;
import com.ismailulasunal.weatherfolksdev.model.WeatherEntity;
import com.ismailulasunal.weatherfolksdev.repository.WeatherRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"weathers"})
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
    }
    @Cacheable(key = "#city")
    public WeatherDto getWeatherByCityName(String city) {

        Optional<WeatherEntity> weatherEntityOptional = weatherRepository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(city);

        return weatherEntityOptional.map(weather -> {
            if (weather.getUpdatedTime().isBefore(LocalDateTime.now().minusMinutes(30))) {
                return WeatherDto.convert(getWeatherFromWeatherStack(city));
            }
            return WeatherDto.convert(weather);
        }).orElseGet(() -> WeatherDto.convert(getWeatherFromWeatherStack(city)));
    }

    public WeatherEntity findById(int theId) {
        Optional<WeatherEntity> result = weatherRepository.findById(theId);


        return result.orElseThrow(RuntimeException::new);
    }


    @CacheEvict(allEntries = true)
    @PostConstruct
    @Scheduled(fixedRateString = "10000")
    public void clearCache() {}
    private WeatherEntity getWeatherFromWeatherStack(String city) {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getWeatherStackUrl(city), String.class);

        try {
            WeatherResponse weatherResponse = objectMapper.readValue(responseEntity.getBody(), WeatherResponse.class);
            return saveWeatherEntity(city, weatherResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private String getWeatherStackUrl(String city) {
        return Constants.API_URL + Constants.ACCESS_KEY_PARAM + Constants.API_KEY + Constants.QUERY_KEY_PARAM + city;
    }


    private WeatherEntity saveWeatherEntity(String city, WeatherResponse weatherResponse) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        WeatherEntity weatherEntity = new WeatherEntity(
                city,
                weatherResponse.location().name(),
                weatherResponse.location().country(),
                weatherResponse.current().temperature(),
                LocalDateTime.now(),
                LocalDateTime.parse(weatherResponse.location().localtime(), dateTimeFormatter));

        return weatherRepository.save(weatherEntity);
    }
}
