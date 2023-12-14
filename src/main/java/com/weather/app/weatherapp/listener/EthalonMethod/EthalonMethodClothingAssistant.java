package com.weather.app.weatherapp.listener.EthalonMethod;

import com.weather.app.weatherapp.listener.ClothingAssistant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EthalonMethodClothingAssistant  implements ClothingAssistant {

    List<WeatherData> ethalons = new ArrayList<>();


    public EthalonMethodClothingAssistant() {
        prepare();
    }

    @Override
    public List<String> getAdvice(double temperature, double humidity, double windSpeed) {
        WeatherData closestEthalon = findClosestEthalon(temperature, humidity, windSpeed);
        return switch (closestEthalon.getWeatherClass()){
            case COLD -> Arrays.asList("https://images.prom.ua/1031768953_w600_h600_1031768953.jpg",
                        "https://img.freepik.com/free-vector/collection-of-young-people-wearing-winter-clothes_23-2148350940.jpg",
                        "https://momentum.in.ua/sites/default/files/styles/370x473/public/product_images/8ada4152764596d4078e837c499ce6a3.jpg?itok=94RYvq3s");
            case COLD_WET -> Arrays.asList("https://media1.popsugar-assets.com/files/thumbor/WoMk1FxsWp3a8VYInOK_rblhujY/fit-in/2048xorig/filters:format_auto-!!-:strip_icc-!!-/2016/01/20/058/n/1922564/2476dbb596684bd8_GettyImages-505729648_master/i/Layer-up-your-knits-look-so-cosy-youll-feel-like-youre-still.jpg");
            case COLD_WINDY -> Arrays.asList("https://media1.popsugar-assets.com/files/thumbor/WoMk1FxsWp3a8VYInOK_rblhujY/fit-in/2048xorig/filters:format_auto-!!-:strip_icc-!!-/2016/01/20/058/n/1922564/2476dbb596684bd8_GettyImages-505729648_master/i/Layer-up-your-knits-look-so-cosy-youll-feel-like-youre-still.jpg");
            case COLD_WET_WINDY -> Arrays.asList("https://media1.popsugar-assets.com/files/thumbor/WoMk1FxsWp3a8VYInOK_rblhujY/fit-in/2048xorig/filters:format_auto-!!-:strip_icc-!!-/2016/01/20/058/n/1922564/2476dbb596684bd8_GettyImages-505729648_master/i/Layer-up-your-knits-look-so-cosy-youll-feel-like-youre-still.jpg");

            case NORMAL -> Arrays.asList("https://i.pinimg.com/1200x/78/80/35/78803531f52ec5ace918925f999e7ffc.jpg");
            case NORMAL_WET -> Arrays.asList("https://i.pinimg.com/1200x/78/80/35/78803531f52ec5ace918925f999e7ffc.jpg");
            case NORMAL_WINDY -> Arrays.asList("https://i.pinimg.com/1200x/78/80/35/78803531f52ec5ace918925f999e7ffc.jpg");
            case NORMAL_WET_WINDY -> Arrays.asList("https://i.pinimg.com/1200x/78/80/35/78803531f52ec5ace918925f999e7ffc.jpg");

            case HOT -> Arrays.asList("https://static2.issaplus.com/wa-data/public/shop/products/12/29/82912/images/195923/195923.600x900.jpg",
                    "https://stylecaster.com/wp-content/uploads/2014/05/cotton.jpg");
            case HOT_WET -> Arrays.asList("https://regalgentleman.com/cdn/shop/articles/Summer_Oufits_1024x1024_1eb22abe-e47b-495b-9e23-3340c005fbfb.jpg?v=1536133088");
            case HOT_WINDY -> Arrays.asList("https://regalgentleman.com/cdn/shop/articles/Summer_Oufits_1024x1024_1eb22abe-e47b-495b-9e23-3340c005fbfb.jpg?v=1536133088");
            case HOT_WET_WINDY -> Arrays.asList("https://regalgentleman.com/cdn/shop/articles/Summer_Oufits_1024x1024_1eb22abe-e47b-495b-9e23-3340c005fbfb.jpg?v=1536133088");
            default -> Arrays.asList("https://i.pinimg.com/1200x/78/80/35/78803531f52ec5ace918925f999e7ffc.jpg");
        };
    }

    private List<WeatherData> learn(List<WeatherData> ... lists){

        List<WeatherData> ethalons = new ArrayList<>();

        for (List<WeatherData> studyObjects: lists) {

            double t = 0;
            double h = 0;
            double w = 0;

            WeatherClass weatherClass = studyObjects.get(0).getWeatherClass();

            for (WeatherData weather: studyObjects) {
                t += weather.getTemperature();
                h += weather.getHumidity();
                w += weather.getWindSpeed();
            }

            t = t / studyObjects.size();
            h = h / studyObjects.size();
            w = w / studyObjects.size();

            ethalons.add(new WeatherData(t, h, w, weatherClass));
        }

        return ethalons;
    }

    private WeatherData findClosestEthalon(double temperature, double humidity, double windSpeed){
        double minDistance = 10000;
        WeatherData closestEthalon = new WeatherData(0,0,0);

        for (WeatherData ethalon: ethalons) {
            double distance = Math.sqrt(
                    Math.pow(temperature - ethalon.getTemperature(), 2)
                            + Math.pow(humidity - ethalon.getHumidity(), 2)
                            + Math.pow(windSpeed - ethalon.getWindSpeed(), 2));
            if (distance < minDistance){
                minDistance = distance;
                closestEthalon = ethalon;
            }
        }
        return closestEthalon;
    }

    private void prepare(){
        List<WeatherData> cold = new ArrayList<>();
        List<WeatherData> coldWet = new ArrayList<>();
        List<WeatherData> coldWindy = new ArrayList<>();
        List<WeatherData> coldWetWindy = new ArrayList<>();

        List<WeatherData> normal = new ArrayList<>();
        List<WeatherData> normalWet = new ArrayList<>();
        List<WeatherData> normalWindy = new ArrayList<>();
        List<WeatherData> normalWetWindy = new ArrayList<>();

        List<WeatherData> hot = new ArrayList<>();
        List<WeatherData> hotWet = new ArrayList<>();
        List<WeatherData> hotWindy = new ArrayList<>();
        List<WeatherData> hotWetWindy = new ArrayList<>();

        cold.add(new WeatherData(-5, 70, 15, WeatherClass.COLD));
        cold.add(new WeatherData(-8, 75, 12, WeatherClass.COLD));
        cold.add(new WeatherData(-10, 80, 10, WeatherClass.COLD));
        cold.add(new WeatherData(-3, 65, 18, WeatherClass.COLD));
        cold.add(new WeatherData(-6, 72, 14, WeatherClass.COLD));

        coldWet.add(new WeatherData(0, 95, 20, WeatherClass.COLD_WET));
        coldWet.add(new WeatherData(2, 90, 18, WeatherClass.COLD_WET));
        coldWet.add(new WeatherData(-1, 98, 22, WeatherClass.COLD_WET));
        coldWet.add(new WeatherData(5, 93, 25, WeatherClass.COLD_WET));
        coldWet.add(new WeatherData(3, 96, 21, WeatherClass.COLD_WET));

        coldWindy.add(new WeatherData(-2, 80, 30, WeatherClass.COLD_WINDY));
        coldWindy.add(new WeatherData(-4, 85, 28, WeatherClass.COLD_WINDY));
        coldWindy.add(new WeatherData(-6, 78, 32, WeatherClass.COLD_WINDY));
        coldWindy.add(new WeatherData(-1, 82, 29, WeatherClass.COLD_WINDY));
        coldWindy.add(new WeatherData(-3, 88, 31, WeatherClass.COLD_WINDY));

        coldWetWindy.add(new WeatherData(+2, 95, 29, WeatherClass.COLD_WET_WINDY));
        coldWetWindy.add(new WeatherData(1, 90, 27, WeatherClass.COLD_WET_WINDY));
        coldWetWindy.add(new WeatherData(4, 98, 30, WeatherClass.COLD_WET_WINDY));
        coldWetWindy.add(new WeatherData(0, 93, 32, WeatherClass.COLD_WET_WINDY));
        coldWetWindy.add(new WeatherData(3, 96, 28, WeatherClass.COLD_WET_WINDY));

        normal.add(new WeatherData(20, 50, 10, WeatherClass.NORMAL));
        normal.add(new WeatherData(22, 48, 12, WeatherClass.NORMAL));
        normal.add(new WeatherData(18, 55, 8, WeatherClass.NORMAL));
        normal.add(new WeatherData(25, 45, 15, WeatherClass.NORMAL));
        normal.add(new WeatherData(21, 52, 11, WeatherClass.NORMAL));

        normalWet.add(new WeatherData(25, 80, 15, WeatherClass.NORMAL_WET));
        normalWet.add(new WeatherData(27, 78, 17, WeatherClass.NORMAL_WET));
        normalWet.add(new WeatherData(23, 85, 13, WeatherClass.NORMAL_WET));
        normalWet.add(new WeatherData(28, 75, 20, WeatherClass.NORMAL_WET));
        normalWet.add(new WeatherData(24, 82, 16, WeatherClass.NORMAL_WET));

        normalWindy.add(new WeatherData(22, 60, 25, WeatherClass.NORMAL_WINDY));
        normalWindy.add(new WeatherData(20, 65, 22, WeatherClass.NORMAL_WINDY));
        normalWindy.add(new WeatherData(24, 58, 28, WeatherClass.NORMAL_WINDY));
        normalWindy.add(new WeatherData(18, 62, 26, WeatherClass.NORMAL_WINDY));
        normalWindy.add(new WeatherData(21, 68, 24, WeatherClass.NORMAL_WINDY));

        normalWetWindy.add(new WeatherData(18, 90, 22, WeatherClass.NORMAL_WET_WINDY));
        normalWetWindy.add(new WeatherData(16, 88, 20, WeatherClass.NORMAL_WET_WINDY));
        normalWetWindy.add(new WeatherData(20, 92, 24, WeatherClass.NORMAL_WET_WINDY));
        normalWetWindy.add(new WeatherData(15, 85, 26, WeatherClass.NORMAL_WET_WINDY));
        normalWetWindy.add(new WeatherData(19, 89, 23, WeatherClass.NORMAL_WET_WINDY));

        hot.add(new WeatherData(30, 40, 5, WeatherClass.HOT));
        hot.add(new WeatherData(32, 38, 7, WeatherClass.HOT));
        hot.add(new WeatherData(28, 42, 3, WeatherClass.HOT));
        hot.add(new WeatherData(35, 36, 8, WeatherClass.HOT));
        hot.add(new WeatherData(31, 39, 6, WeatherClass.HOT));

        hotWet.add(new WeatherData(35, 75, 8, WeatherClass.HOT_WET));
        hotWet.add(new WeatherData(37, 73, 10, WeatherClass.HOT_WET));
        hotWet.add(new WeatherData(33, 78, 6, WeatherClass.HOT_WET));
        hotWet.add(new WeatherData(38, 70, 12, WeatherClass.HOT_WET));
        hotWet.add(new WeatherData(34, 76, 9, WeatherClass.HOT_WET));

        hotWindy.add(new WeatherData(32, 55, 12, WeatherClass.HOT_WINDY));
        hotWindy.add(new WeatherData(30, 58, 10, WeatherClass.HOT_WINDY));
        hotWindy.add(new WeatherData(34, 52, 14, WeatherClass.HOT_WINDY));
        hotWindy.add(new WeatherData(29, 60, 11, WeatherClass.HOT_WINDY));
        hotWindy.add(new WeatherData(33, 54, 13, WeatherClass.HOT_WINDY));

        hotWetWindy.add(new WeatherData(28, 85, 10, WeatherClass.HOT_WET_WINDY));
        hotWetWindy.add(new WeatherData(26, 88, 8, WeatherClass.HOT_WET_WINDY));
        hotWetWindy.add(new WeatherData(30, 82, 12, WeatherClass.HOT_WET_WINDY));
        hotWetWindy.add(new WeatherData(25, 90, 9, WeatherClass.HOT_WET_WINDY));
        hotWetWindy.add(new WeatherData(29, 84, 11, WeatherClass.HOT_WET_WINDY));

        ethalons = learn(cold, coldWindy, coldWet, coldWetWindy,
                normal, normalWet, normalWindy, normalWetWindy,
                hot, hotWindy, hotWet, hotWetWindy);
    }
}
