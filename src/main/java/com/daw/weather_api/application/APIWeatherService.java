package com.daw.weather_api.application;

import com.daw.weather_api.domain.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIWeatherService {

    @Value("${openweather.base-url}")
    private String baseUrl;

    @Value("${openweather.lat}")
    private String lat;

    @Value("${openweather.lon}")
    private String lon;

    @Value("${openweather.units}")
    private String units;

    @Value("${openweather.lang}")
    private String lang;

    @Value("${openweather.api-key}")
    private String apiKey;

    private String montarUrl() {
        return String.format(
                "%s?lat=%s&lon=%s&units=%s&lang=%s&appid=%s",
                baseUrl, lat, lon, units, lang, apiKey
        );
    }

    public WeatherResponse buscarClima() {
        String url = montarUrl();
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}
