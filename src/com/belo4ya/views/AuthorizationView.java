package com.belo4ya.views;

public class AuthorizationView extends AbstractView {

    public static void displayTitle() {
        String title = "Log in";
        String outline = "*".repeat(displayWidth);
        String space = " ".repeat((displayWidth - title.length()) / 2 - 1);
        String message = outline + "\n" + "*" + space + title + space + " *" + "\n" + outline;
        System.out.println(message);
    }

    public static void displayUsernameInput() {
        System.out.print("username: ");
    }

    public static void displayPasswordInput() {
        System.out.print("password: ");
    }
}
