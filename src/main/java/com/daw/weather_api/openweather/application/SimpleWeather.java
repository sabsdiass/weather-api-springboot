package com.daw.weather_api.openweather.application;

import lombok.Data;

@Data
public class SimpleWeather {
    private String city;
    private String country;
    private double temperature;
    private String description;
    private String icon;
}
