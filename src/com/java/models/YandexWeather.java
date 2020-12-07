package com.java.models;

import com.java.exceptions.CityNotExistException;

import java.util.HashMap;

public class YandexWeather extends AbstractWeatherService {
    private static YandexWeather instance;

    private YandexWeather() {
        apiKey = "ebee69a6-eae5-49f5-a6e7-d6b5af9fcb74";
        apiUrl = "https://api.weather.yandex.ru/v2/forecast";
    }

    public static YandexWeather getInstance() {
        if (instance == null) {
            instance = new YandexWeather();
        }
        return instance;
    }

    @Override
    protected void setCurrentNode() {
        currentNode = mainNode.get("fact");
    }

    @Override
    protected void setHourlyNode() {
        hourlyNode = mainNode.get("forecasts");
    }

    @Override
    protected void setDailyNode() {
        dailyNode = mainNode.get("forecasts");
    }

    @Override
    protected HashMap<String, String> getParams(String city) throws CityNotExistException {
        Double[] coords = DataBase.getInstance().getCoordsByCity(city);
        String lat = coords[0].toString();
        String lon = coords[1].toString();

        return new HashMap<>() {{
            put("lat", lat);
            put("lon", lon);
            put("lang", "en_US");
            put("limit", "7");
            put("hours", "true");
            put("X-Yandex-API-Key", apiKey);
        }};
    }
}