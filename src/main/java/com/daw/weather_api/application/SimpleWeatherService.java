package com.daw.weather_api.application;

import com.daw.weather_api.domain.response.Weather;
import com.daw.weather_api.domain.response.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class SimpleWeatherService {

    private final APIWeatherService apiWeatherService;

    public SimpleWeatherService(APIWeatherService apiWeatherService) {
        this.apiWeatherService = apiWeatherService;
    }

    public SimpleWeather getSaoPauloWeather() {
        // resposta da API que está em weatherservice, ele monta a url
        WeatherResponse response = apiWeatherService.buscarClima();

        SimpleWeather dto = new SimpleWeather();

        // setei a cidade pra sempre me devolver são paulo
        dto.setCity("São Paulo");
        dto.setCountry("BR");

        // dados que recebo da api
        dto.setTemperature(response.getMain().getTemp());

        // info de clima vem no primeiro elemento do array "weather"
        Weather weather = response.getWeather()[0];
        dto.setDescription(weather.getDescription());
        dto.setIcon(weather.getIcon());

        return dto;
    }
}
