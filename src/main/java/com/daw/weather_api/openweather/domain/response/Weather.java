package com.daw.weather_api.openweather.domain.response;

import lombok.Data;

@Data
public class Weather {
    private long id;
    private String main;
    private String description;
    private String icon;
}
