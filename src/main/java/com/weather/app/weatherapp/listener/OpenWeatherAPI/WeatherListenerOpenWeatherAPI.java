package com.weather.app.weatherapp.listener.OpenWeatherAPI;

import com.weather.app.weatherapp.listener.WeatherListener;
import com.weather.app.weatherapp.weather.DayWeatherInfo;
import com.weather.app.weatherapp.weather.WeatherInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

public class WeatherListenerOpenWeatherAPI implements WeatherListener {

    private final String applicationKeyToken = "20f4e8418e51faa8d02e9f65016a7ed0";

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public WeatherInfo getWeatherNow(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + applicationKeyToken;
        ResponseEntity<WeatherResponseDTO> response = restTemplate.getForEntity(url, WeatherResponseDTO.class);
        if (Objects.isNull(response.getBody())){
            throw new RuntimeException("Error in request!");
        }
        return new WeatherInfo(new Date(), "its cold outside", kelvinToCelsius(response.getBody().getMain().getTemp()), response.getBody().getMain().getTemp());
    }

    @Override
    public List<WeatherInfo> getCoupleDays(double lat, double lon, int days) {
        String url = "http://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=20f4e8418e51faa8d02e9f65016a7ed0";
        ResponseEntity<WeatherFiveDaysForecastDTO> response = restTemplate.getForEntity(url, WeatherFiveDaysForecastDTO.class);
        if (Objects.isNull(response.getBody())){
            throw new RuntimeException("Error in request!");
        }
        List<WeatherInfo> dayWeatherInfos = response.getBody().getList().stream()
                .map((unit) ->{
                    return new WeatherInfo(
                            new Date(unit.dt), "TEST", kelvinToCelsius(unit.main.temp),unit.main.temp
                    );
                })
                .toList();
        return dayWeatherInfos;
    }


    @Override
    public DayWeatherInfo getTodayDayWeather(double lat, double lon) {
        String url = "http://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=20f4e8418e51faa8d02e9f65016a7ed0";
        ResponseEntity<WeatherFiveDaysForecastDTO> response = restTemplate.getForEntity(url, WeatherFiveDaysForecastDTO.class);
        if (Objects.isNull(response.getBody())){
            throw new RuntimeException("Error in request!");
        }
        List<WeatherInfo> dayWeatherInfos = response.getBody().getList().stream()
                .map((unit) ->{
                    return new WeatherInfo(
                            new Date(unit.dt), "TEST", kelvinToCelsius(unit.main.temp),unit.main.temp
                    );
                })
                .toList();
        return new DayWeatherInfo(dayWeatherInfos.get(0),dayWeatherInfos.get(2), dayWeatherInfos.get(4), dayWeatherInfos.get(6));   
    }


    public double kelvinToCelsius(double kelvin){
        return kelvin - 273.15;
    }





}
