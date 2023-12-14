package com.weather.app.weatherapp.listener;

import java.util.List;

public interface ClothingAssistant {


    //return a List of urls of Pictures
    public List<String> getAdvice(double temperature, double humidity, double windSpeed);
}
