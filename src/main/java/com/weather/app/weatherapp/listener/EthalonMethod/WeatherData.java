package com.weather.app.weatherapp.listener.EthalonMethod;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherData {

    double temperature;
    double humidity;
    double windSpeed;

    WeatherClass weatherClass;

    public WeatherData(double temperature, double humidity, double windSpeed) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }
}
