package com.daw.weather_api.openweather.domain.response;

import lombok.Data;

@Data
public class Sys {
    private long type;
    private long id;
    private String country;
    private long sunrise;
    private long sunset;
}
