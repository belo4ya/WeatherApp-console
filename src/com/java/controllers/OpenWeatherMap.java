//package com.java.controllers;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//
//public class OpenWeatherMap {
//    private final String apiKey = "8510c1d59e941a5cb1fa98b39e9121cc";
//    private final String apiUrl = "http://api.openweathermap.org/data/2.5/";
//
//    public String getCurrentData() throws IOException, InterruptedException {
//        String url = buildUrl("weather", "Moscow");
//
//        return request(url);
//    }
//
//    public String getHourlyForecast() throws IOException, InterruptedException {
//        String url = buildUrl("forecast/hourly", "Moscow");
//        System.out.println(url);
//        return request(url);
//    }
//
//    public String getDailyForecast() throws IOException, InterruptedException {
//        String url = buildUrl("forecast/daily", "Moscow");
//
//        return request(url);
//    }
//
//    public String parseData() {
//        return null;
//    }
//
//    public String buildUrl(String type, String city) {
//
//        return apiUrl + type + "?" +
//                "q=" + city + "&" +
//                "cnt=" + "7" + "&" +
//                "units=" + "metric" + "&" +
//                "appid=" + apiKey;
//    }
//}
