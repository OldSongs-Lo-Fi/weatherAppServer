package com.weather.app.weatherapp.config;

import com.weather.app.weatherapp.listener.OpenWeatherAPI.WeatherListenerOpenWeatherAPI;
import com.weather.app.weatherapp.listener.WeatherListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherWebConfig {
    @Bean
    public WeatherListener weatherListener(){
        return new WeatherListenerOpenWeatherAPI();
    }
}
