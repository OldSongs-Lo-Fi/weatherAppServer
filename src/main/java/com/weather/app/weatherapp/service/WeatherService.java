package com.weather.app.weatherapp.service;

import com.weather.app.weatherapp.entity.User;
import com.weather.app.weatherapp.listener.WeatherListener;
import com.weather.app.weatherapp.weather.DayWeatherInfo;
import com.weather.app.weatherapp.weather.WeatherInfo;
import com.weather.app.weatherapp.weather.WeekWeatherInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeatherService {

    int days = 4;
    final
    WeatherListener weatherListener;

    public WeatherService(WeatherListener weatherListener) {
        this.weatherListener = weatherListener;
    }

    public WeatherInfo getCurrentWeather(User user){
        System.out.println("User lat: " + user.getLatitude() + " Lon: " + user.getLongitude());
        return weatherListener.getWeatherNow(user.getLatitude(), user.getLongitude());
    }

    public DayWeatherInfo getTodayWeather(User user) {
        return weatherListener.getTodayDayWeather(user.getLatitude(), user.getLongitude());
    }

    public List<WeatherInfo> getCoupleDays(User user) {
        return weatherListener.getCoupleDays(user.getLatitude(), user.getLongitude(), days);
    }
}
