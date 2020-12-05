package com.java.views;

import java.util.*;

public class Menu {
    public static final HashMap<Commands, String> caption = new HashMap<Commands, String>(){{
        put(Commands.logOut, "Log out");
        put(Commands.setCity, "Set city");
        put(Commands.updateCity, "Change city");
        put(Commands.setService, "Set weather service");
        put(Commands.updateService, "Change weather service");

        put(Commands.service1Name, "Open Weather");
        put(Commands.service2Name, "Яндекс.Погода");
        put(Commands.service3Name, "Ещё погода");

        put(Commands.service1Current, "Current weather");
        put(Commands.service1Minute, "Minute forecast for 1 hour");
        put(Commands.service1Hourly, "Hourly forecast for 48 hours");
        put(Commands.service1Daily, "Daily forecast for 7 days");

        put(Commands.service2Current, "Current weather");
        put(Commands.service2Hourly, "Hourly forecast for 24 hours");
        put(Commands.service2Part, "Forecasts by part of day");
        put(Commands.service2Daily, "Daily forecast for 7 days");
    }};
    private final HashMap<Integer, Commands> menu = new HashMap<Integer, Commands>();
    private int key = 1;

    public void put(Commands value) {
        menu.put(key++, value);
    }

    public Commands get(Integer key) {
        return menu.get(key);
    }

    public void remove(Integer key) {
        menu.remove(key);
        update();
    }

    public void reset() {
        menu.clear();
        key = 1;
    }

    public void update() {
        int key = 1;
        for (Map.Entry<Integer, Commands> entry: menu.entrySet()) {
            menu.put(key++, entry.getValue());
        }
    }

    public void display() {
        StringBuilder menuString = new StringBuilder();
        for (Map.Entry<Integer, Commands> entry: menu.entrySet()) {
            menuString.append(entry.getKey()).append(". - ").append(caption.get(entry.getValue())).append("\n");
        }
        System.out.println(menuString);
    }

    public void displayInputPrompt() {
        System.out.print(">>> ");
    }

    public enum Commands {
        logOut,
        setCity,
        updateCity,
        setService,
        updateService,

        service1Name,
        service2Name,
        service3Name,

        service1Current,
        service1Minute,  // 1 hour
        service1Hourly,  // 48 hours
        service1Daily,  // 7 days

        service2Current,
        service2Hourly,  // 24 hours
        service2Part,  // night, day, evening ...
        service2Daily,  // 7 days
    }
}
