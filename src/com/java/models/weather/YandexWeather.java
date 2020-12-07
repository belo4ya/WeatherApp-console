package com.java.models.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.java.exceptions.ApiException;
import com.java.exceptions.CityNotExistException;
import com.java.models.DataBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
    public WeatherObject getCurrent(String city) throws ApiException, CityNotExistException, IOException {
        update(city);
        WeatherObject weatherObject = new WeatherObject();

        weatherObject.setDateTime(WeatherObject.secToMilli(mainNode.get("now").asLong()));
        weatherObject.setTemp(currentNode.get("temp").asDouble());
        weatherObject.setFeelsLike(currentNode.get("feels_like").asDouble());
        weatherObject.setPressure(currentNode.get("pressure_mm").asDouble());
        weatherObject.setHumidity(currentNode.get("humidity").asInt());
        weatherObject.setWindSpeed(currentNode.get("wind_speed").asDouble());
        weatherObject.setWindDir(currentNode.get("wind_dir").asText());
        weatherObject.setDescription(currentNode.get("condition").asText());

        return weatherObject;
    }

    @Override
    public ArrayList<WeatherObject> getHourly(String city) throws ApiException, CityNotExistException, IOException {
        update(city);
        ArrayList<WeatherObject> weatherObjects = new ArrayList<>();
        Iterator<JsonNode> elements = hourlyNode.elements();
        while (elements.hasNext()) {
            WeatherObject weatherObject = new WeatherObject();
            JsonNode node = elements.next();

            weatherObject.setDateTime(WeatherObject.secToMilli(node.get("hour_ts").asLong()));
            weatherObject.setTemp(node.get("temp").asDouble());
            weatherObject.setFeelsLike(node.get("feels_like").asDouble());
            weatherObject.setPressure(node.get("pressure_mm").asDouble());
            weatherObject.setHumidity(node.get("humidity").asInt());
            weatherObject.setWindSpeed(node.get("wind_speed").asDouble());
            weatherObject.setWindDir(node.get("wind_dir").asText());
            weatherObject.setDescription(node.get("condition").asText());

            weatherObjects.add(weatherObject);
        }

        return weatherObjects;
    }

    @Override
    public ArrayList<WeatherObject> getDaily(String city) throws ApiException, CityNotExistException, IOException {
        update(city);
        ArrayList<WeatherObject> weatherObjects = new ArrayList<>();
        Iterator<JsonNode> elements = dailyNode.elements();
        while (elements.hasNext()) {
            WeatherObject weatherObject = new WeatherObject();
            JsonNode node = elements.next();

            weatherObject.setDateTime(WeatherObject.secToMilli(node.get("date_ts").asLong()));

            JsonNode parts = node.get("parts");
            weatherObject.setDayTemp(parts.get("day").get("temp_avg").asDouble());
            weatherObject.setNightTemp(parts.get("night").get("temp_avg").asDouble());
            weatherObject.setEveTemp(parts.get("evening").get("temp_avg").asDouble());
            weatherObject.setMornTemp(parts.get("morning").get("temp_avg").asDouble());

            weatherObject.setDayFeelsLike(parts.get("day").get("feels_like").asDouble());
            weatherObject.setNightFeelsLike(parts.get("night").get("feels_like").asDouble());
            weatherObject.setEveFeelsLike(parts.get("evening").get("feels_like").asDouble());
            weatherObject.setMornFeelsLike(parts.get("morning").get("feels_like").asDouble());

            weatherObject.setPressure(parts.get("day").get("pressure_mm").asDouble());
            weatherObject.setHumidity(parts.get("day").get("humidity").asInt());
            weatherObject.setWindSpeed(parts.get("day").get("wind_speed").asDouble());
            weatherObject.setWindDir(parts.get("day").get("wind_dir").asText());
            weatherObject.setDescription(parts.get("day").get("condition").asText());

            weatherObjects.add(weatherObject);
        }

        return weatherObjects;
    }

    @Override
    protected void setCurrentNode() {
        currentNode = mainNode.get("fact");
    }

    @Override
    protected void setHourlyNode() {
        hourlyNode = mainNode.get("forecasts").elements().next().get("hours");
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
        }};
    }

    @Override
    protected HashMap<String, String> getHeaders() {
        return new HashMap<>() {{
            put("X-Yandex-API-Key", apiKey);
        }};
    }
}
