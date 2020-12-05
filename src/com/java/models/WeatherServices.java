package com.java.models;

import java.util.HashMap;

public class WeatherServices {
    public static final HashMap<Integer, String> SERVICES = new HashMap<Integer, String>(){{
        put(null, "unspecified");
        put(0, "unspecified");
        put(1, "Open Weather");
        put(2, "Service 1");
        put(3, "Service 2");
    }};

    public static String getServiceName(Integer id) {
        return SERVICES.get(id);
    }
}
