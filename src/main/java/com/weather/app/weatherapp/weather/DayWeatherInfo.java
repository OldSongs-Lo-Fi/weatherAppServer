package com.weather.app.weatherapp.weather;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DayWeatherInfo {

    //6 oc
    WeatherInfo morning;
    //12 oc
    WeatherInfo noon;
    //18 oc
    WeatherInfo evening;
    //00 oc
    WeatherInfo night;


}
