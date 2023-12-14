package com.weather.app.weatherapp.config;

import com.weather.app.weatherapp.listener.ClothingAssistant;
import com.weather.app.weatherapp.listener.EthalonMethod.EthalonMethodClothingAssistant;
import com.weather.app.weatherapp.listener.OpenWeatherAPI.WeatherListenerOpenWeatherAPI;
import com.weather.app.weatherapp.listener.WeatherListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class WeatherWebConfig {
    @Autowired
    @Lazy
    private ClothingAssistant clothingAssistant;

    @Bean
    public WeatherListener weatherListener() {
        return new WeatherListenerOpenWeatherAPI(clothingAssistant);
    }

    @Bean
    public ClothingAssistant clothingAssistant() {
        return new EthalonMethodClothingAssistant();
    }
}

