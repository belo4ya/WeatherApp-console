package com.java.views;

import java.util.Scanner;

public class AuthorizationView {
    private static String username;
    private static String password;

    public static void display() {

        System.out.println("---------Авторизация/Регистрация---------");
        System.out.println();
    }

    public static void input() {
        Scanner in = new Scanner(System.in);
        System.out.print("Username: ");
        username = in.nextLine();
        System.out.print("Password: ");
        password = in.nextLine();

        System.out.println();
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
