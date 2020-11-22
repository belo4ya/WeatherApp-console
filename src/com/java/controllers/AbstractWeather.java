package com.java.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class AbstractWeather {
    private HttpClient client = null;

    AbstractWeather() {
        client = HttpClient.newHttpClient();
    }

    abstract public String getCurrentData() throws IOException, InterruptedException;

    abstract public String getHourlyForecast() throws IOException, InterruptedException;

    abstract public String getDailyForecast() throws IOException, InterruptedException;

    abstract public String parseData();

    public String request(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET().build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

//        if (response.statusCode() != 200) {
//            throw new IOException();
//        }
        return response.body();
    }

}
