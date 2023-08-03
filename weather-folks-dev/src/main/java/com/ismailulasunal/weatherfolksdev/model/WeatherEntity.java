package com.ismailulasunal.weatherfolksdev.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // TODO I CHANGED THIS PART
    private String requestedCityName;
    private String cityName;
    private String country;
    private Integer temperature;
    private LocalDateTime updatedTime;
    private LocalDateTime responseLocalTime;

    // all args constructor
    public WeatherEntity(UUID id, String requestedCityName, String cityName, String country, Integer temperature, LocalDateTime updatedTime, LocalDateTime responseLocalTime) {
        this.id = id;
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updatedTime = updatedTime;
        this.responseLocalTime = responseLocalTime;
    }

    // without id constructor
    public WeatherEntity(String requestedCityName, String cityName, String country, Integer temperature, LocalDateTime updatedTime, LocalDateTime responseLocalTime) {
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updatedTime = updatedTime;
        this.responseLocalTime = responseLocalTime;
    }

    // no args constructor
    public WeatherEntity() {
    }


    // Getters
    public UUID getId() {
        return id;
    }

    public String getRequestedCityName() {
        return requestedCityName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public LocalDateTime getResponseLocalTime() {
        return responseLocalTime;
    }
}
