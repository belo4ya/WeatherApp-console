package com.java.views;

import com.java.models.weather.WeatherObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WeatherView {

    public static void badApiResponse() {
        System.out.println("There are problems with the service that provides the api.");
        System.out.println("Try repeating the request later");
    }

    public static void current(WeatherObject weatherObject) {
        int colOne = 12;
        int colTwo = 10;
        int colThree = 13;
        int colUnionTwoThree = colTwo + colThree + 3;
        String placeholder = " ";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date dateTime = new Date(weatherObject.getDateTime());
        String now = String.format("Now %s", format.format(dateTime));
        String description = String.format("%." + colUnionTwoThree + "s", weatherObject.getDescription());
        String temp = String.format("%.0f°С", weatherObject.getTemp());
        String feelsLike = String.format("feels like %.0f°С", weatherObject.getFeelsLike());
        String windTitle = "wind";
        String humidityTitle = "humidity";
        String pressureTitle = "pressure";
        String wind = String.format("%.1f m/s, %s", weatherObject.getWindSpeed(), weatherObject.getWindDir());
        String humidity = String.format("%d%%", weatherObject.getHumidity());
        String pressure = String.format("%.0f mmHg", weatherObject.getPressure());

        String card = "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colUnionTwoThree) + "-+" + "\n" +
                "| " + centerAlignment(now, colOne, placeholder) + " | " + " ".repeat(colUnionTwoThree) + " |" + "\n" +
                "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colUnionTwoThree) + "-+" + "\n" +
                "| " + " ".repeat(colOne) + " | " + centerAlignment(description, colUnionTwoThree, placeholder) + " |" + "\n" +
                "| " + centerAlignment(temp, colOne, placeholder) + " +-" + "-".repeat(colUnionTwoThree) + "-+\n" +
                "| " + " ".repeat(colOne) + " | " + centerAlignment(feelsLike, colUnionTwoThree, placeholder) + " |" + "\n" +
                "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colTwo) + "-+-" + "-".repeat(colThree) + "-+" + "\n" +
                "| " + centerAlignment(windTitle, colOne, placeholder) + " | " + centerAlignment(humidityTitle, colTwo, placeholder) + " | " + centerAlignment(pressureTitle, colThree, placeholder) + " |" + "\n" +
                "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colTwo) + "-+-" + "-".repeat(colThree) + "-+" + "\n" +
                "| " + centerAlignment(wind, colOne, placeholder) + " | " + centerAlignment(humidity, colTwo, placeholder) + " | " + centerAlignment(pressure, colThree, placeholder) + " |" + "\n" +
                "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colTwo) + "-+-" + "-".repeat(colThree) + "-+" + "\n";

        System.out.println(card);
    }

    public static void hourlyForecast(ArrayList<WeatherObject> weatherObjects) {
        ArrayList<String> dateRows = new ArrayList<>();
        ArrayList<String> descriptionRows = new ArrayList<>();
        ArrayList<String> tempRows = new ArrayList<>();
        ArrayList<String> separatorRows = new ArrayList<>();
        for (WeatherObject weatherObject : weatherObjects) {
            int colSize = 22;
            String separator = "+-" + "-".repeat(colSize) + "-+";
            String placeholder = " ";

            SimpleDateFormat format = new SimpleDateFormat("dd MMM HH:mm", Locale.ENGLISH);
            Date dateTime = new Date(weatherObject.getDateTime());
            String description = String.format("%." + colSize + "s", weatherObject.getDescription());
            String temp = String.format("%.0f°С", weatherObject.getTemp());
            
            if (dateTime.getTime() - (new Date().getTime() - 3600000) >= 0) {
                separatorRows.add(separator);
                dateRows.add("| " + centerAlignment(format.format(dateTime), colSize, placeholder) + " |");
                descriptionRows.add("| " + centerAlignment(description, colSize, placeholder) + " |");
                tempRows.add("| " + centerAlignment(temp, colSize, placeholder) + " |");
            }
        }

        printRow(dateRows, separatorRows);
        printRow(descriptionRows, separatorRows);
        printRow(tempRows, separatorRows);

        for (String el: separatorRows) {
            System.out.print(el + "  ");
        }
        System.out.println("\n");
    }

    private static void printRow(ArrayList<String> row, ArrayList<String> separator) {
        for (String el: separator) {
            System.out.print(el + "  ");
        }
        System.out.print("\n");
        for (String el: row) {
            System.out.print(el + "  ");
        }
        System.out.print("\n");
    }

    public static void dailyForecast(ArrayList<WeatherObject> weatherObjects) {
        for (WeatherObject weatherObject : weatherObjects) {
            int colOne = 13;
            int colTwo = 11;
            int colThree = 11;
            int colUnionOneTwoThree = colOne + colTwo + 3 + colThree + 3;
            int colUnionTwoThree = colTwo + colThree + 3;
            String placeholder = " ";

            SimpleDateFormat format = new SimpleDateFormat("E, dd MMM", Locale.ENGLISH);
            Date dateTime = new Date(weatherObject.getDateTime());
            String description = String.format("%." + colUnionOneTwoThree + "s", weatherObject.getDescription());

            String mornTitle = "morning";
            String mornTemp = String.format("%.0f°С", weatherObject.getMornTemp());
            String mornFeelsLike = String.format("%.0f°С", weatherObject.getMornFeelsLike());

            String dayTitle = "day";
            String dayTemp = String.format("%.0f°С", weatherObject.getDayTemp());
            String dayFeelsLike = String.format("%.0f°С", weatherObject.getDayFeelsLike());

            String eveTitle = "evening";
            String eveTemp = String.format("%.0f°С", weatherObject.getEveTemp());
            String eveFeelsLike = String.format("%.0f°С", weatherObject.getEveFeelsLike());

            String nightTitle = "night";
            String nightTemp = String.format("%.0f°С", weatherObject.getNightTemp());
            String nightFeelsLike = String.format("%.0f°С", weatherObject.getNightFeelsLike());

            String windTitle = "wind";
            String wind = String.format("%.1f m/s, %s", weatherObject.getWindSpeed(), weatherObject.getWindDir());

            String humidityTitle = "humidity";
            String humidity = String.format("%d%%", weatherObject.getHumidity());

            String pressureTitle = "pressure";
            String pressure = String.format("%.0f mmHg", weatherObject.getPressure());

            String card = "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colUnionTwoThree) + "-+" + "\n" +
                    "| " + leftAlignment(format.format(dateTime), colOne, placeholder) + " | " + placeholder.repeat(colUnionTwoThree) + " |" + "\n" +
                    "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colUnionTwoThree) + "-+" + "\n" +
                    "| " + centerAlignment(description, colUnionOneTwoThree, placeholder) + " |" + "\n" +
                    "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colTwo) + "-+-" + "-".repeat(colThree) + "-+" + "\n" +
                    "| " + leftAlignment(mornTitle, colOne, placeholder) + " | " + centerAlignment(mornTemp, colTwo, placeholder) + " | " + centerAlignment(mornFeelsLike, colThree, placeholder) + " |" + "\n" +
                    "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colTwo) + "-+-" + "-".repeat(colThree) + "-+" + "\n" +
                    "| " + leftAlignment(dayTitle, colOne, placeholder) + " | " + centerAlignment(dayTemp, colTwo, placeholder) + " | " + centerAlignment(dayFeelsLike, colThree, placeholder) + " |" + "\n" +
                    "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colTwo) + "-+-" + "-".repeat(colThree) + "-+" + "\n" +
                    "| " + leftAlignment(eveTitle, colOne, placeholder) + " | " + centerAlignment(eveTemp, colTwo, placeholder) + " | " + centerAlignment(eveFeelsLike, colThree, placeholder) + " |" + "\n" +
                    "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colTwo) + "-+-" + "-".repeat(colThree) + "-+" + "\n" +
                    "| " + leftAlignment(nightTitle, colOne, placeholder) + " | " + centerAlignment(nightTemp, colTwo, placeholder) + " | " + centerAlignment(nightFeelsLike, colThree, placeholder) + " |" + "\n" +
                    "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colTwo) + "-+-" + "-".repeat(colThree) + "-+" + "\n" +
                    "| " + leftAlignment(windTitle, colOne, placeholder) + " | " + centerAlignment(wind, colUnionTwoThree, placeholder) + " |" + "\n" +
                    "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colUnionTwoThree) + "-+" + "\n" +
                    "| " + leftAlignment(humidityTitle, colOne, placeholder) + " | " + centerAlignment(humidity, colUnionTwoThree, placeholder) + " |" + "\n" +
                    "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colUnionTwoThree) + "-+" + "\n" +
                    "| " + leftAlignment(pressureTitle, colOne, placeholder) + " | " + centerAlignment(pressure, colUnionTwoThree, placeholder) + " |" + "\n" +
                    "+-" + "-".repeat(colOne) + "-+-" + "-".repeat(colUnionTwoThree) + "-+" + "\n";

            System.out.println(card);
        }
    }

    public static String leftAlignment(String content, int colSize, String placeholder) {
        return content + placeholder.repeat(colSize - content.length());
    }

    public static String centerAlignment(String content, int colSize, String placeholder) {
        return placeholder.repeat((colSize - content.length()) / 2) + content +
                placeholder.repeat(colSize - ((colSize - content.length()) / 2) - content.length());
    }

    public static String rightAlignment(String content, int colSize, String placeholder) {
        return placeholder.repeat(colSize - content.length()) + content;
    }

}
