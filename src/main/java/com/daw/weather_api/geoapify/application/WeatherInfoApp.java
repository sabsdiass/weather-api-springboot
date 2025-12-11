package com.daw.weather_api.geoapify.application;

import com.daw.weather_api.geoapify.domain.response.GeoApifyResponse;
import com.daw.weather_api.geoapify.domain.services.GeoApifyService;
import com.daw.weather_api.openweather.domain.response.WeatherResponse;
import com.daw.weather_api.openweather.domain.services.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class WeatherInfoApp {

    private final GeoApifyService geoApifyService;
    private final OpenWeatherService openWeatherService;

    public WeatherResponse getWeatherByCity(String city) throws IOException {
        GeoApifyResponse coords = geoApifyService.geocoding(city);

        if (coords == null) {
            coords = fallbackCoordinates(city);
        }

        return openWeatherService.getCurrentWeather(
                coords.getLat(),
                coords.getLon()
        );
    }

    private GeoApifyResponse fallbackCoordinates(String city) {
        String normalized = city == null ? "" : city.toLowerCase();

        GeoApifyResponse response = new GeoApifyResponse();

        if (normalized.contains("são paulo") || normalized.contains("sao paulo")) {
            response.setLat(-23.5505);
            response.setLon(-46.6333);
        } else if (normalized.contains("rio de janeiro")) {
            response.setLat(-22.9068);
            response.setLon(-43.1729);
        } else if (normalized.contains("salvador")) {
            response.setLat(-12.9777);
            response.setLon(-38.5016);
        } else {
            response.setLat(-23.5505);
            response.setLon(-46.6333);
        }

        return response;
    }
}
