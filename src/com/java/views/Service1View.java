package com.java.views;

import com.java.models.Weather;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Service1View {

    public static void badApiResponse() {
        System.out.println("There are problems with the service that provides the api.");
        System.out.println("Try repeating the request later");
    }

    public static void currentWeather(Weather weather) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date dateTime = new Date(weather.getDateTime());

        String now = String.format("Now %s", format.format(dateTime));
        String description = weather.getDescription();
        String temp = String.format("%.0f°С", weather.getTemp());
        String feelsLike = String.format("feels like %.0f°С", weather.getFeelsLike());
        String windTitle = "wind";
        String humidityTitle = "humidity";
        String pressureTitle = "pressure";
        String wind = String.format("%.1f m/s, %s", weather.getWindSpeed(), weather.getWindDeg());
        String humidity = String.format("%d%%", weather.getHumidity());
        String pressure = String.format("%.0f mmHg", weather.getPressure());

        int rowOne = 12;
        int rowTwo = 10;
        int rowThree = 13;
        int customRow = rowTwo + rowThree + 3;

        String card = "+-" + "-".repeat(rowOne) + "-+-" + "-".repeat(customRow) + "-+" + "\n" +
                "| " + " ".repeat((rowOne - now.length()) / 2) + now + " ".repeat(rowOne - ((rowOne - now.length()) / 2) - now.length()) + " | " + " ".repeat(customRow) + " |" + "\n" +
                "+-" + "-".repeat(rowOne) + "-+-" + "-".repeat(customRow) + "-+" + "\n" +
                "| " + " ".repeat(rowOne) + " | " + " ".repeat((customRow - description.length()) / 2) + description + " ".repeat(customRow - ((customRow - description.length()) / 2) - description.length()) + " |" + "\n" +
                "| " + " ".repeat((rowOne - temp.length()) / 2) + temp + " ".repeat(rowOne - ((rowOne - temp.length()) / 2) - temp.length()) + " +-" + "-".repeat(customRow) + "-+\n" +
                "| " + " ".repeat(rowOne) + " | " + " ".repeat((customRow - feelsLike.length()) / 2) + feelsLike + " ".repeat(customRow - ((customRow - feelsLike.length()) / 2) - feelsLike.length()) + " |" + "\n" +
                "+-" + "-".repeat(rowOne) + "-+-" + "-".repeat(rowTwo) + "-+-" + "-".repeat(rowThree) + "-+" + "\n" +
                "| " + " ".repeat((rowOne - windTitle.length()) / 2) + windTitle + " ".repeat(rowOne - ((rowOne - windTitle.length()) / 2) - windTitle.length()) + " | " + " ".repeat((rowTwo - humidityTitle.length()) / 2) + humidityTitle + " ".repeat(rowTwo - ((rowTwo - humidityTitle.length()) / 2) - humidityTitle.length()) + " | " + " ".repeat((rowThree - pressureTitle.length()) / 2) + pressureTitle + " ".repeat(rowThree - ((rowThree - pressureTitle.length()) / 2) - pressureTitle.length()) + " |" + "\n" +
                "+-" + "-".repeat(rowOne) + "-+-" + "-".repeat(rowTwo) + "-+-" + "-".repeat(rowThree) + "-+" + "\n" +
                "| " + " ".repeat((rowOne - wind.length()) / 2) + wind + " ".repeat(rowOne - ((rowOne - wind.length()) / 2) - wind.length()) + " | " + " ".repeat((rowTwo - humidity.length()) / 2) + humidity + " ".repeat(rowTwo - ((rowTwo - humidity.length()) / 2) - humidity.length()) + " | " + " ".repeat((rowThree - pressure.length()) / 2) + pressure + " ".repeat(rowThree - ((rowThree - pressure.length()) / 2) - pressure.length()) + " |" + "\n" +
                "+-" + "-".repeat(rowOne) + "-+-" + "-".repeat(rowTwo) + "-+-" + "-".repeat(rowThree) + "-+" + "\n";

        System.out.println(card);
    }

    public static void hourlyWeather(ArrayList<Weather> weatherList) {

    }

    public static void dailyWeather(ArrayList<Weather> weatherList) {

    }

}
