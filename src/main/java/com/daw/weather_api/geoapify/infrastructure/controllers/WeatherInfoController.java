package com.daw.weather_api.geoapify.infrastructure.controllers;

import com.daw.weather_api.geoapify.application.WeatherInfoApp;
import com.daw.weather_api.openweather.domain.response.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WeatherInfoController {

    private final WeatherInfoApp app;

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather(@RequestParam String city) {
        try {
            WeatherResponse response = app.getWeatherByCity(city);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // log no console
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao obter clima: " + e.getMessage());
        }
    }
}
