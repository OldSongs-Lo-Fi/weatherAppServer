package com.weather.app.weatherapp.weather;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class WeatherInfo {
    Date date;
    List<String> description;
    double temperatureInCelsius;
    double temperatureInKelvin;
    double windSpeed;
    double humidity;
}
