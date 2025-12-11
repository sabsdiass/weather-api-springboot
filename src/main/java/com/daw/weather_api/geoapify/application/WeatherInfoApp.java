package com.daw.weather_api.geoapify.application;

import com.daw.weather_api.geoapify.domain.response.GeoApifyResponse;
import com.daw.weather_api.openweather.domain.response.WeatherResponse;
import com.daw.weather_api.openweather.domain.services.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.Normalizer;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class WeatherInfoApp {

    private final OpenWeatherService openWeatherService;

    public WeatherResponse getWeatherByCity(String city) throws IOException {
        GeoApifyResponse coords = resolveCoordinates(city);
        return openWeatherService.getCurrentWeather(coords.getLat(), coords.getLon());
    }

    private GeoApifyResponse resolveCoordinates(String city) {
        String normalized = normalize(city);

        if (normalized.contains("sao paulo")) {
            return geoResponse(-23.55052, -46.63331);
        }

        if (normalized.contains("rio de janeiro")) {
            return geoResponse(-22.90685, -43.17290);
        }

        if (normalized.contains("salvador")) {
            return geoResponse(-12.97775, -38.50163);
        }

        throw new RuntimeException("Cidade no soportada: " + city);
    }

    private GeoApifyResponse geoResponse(double lat, double lon) {
        GeoApifyResponse response = new GeoApifyResponse();
        response.setLat(lat);
        response.setLon(lon);
        return response;
    }

    private String normalize(String value) {
        String lower = value.toLowerCase(Locale.ROOT);
        String normalized = Normalizer.normalize(lower, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", ""); // remove acentos
    }
}
