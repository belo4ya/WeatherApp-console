package com.java.views;

public class AccountView extends AbstractView {

    public static void displayTitle() {
        String title = "My account";
        String outline = "*".repeat(displayWidth);
        String space = " ".repeat((displayWidth - title.length()) / 2 - 1);
        String message = outline + "\n" + "*" + space + title + space + "*" + "\n" + outline;

        System.out.println(message);
    }

    public static void displayUsername(String username) {
        System.out.printf("%" + displayWidth + "s%n", String.format("Hello, %s", username));
    }

    public static void displayCity(String city) {
        System.out.printf("%" + displayWidth + "s%n", String.format("Your city: %s", city));
    }

    public static void displayWeatherService(String weatherService) {
        System.out.printf("%" + displayWidth + "s%n", String.format("Weather: %s", weatherService));
    }

    public static void displayHeader(String username, String city, String weatherService) {
        displayTitle();
        displayUsername(username);
        displayCity(city);
        displayWeatherService(weatherService);
    }

    public static void displayCityInput() {
        System.out.print("Enter city name: ");
    }

    public static void displayInputPrompt() {
        System.out.print(">>> ");
    }

}
