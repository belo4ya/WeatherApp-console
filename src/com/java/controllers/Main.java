package com.java.controllers;

import com.java.exceptions.WrongPasswordException;
import com.java.models.DataBase;
import com.java.models.User;
import com.java.views.*;

public class Main {

    public static void main(String[] args) {
        DataBase db = DataBase.getInstance();

        // начало
        StartView.displayTitle();
        ScreenSpacer.smallIndent();

        // авторизация
        User user = AuthorizationController.authorize(db);

        System.out.println(user);


    }
}
