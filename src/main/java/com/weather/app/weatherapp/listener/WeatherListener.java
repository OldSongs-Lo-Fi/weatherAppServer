package com.weather.app.weatherapp.listener;

import com.weather.app.weatherapp.weather.DayWeatherInfo;
import com.weather.app.weatherapp.weather.WeatherInfo;
import com.weather.app.weatherapp.weather.WeekWeatherInfo;

import java.util.Date;
import java.util.List;

public interface WeatherListener {

    DayWeatherInfo getTodayDayWeather(double lat, double lon);

    WeatherInfo getWeatherNow(double lat, double lon);

    List<WeatherInfo> getCoupleDays(double lat, double lon, int days);
}
