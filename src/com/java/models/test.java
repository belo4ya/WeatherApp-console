package com.java.models;

public class test {

    public static void main(String[] args) {
        String now = String.format("Now %s", "00:00");
        String description = "overcast clouds";
        String temp = String.format("%.0f°С", -10.1);
        String feelsLike = String.format("feels like %.0f°С", 9.2);
        String windTitle = "wind";
        String humidityTitle = "humidity";
        String pressureTitle = "pressure";
        String wind = String.format("%.1f m/s, %s", 2.3, "NW");
        String humidity = String.format("%d%%", 58);
        String pressure = String.format("%.0f mmHg", 780.2);
        int rowOne = 12;
        int rowTwo = 10;
        int rowThree = 13;
        int customRow = rowTwo + rowThree + 3;

        String print = "+-" + "-".repeat(rowOne) + "-+-" + "-".repeat(customRow) + "-+" + "\n" +
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
        System.out.println(print);
        System.out.println(print.length());
    }

}
