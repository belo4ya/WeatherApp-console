package com.java.controllers;

import com.java.views.AuthorizationView;
import com.java.views.InvalidInputView;
import com.java.views.Menu;

import java.util.Scanner;

public class InputController {
    public static Scanner in = new Scanner(System.in);

    public static String usernameInput() {
        AuthorizationView.displayUsernameInput();
        String username = in.nextLine();
        if (!validate(username)) {
            InvalidInputView.invalidUsername(true);
            return usernameInput();
        }
        return username;
    }

    public static String passwordInput() {
        AuthorizationView.displayPasswordInput();
        String password = in.nextLine();
        if (!validate(password)) {
            InvalidInputView.invalidPassword("Password не должен содержать спецсимволы.", true);
            return passwordInput();
        }
        return password;
    }

    public static int menuItemInput(Menu menu) {
        int n;
        menu.displayInputPrompt();

        try {
            n = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            InvalidInputView.invalidMenuItem(true);
            return menuItemInput(menu);
        }
        if (menu.get(n) == null) {
            InvalidInputView.invalidMenuItem(true);
            return menuItemInput(menu);
        }

        return n;
    }

    private static boolean validate(String str) {
        String validCharacters = "_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (char ch: str.toCharArray()) {
            if (validCharacters.indexOf(ch) == -1) {
                return false;
            }
        }
        return true;
    }
}
