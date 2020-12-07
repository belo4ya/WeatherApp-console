package com.belo4ya.models.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.belo4ya.exceptions.ApiException;
import com.belo4ya.exceptions.CityNotExistException;
import com.belo4ya.models.DataBase;

import java.io.IOException;
import java.util.*;

public class OpenWeatherMap extends AbstractWeatherService {
    private static OpenWeatherMap instance;

    private OpenWeatherMap() {
        apiKey = "8510c1d59e941a5cb1fa98b39e9121cc";
        apiUrl = "https://api.openweathermap.org/data/2.5/onecall";
    }

    public static OpenWeatherMap getInstance() {
        if (instance == null) {
            instance = new OpenWeatherMap();
        }
        return instance;
    }

    @Override
    public WeatherObject getCurrent(String city) throws ApiException, CityNotExistException, IOException {
        update(city);
        WeatherObject weatherObject = new WeatherObject();

        weatherObject.setDateTime(WeatherObject.secToMilli(currentNode.get("dt").asLong()));
        weatherObject.setTemp(currentNode.get("temp").asDouble());
        weatherObject.setFeelsLike(currentNode.get("feels_like").asDouble());
        weatherObject.setPressure(WeatherObject.pHaToMmHg(currentNode.get("pressure").asDouble()));
        weatherObject.setHumidity(currentNode.get("humidity").asInt());
        weatherObject.setWindSpeed(currentNode.get("wind_speed").asDouble());
        weatherObject.setWindDir(WeatherObject.windDegToDir(currentNode.get("wind_deg").asInt()));
        weatherObject.setDescription(currentNode.get("weather").elements().next().get("description").asText());

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

            weatherObject.setDateTime(WeatherObject.secToMilli(node.get("dt").asLong()));
            weatherObject.setTemp(node.get("temp").asDouble());
            weatherObject.setFeelsLike(node.get("feels_like").asDouble());
            weatherObject.setPressure(WeatherObject.pHaToMmHg(node.get("pressure").asDouble()));
            weatherObject.setHumidity(node.get("humidity").asInt());
            weatherObject.setWindSpeed(node.get("wind_speed").asDouble());
            weatherObject.setWindDir(WeatherObject.windDegToDir(node.get("wind_deg").asInt()));
            weatherObject.setDescription(node.get("weather").elements().next().get("description").asText());

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

            weatherObject.setDateTime(WeatherObject.secToMilli(node.get("dt").asLong()));

            JsonNode temp = node.get("temp");
            weatherObject.setDayTemp(temp.get("day").asDouble());
            weatherObject.setNightTemp(temp.get("night").asDouble());
            weatherObject.setEveTemp(temp.get("eve").asDouble());
            weatherObject.setMornTemp(temp.get("morn").asDouble());

            JsonNode feelsLike = node.get("feels_like");
            weatherObject.setDayFeelsLike(feelsLike.get("day").asDouble());
            weatherObject.setNightFeelsLike((feelsLike.get("night").asDouble()));
            weatherObject.setEveFeelsLike((feelsLike.get("eve").asDouble()));
            weatherObject.setMornFeelsLike((feelsLike.get("morn").asDouble()));

            weatherObject.setPressure(WeatherObject.pHaToMmHg(node.get("pressure").asDouble()));
            weatherObject.setHumidity(node.get("humidity").asInt());
            weatherObject.setWindSpeed(node.get("wind_speed").asDouble());
            weatherObject.setWindDir(WeatherObject.windDegToDir(node.get("wind_deg").asInt()));
            weatherObject.setDescription(node.get("weather").elements().next().get("description").asText());

            weatherObjects.add(weatherObject);
        }

        return weatherObjects;
    }

    @Override
    protected void setCurrentNode() {
        currentNode = mainNode.get("current");
    }

    @Override
    protected void setHourlyNode() {
        hourlyNode = mainNode.get("hourly");
    }

    @Override
    protected void setDailyNode() {
        dailyNode = mainNode.get("daily");
    }

    @Override
    protected HashMap<String, String> getParams(String city) throws CityNotExistException {
        Double[] coords = DataBase.getInstance().getCoordsByCity(city);
        String lat = coords[0].toString();
        String lon = coords[1].toString();

        return new HashMap<>() {{
            put("lat", lat);
            put("lon", lon);
            put("exclude", "minutely");
            put("units", "metric");
            put("appid", apiKey);
        }};
    }

    @Override
    protected HashMap<String, String> getHeaders() {
        return null;
    }
}
