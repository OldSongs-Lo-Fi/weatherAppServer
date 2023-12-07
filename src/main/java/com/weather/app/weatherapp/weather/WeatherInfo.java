package com.weather.app.weatherapp.weather;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class WeatherInfo {
    Date date;
    String description;
    double temperatureInCelsius;
    double temperatureInKelvin;
}
