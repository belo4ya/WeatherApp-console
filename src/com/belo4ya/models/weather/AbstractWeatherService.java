package com.belo4ya.models.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.belo4ya.exceptions.ApiException;
import com.belo4ya.exceptions.CityNotExistException;
import com.belo4ya.models.HttpRequester;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public abstract class AbstractWeatherService {
    protected String apiKey;
    protected String apiUrl;
    protected final HttpRequester requester = new HttpRequester();
    protected Date lastRequest;
    protected String lastCity;
    protected String response;
    protected JsonNode mainNode;
    protected JsonNode currentNode;
    protected JsonNode hourlyNode;
    protected JsonNode dailyNode;

    public abstract WeatherObject getCurrent(String city) throws ApiException, CityNotExistException, IOException;

    public abstract ArrayList<WeatherObject> getHourly(String city) throws ApiException, CityNotExistException, IOException;

    public abstract ArrayList<WeatherObject> getDaily(String city) throws ApiException, CityNotExistException, IOException;

    protected abstract HashMap<String, String> getParams(String city) throws CityNotExistException;

    protected abstract HashMap<String, String> getHeaders() throws UnsupportedEncodingException;

    protected abstract void setCurrentNode() throws IOException;

    protected abstract void setHourlyNode();

    protected abstract void setDailyNode() throws IOException;

    protected void setMainNode() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mainNode = mapper.readTree(response);
    }

    protected void update(String city) throws ApiException, CityNotExistException, IOException {
        if (mainNode == null || lastRequest == null || lastCity == null ||
                !lastCity.equalsIgnoreCase(city) || new Date().getTime() - lastRequest.getTime() > 10000) {

            HashMap<String, String> params = getParams(city);
            HashMap<String, String> headers = getHeaders();

            try {
                if (headers == null) {
                    response = requester.get(apiUrl, params);
                } else {
                    response = requester.get(apiUrl, params, headers);
                }

                if (response == null) {
                    throw new ApiException();
                }
            } catch (IOException | InterruptedException e) {
                throw new ApiException();
            }

            setMainNode();
            setCurrentNode();
            setHourlyNode();
            setDailyNode();

            lastCity = city;
            lastRequest = new Date();
        }
    }

    public String getResponse() {
        return response;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
