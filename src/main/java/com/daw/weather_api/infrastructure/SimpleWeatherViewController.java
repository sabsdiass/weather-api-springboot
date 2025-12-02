package com.daw.weather_api.infrastructure;

import com.daw.weather_api.application.SimpleWeather;
import com.daw.weather_api.application.SimpleWeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/spweather")
public class SimpleWeatherViewController {

    private final SimpleWeatherService simpleWeatherService;

    public SimpleWeatherViewController(SimpleWeatherService simpleWeatherService) {
        this.simpleWeatherService = simpleWeatherService;
    }

    @GetMapping
    public String getSpWeatherView(Model model) {
        SimpleWeather weather = simpleWeatherService.getSaoPauloWeather();
        model.addAttribute("weather", weather);
        return "spweather"; // usa templates/spweather.html
    }
}