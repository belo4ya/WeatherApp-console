package com.java.views;

public class AccountView {
    private static final int len = 43;

    public static void displayTitle() {
        String title = "My account";
        String outline = "*".repeat(len);
        String space = " ".repeat((len - title.length()) / 2 - 1);
        String message = outline + "\n" + "*" + space + title + space + "*" + "\n" + outline;

        System.out.println(message);
    }

    public static void displayUsername(String username) {
        System.out.printf("%" + len + "s%n", String.format("Hello, %s", username));
    }

    public static void displayCity(String city) {
        System.out.printf("%" + len + "s%n", String.format("Your city: %s", city));
    }

    public static void displayWeatherService(String weatherService) {
        System.out.printf("%" + len + "s%n", String.format("Weather: %s", weatherService));
    }

    public static void displayHeader(String username, String city, String weatherService) {
        displayTitle();
        displayUsername(username);
        displayCity(city);
        displayWeatherService(weatherService);
    }

}
