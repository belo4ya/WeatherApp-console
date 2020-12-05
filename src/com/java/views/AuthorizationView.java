package com.java.views;

public class AuthorizationView {

    public static void displayTitle() {
        int len = 43;
        String title = "Log in";
        String outline = "*".repeat(len);
        String space = " ".repeat((len - title.length()) / 2 - 1);
        String message = outline + "\n" + "*" + space + title + space + "*" + "\n" + outline;
        System.out.println(message);
    }

    public static void displayUsernameInput() {
        System.out.print("username: ");
    }

    public static void displayPasswordInput() {
        System.out.print("password: ");
    }
}
