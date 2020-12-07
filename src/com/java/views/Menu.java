package com.java.views;

import java.util.*;

public class Menu {
    public static final HashMap<Commands, String> caption = new HashMap<Commands, String>(){{
        put(Commands.logOut, "Log out");
        put(Commands.setCity, "Set city");
        put(Commands.updateCity, "Change city");
        put(Commands.setService, "Set weather service");
        put(Commands.updateService, "Change weather service");

        put(Commands.openWeatherService, "Open Weather");
        put(Commands.yandexWeather, "Яндекс.Погода");

        put(Commands.openWeatherServiceCurrent, "Current weather");
        put(Commands.openWeatherServiceHourly, "Hourly forecast for 48 hours");
        put(Commands.openWeatherServiceDaily, "Daily forecast for 7 days");

        put(Commands.yandexWeatherCurrent, "Current weather");
        put(Commands.yandexWeatherHourly, "Hourly forecast for 24 hours");
        put(Commands.yandexWeatherDaily, "Daily forecast for 7 days");
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

    public void backToMainView() {
        System.out.println("Press ENTER to return to the main menu.");
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

        openWeatherService,
        yandexWeather,

        openWeatherServiceCurrent,
        openWeatherServiceHourly,  // 48 hours
        openWeatherServiceDaily,  // 7 days

        yandexWeatherCurrent,
        yandexWeatherHourly,  // 24 hours
        yandexWeatherDaily,  // 7 days
    }
}
