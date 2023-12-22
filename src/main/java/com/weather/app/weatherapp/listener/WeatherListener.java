package com.weather.app.weatherapp.listener;

import com.weather.app.weatherapp.weather.DayWeatherInfo;
import com.weather.app.weatherapp.weather.WeatherInfo;
import com.weather.app.weatherapp.weather.WeekWeatherInfo;

import java.util.Date;
import java.util.List;

public interface WeatherListener {

    DayWeatherInfo getTodayDayWeather(String city, String countryCode);

    WeatherInfo getWeatherNow(String city, String countryCode);

    List<WeatherInfo> getCoupleDays(String city, String countryCode, int days);
}
