package com.java.views;

public class StartView {

    public static void displayTitle() {
        int len = 43;
        String title = "Weather Console Service";
        String outline = "*".repeat(len);
        String space = " ".repeat((len - title.length()) / 2 - 1);
        String message = outline + "\n" + "*" + space + title + space + "*" + "\n" + outline;

        System.out.println(message);
    }
}
