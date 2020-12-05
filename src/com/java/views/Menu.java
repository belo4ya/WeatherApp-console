package com.java.views;

import java.util.*;

public class Menu {
    public static final HashMap<Commands, String> caption = new HashMap<Commands, String>(){{
        put(Commands.logOut, "Log out");
        put(Commands.setCity, "Set city");
        put(Commands.setService, "Set weather service");
        put(Commands.updateCity, "Change city");
        put(Commands.updateService, "Change weather service");
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
        setService,
        updateCity,
        updateService
    }
}
