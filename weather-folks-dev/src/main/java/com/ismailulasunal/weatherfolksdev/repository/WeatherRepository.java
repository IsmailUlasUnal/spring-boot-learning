package com.ismailulasunal.weatherfolksdev.repository;

import com.ismailulasunal.weatherfolksdev.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WeatherRepository extends JpaRepository<WeatherEntity, UUID> {

    //@Query("Select * from entity where requestedCityName order by updateTime desc limit 1")
    Optional<WeatherEntity> findFirstByRequestedCityNameOrderByUpdatedTimeDesc(String city);

    Optional<WeatherEntity> findById(int id);

}
