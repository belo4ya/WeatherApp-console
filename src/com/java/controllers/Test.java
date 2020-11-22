package com.java.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test {

        public static void main(String[] args) throws IOException, InterruptedException {
        String oneCall = "https://api.openweathermap.org/data/2.5/onecall";
        String apiKey = "8510c1d59e941a5cb1fa98b39e9121cc";

        Map<String, String> params = new HashMap<>() {{
            put("lat", "55.75222");
            put("lon", "37.615555");
            put("units", "metric");
            put("appid", apiKey);
        }};

        ObjectMapper mapper = new ObjectMapper();
        HttpRequester requester = new HttpRequester();
        String answer = requester.get(oneCall, params);
        System.out.println(answer);

        JsonNode node = mapper.readValue(answer, JsonNode.class);
        String lon = node.get("coord").get("lon").asText();
    }
}
