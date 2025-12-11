package com.daw.weather_api.openweather.domain.services;

import com.daw.weather_api.openweather.domain.response.WeatherResponse;
import com.daw.weather_api.shared.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OpenWeatherService {

    @Value("${openweather.api.current_weather}")
    private String currentWeatherEndpoint;

    private final ApiService apiService;
    private final ObjectMapper objectMapper;

    public WeatherResponse getCurrentWeather(double lat, double lon) throws IOException {
        String url = currentWeatherEndpoint
                .replace("<lat>", String.valueOf(lat))
                .replace("<lon>", String.valueOf(lon));

        ResponseBody responseBody = apiService.get(url);

        return objectMapper.readValue(responseBody.string(), WeatherResponse.class);
    }
}
