package com.weather.app.weatherapp.weather;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class WeekWeatherInfo {
    List<DayWeatherInfo> week = new ArrayList<>(7);
}
