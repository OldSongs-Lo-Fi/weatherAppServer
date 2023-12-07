package com.weather.app.weatherapp.controller;


import com.weather.app.weatherapp.entity.User;
import com.weather.app.weatherapp.service.WeatherService;
import com.weather.app.weatherapp.weather.DayWeatherInfo;
import com.weather.app.weatherapp.weather.WeatherInfo;
import com.weather.app.weatherapp.weather.WeekWeatherInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users/weather")
public class WeatherController {

    final
    WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/currentWeather")
    public ResponseEntity<WeatherInfo> getCurrentWeather(@AuthenticationPrincipal User user){
        System.out.println("User "  + user.getUsername());
        return ResponseEntity.status(200)
                .body(weatherService.getCurrentWeather(user));
    }

    @GetMapping("/getTodayWeather")
    public ResponseEntity<DayWeatherInfo> getTodayWeather(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200)
                .body(weatherService.getTodayWeather(user));
    }
    @GetMapping("/4")
    public ResponseEntity<List<WeatherInfo>> getCoupleDays(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200)
                .body(weatherService.getCoupleDays(user));

    }

}
