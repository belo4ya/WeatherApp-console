package com.java.controllers;

import com.java.exceptions.WrongPasswordException;
import com.java.models.DataBase;
import com.java.models.User;
import com.java.views.*;


public class Main {

    public static void main(String[] args) {
        DataBase db = DataBase.getInstance();

        // начало
        StartFormView.display();

        // авторизация
        User user = null;
        boolean authorization = false;

        AuthorizationView.display();
        AuthorizationView.input();
        while (!authorization) {
            try {
                user = db.getUser(AuthorizationView.getUsername(), AuthorizationView.getPassword());
                authorization = true;
            } catch (WrongPasswordException e) {
                WrongPasswordView.display();
                AuthorizationView.input();
            }
        }

        ScreenCleaner.safelyClean();

        // Личный кабинет
        new AccountView(user.getUsername()).header();

    }
}
