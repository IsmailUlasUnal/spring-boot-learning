package com.ismailulasunal.weatherfolksdev.controller;

import com.ismailulasunal.weatherfolksdev.controller.validation.CityNameConstraint;
import com.ismailulasunal.weatherfolksdev.dto.WeatherDto;
import com.ismailulasunal.weatherfolksdev.service.WeatherService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/weather")
@Validated
@Tag(name = "Open Weather API v1", description = "Open Weather API for filter by city for current temperature")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(
            method = "GET",
            summary = "Open Weather API summary",
            description = "Open Weather API for filter by city for current temperature",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = WeatherDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "City name is wrong. Re-try with a valid city name",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "429",
                            description = "Rate limit exceeded. Please try your request again later!",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )

    @GetMapping("/{city}")
    @RateLimiter(name = "basic")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable("city") @NotBlank @CityNameConstraint String city) {
        return new ResponseEntity<>(weatherService.getWeatherByCityName(city), HttpStatus.OK);
    }
}
