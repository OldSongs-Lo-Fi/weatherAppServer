package com.weather.app.weatherapp.listener.OpenWeatherAPI;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherFiveDaysForecastDTO {

    public static class Main {
        public double temp;
        public double feels_like;

        @JsonProperty("temp_min")
        public double tempMin;

        @JsonProperty("temp_max")
        public double tempMax;

        public int pressure;
        public int humidity;

        @JsonProperty("sea_level")
        public int seaLevel;

        @JsonProperty("grnd_level")
        public int groundLevel;

        @JsonProperty("temp_kf")
        public double tempKf;
    }

    public static class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public static class Clouds {
        public int all;
    }

    public static class Wind {
        public double speed;
        public int deg;
        public double gust;
    }

    public static class Sys {
        public String pod;
    }

    public static class ListItem {
        public long dt;
        public Main main;
        public List<Weather> weather;
        public Clouds clouds;
        public Wind wind;
        public int visibility;
        public double pop;
        public Sys sys;

        @JsonProperty("dt_txt")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        public String dtTxt;
    }

    public static class City {
        public int id;
        public String name;
        public Coord coord;
        public String country;
        public int population;
        public int timezone;
        public long sunrise;
        public long sunset;
    }

    public static class Coord {
        public double lat;
        public double lon;
    }

    public int cod;
    public int message;
    public int cnt;
    public List<ListItem> list;
    public City city;
}
