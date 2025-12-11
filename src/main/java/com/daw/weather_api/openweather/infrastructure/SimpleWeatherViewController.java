package com.daw.weather_api.openweather.infrastructure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleWeatherViewController {

    @GetMapping("/spweather")
    public String weatherView() {
        return "spweather";
    }
}
