package com.ismailulasunal.weatherfolksdev.dto;

public record WeatherResponse (
        Request request,
        Location location,
        Current current
) {
}

