package com.daw.weather_api.openweather.domain.response;

import lombok.Data;

@Data
public class Wind {
    private double speed;
    private long deg;
}
