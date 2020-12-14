package com.belo4ya.models.weather;

import com.belo4ya.exceptions.ApiException;
import com.belo4ya.exceptions.CityNotExistException;
import com.belo4ya.models.DataBase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class WeatherBit extends AbstractWeatherService {
    private static WeatherBit instance;
    private String currentResponse;
    private String dailyResponse;
    private final String currentUrl;
    private final String dailyUrl;

    private WeatherBit() {
        apiKey = "9b7e8919b5ce4b09acbfc6b7c88fdf8f";
        currentUrl = "https://api.weatherbit.io/v2.0/current";
        dailyUrl = "https://api.weatherbit.io/v2.0/forecast/daily";
    }

    public static WeatherBit getInstance() {
        if (instance == null) {
            instance = new WeatherBit();
        }
        return instance;
    }

    @Override
    public WeatherObject getCurrent(String city) throws ApiException, CityNotExistException, IOException {
        update(city);
        WeatherObject weatherObject = new WeatherObject();
        JsonNode node = currentNode.get("data").elements().next();

        weatherObject.setDateTime(WeatherObject.secToMilli(node.get("ts").asLong()));
        weatherObject.setTemp(node.get("temp").asDouble());
        weatherObject.setFeelsLike(node.get("app_temp").asDouble());
        weatherObject.setPressure(WeatherObject.mbToMmHg(node.get("pres").asDouble()));
        weatherObject.setHumidity(node.get("rh").asInt());
        weatherObject.setWindSpeed(node.get("wind_spd").asDouble());
        weatherObject.setWindDir(node.get("wind_cdir").asText());
        weatherObject.setDescription(node.get("weather").get("description").asText());

        return weatherObject;
    }

    @Override
    public ArrayList<WeatherObject> getHourly(String city) throws ApiException, CityNotExistException, IOException {
        return null;
    }

    @Override
    public ArrayList<WeatherObject> getDaily(String city) throws ApiException, CityNotExistException, IOException {
        update(city);

        ArrayList<WeatherObject> weatherObjects = new ArrayList<>();
        Iterator<JsonNode> elements = dailyNode.get("data").elements();
        while (elements.hasNext()) {
            WeatherObject weatherObject = new WeatherObject();
            JsonNode node = elements.next();

            weatherObject.setDateTime(WeatherObject.secToMilli(node.get("ts").asLong()));

            weatherObject.setMornTemp(node.get("temp").asDouble());
            weatherObject.setDayTemp(node.get("max_temp").asDouble());
            weatherObject.setEveTemp(node.get("temp").asDouble());
            weatherObject.setNightTemp(node.get("min_temp").asDouble());

            weatherObject.setMornFeelsLike(node.get("min_temp").asDouble());
            weatherObject.setDayFeelsLike(node.get("app_max_temp").asDouble());
            weatherObject.setEveFeelsLike(node.get("low_temp").asDouble());
            weatherObject.setNightFeelsLike(node.get("app_min_temp").asDouble());

            weatherObject.setPressure(WeatherObject.mbToMmHg(node.get("pres").asDouble()));
            weatherObject.setHumidity(node.get("rh").asInt());
            weatherObject.setWindSpeed(node.get("wind_spd").asDouble());
            weatherObject.setWindDir(node.get("wind_cdir").asText());
            weatherObject.setDescription(node.get("weather").get("description").asText());

            weatherObjects.add(weatherObject);
        }

        return weatherObjects;
    }

    @Override
    protected void update(String city) throws ApiException, CityNotExistException, IOException {
        if (currentResponse == null || dailyResponse == null || lastRequest == null || lastCity == null ||
                !lastCity.equalsIgnoreCase(city) || new Date().getTime() - lastRequest.getTime() > 10000) {

            HashMap<String, String> params = getParams(city);

            try {
                currentResponse = requester.get(currentUrl, params);
                dailyResponse = requester.get(dailyUrl, params);
                System.out.println(currentResponse);
            } catch (IOException | InterruptedException e) {
                throw new ApiException();
            }

            setCurrentNode();
            setDailyNode();

            lastCity = city;
            lastRequest = new Date();
        }
    }

    @Override
    protected HashMap<String, String> getParams(String city) throws CityNotExistException {
        Double[] coords = DataBase.getInstance().getCoordsByCity(city);
        String lat = coords[0].toString();
        String lon = coords[1].toString();

        return new HashMap<>() {{
            put("lat", lat);
            put("lon", lon);
            put("units", "M");
            put("key", apiKey);
        }};
    }

    @Override
    protected HashMap<String, String> getHeaders() {
        return null;
    }

    @Override
    protected void setMainNode() throws IOException {

    }

    @Override
    protected void setCurrentNode() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        currentNode = mapper.readTree(currentResponse);
    }

    @Override
    protected void setHourlyNode() {

    }

    @Override
    protected void setDailyNode() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        dailyNode = mapper.readTree(dailyResponse);
    }
}
