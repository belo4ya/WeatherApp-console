package com.java.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.exceptions.ApiException;
import com.java.exceptions.CityNotExistException;

import java.io.IOException;
import java.util.*;

public class OpenWeatherMap {
    private static OpenWeatherMap instance;
    private final String apiKey = "8510c1d59e941a5cb1fa98b39e9121cc";
    private final String apiUrl = "https://api.openweathermap.org/data/2.5/onecall";
    private HttpRequester requester = null;
    private Date lastRequest = null;
    private JsonNode mainNode = null;
    private JsonNode currentNode = null;
    private JsonNode hourlyNode = null;
    private JsonNode dailyNode = null;

    public OpenWeatherMap() {
        this.requester = new HttpRequester();
    }

    public static OpenWeatherMap getInstance() {
        if (instance == null) {
            instance = new OpenWeatherMap();
        }
        return instance;
    }

    public Weather getCurrent(String city) throws ApiException, CityNotExistException, IOException {
        update(city);
        Weather weather = new Weather();

        weather.setDateTime(currentNode.get("dt").asLong());
        weather.setTemp(currentNode.get("temp").asDouble());
        weather.setFeelsLike(currentNode.get("feels_like").asDouble());
        weather.setPressure(currentNode.get("pressure").asDouble());
        weather.setHumidity(currentNode.get("humidity").asInt());
        weather.setWindSpeed(currentNode.get("wind_speed").asDouble());
        weather.setWindDeg(currentNode.get("wind_deg").asInt());
        weather.setDescription(currentNode.get("weather").elements().next().get("description").asText());

        return weather;
    }

    public ArrayList<Weather> getHourly(String city) throws ApiException, CityNotExistException, IOException {
        update(city);
        ArrayList<Weather> weatherList = new ArrayList<>();
        Iterator<JsonNode> elements = hourlyNode.get("hourly").elements();
        while (elements.hasNext()) {
            Weather weather = new Weather();
            JsonNode node = elements.next();

            weather.setDateTime(node.get("dt").asLong());
            weather.setTemp(node.get("temp").asDouble());
            weather.setFeelsLike(node.get("feels_like").asDouble());
            weather.setPressure(node.get("pressure").asDouble());
            weather.setHumidity(node.get("humidity").asInt());
            weather.setWindSpeed(node.get("wind_speed").asDouble());
            weather.setWindDeg(node.get("wind_deg").asInt());
            weather.setDescription(node.get("weather").elements().next().get("description").asText());
        }

        return weatherList;
    }

    public ArrayList<Weather> getDaily(String city) throws ApiException, CityNotExistException, IOException {
        update(city);
        ArrayList<Weather> weatherList = new ArrayList<>();


        return weatherList;
    }

    private void update(String city) throws ApiException, CityNotExistException, IOException {
        if (mainNode == null || lastRequest == null || new Date().getTime() - lastRequest.getTime() > 1) {
            Double[] coords = DataBase.getInstance().getCoordsByCity(city);
            String lat = coords[0].toString();
            String lon = coords[1].toString();

            HashMap<String, String> params = new HashMap<String, String>() {{
                put("lat", lat);
                put("lon", lon);
                put("exclude", "minutely");
                put("units", "metric");
                put("appid", apiKey);
            }};

            String response;
            try {
                response = requester.get(apiUrl, params);
                if (response == null) {
                    throw new ApiException();
                }
            } catch (IOException | InterruptedException e) {
                throw new ApiException();
            }

            ObjectMapper mapper = new ObjectMapper();
            mainNode = mapper.readTree(response);
            currentNode = mainNode.get("current");
            hourlyNode = mainNode.get("hourly");
            dailyNode = mainNode.get("daily");

            lastRequest = new Date();
        }
    }
}
