package com.belo4ya.views;

public class StartView extends AbstractView{

    public static void displayTitle() {
        String title = "Weather Console Service";
        String outline = "*".repeat(displayWidth);
        String space = " ".repeat((displayWidth - title.length()) / 2 - 1);
        String message = outline + "\n" + "*" + space + title + space + "*" + "\n" + outline;

        System.out.println(message);
    }
}
