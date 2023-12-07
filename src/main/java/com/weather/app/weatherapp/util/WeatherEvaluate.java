package com.weather.app.weatherapp.util;

public class WeatherEvaluate {

    public static String hint(double temperature){
        String hint = "default";
        if (temperature < -10){
            hint = "Оденьтесь по зимнему";
        }else if (temperature < 0){
            hint = "Оденьтесь по осеннему";
        }else if (temperature < 10){
            hint = "Оденьтесь по весеннему";
        } else if (temperature < 20) {
            hint = "Оденьтесь по летнему";
        }
        return hint;
    }
}
