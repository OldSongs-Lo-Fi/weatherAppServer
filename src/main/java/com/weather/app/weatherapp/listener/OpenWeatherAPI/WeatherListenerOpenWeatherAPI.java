package com.weather.app.weatherapp.listener.OpenWeatherAPI;

import com.weather.app.weatherapp.listener.ClothingAssistant;
import com.weather.app.weatherapp.listener.WeatherListener;
import com.weather.app.weatherapp.weather.DayWeatherInfo;
import com.weather.app.weatherapp.weather.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

public class WeatherListenerOpenWeatherAPI implements WeatherListener {

    private final String applicationKeyToken = "c3befe9ac4e39e8834496f294a944a9b";

    RestTemplate restTemplate = new RestTemplate();

    ClothingAssistant clothingAssistant;

    public WeatherListenerOpenWeatherAPI(ClothingAssistant clothingAssistant) {
        this.clothingAssistant = clothingAssistant;
    }

    @Override
    public WeatherInfo getWeatherNow(String city, String countryCode) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + countryCode + "&appid=" + applicationKeyToken;
        ResponseEntity<WeatherResponseDTO> response = restTemplate.getForEntity(url, WeatherResponseDTO.class);
        if (Objects.isNull(response.getBody())){
            throw new RuntimeException("Error in request!");
        }
        double tempK =  response.getBody().getMain().getTemp();
        double tempC = kelvinToCelsius(tempK);
        double windSpeed = response.getBody().getWind().getSpeed();
        double humidity = response.getBody().getMain().getHumidity();

        return new WeatherInfo(new Date(), clothingAssistant.getAdvice(tempC, humidity, windSpeed), tempC, tempK, windSpeed, humidity);
    }

    @Override
    public List<WeatherInfo> getCoupleDays(String city, String countryCode, int days) {
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "," + countryCode + "&appid=" + applicationKeyToken;
        ResponseEntity<WeatherFiveDaysForecastDTO> response = restTemplate.getForEntity(url, WeatherFiveDaysForecastDTO.class);
        if (Objects.isNull(response.getBody())){
            throw new RuntimeException("Error in request!");
        }
        List<WeatherInfo> dayWeatherInfos = response.getBody().getList().stream()
                .map((unit) ->{
                    double tempK =  unit.main.temp;
                    double tempC = kelvinToCelsius(tempK);
                    double windSpeed = unit.wind.speed;
                    double humidity = unit.main.humidity;

                    return new WeatherInfo(
                            new Date(unit.dt), clothingAssistant.getAdvice(tempC, humidity, windSpeed), tempC, tempK, windSpeed, humidity
                    );
                })
                .toList();
        return dayWeatherInfos;
    }


    @Override
    public DayWeatherInfo getTodayDayWeather(String city, String countryCode) {
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "," + countryCode + "&appid=" + applicationKeyToken;
        ResponseEntity<WeatherFiveDaysForecastDTO> response = restTemplate.getForEntity(url, WeatherFiveDaysForecastDTO.class);
        if (Objects.isNull(response.getBody())){
            throw new RuntimeException("Error in request!");
        }
        List<WeatherInfo> dayWeatherInfos = response.getBody().getList().stream()
                .map((unit) ->{
                    double tempK =  unit.main.temp;
                    double tempC = kelvinToCelsius(tempK);
                    double windSpeed = unit.wind.speed;
                    double humidity = unit.main.humidity;

                    return new WeatherInfo(
                            new Date(unit.dt), clothingAssistant.getAdvice(tempC, humidity, windSpeed), tempC, tempK, windSpeed, humidity
                    );
                })
                .toList();
        DayWeatherInfo dayWeatherInfo = new DayWeatherInfo(null,null,null,null);
        for (WeatherInfo info: dayWeatherInfos) {
            switch (info.getDate().getHours()){
                case 6 -> dayWeatherInfo.setMorning(info);
                case 12 -> dayWeatherInfo.setNoon(info);
                case 18 -> dayWeatherInfo.setEvening(info);
                case 0 -> dayWeatherInfo.setNight(info);
            }
        }
        return dayWeatherInfo;
    }


    private double kelvinToCelsius(double kelvin){
        return kelvin - 273.15;
    }





}
