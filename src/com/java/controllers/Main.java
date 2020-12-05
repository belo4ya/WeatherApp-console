package com.java.controllers;

import com.java.models.User;
import com.java.views.*;

public class Main {

    public static void main(String[] args) {
        // начало
        StartView.displayTitle();
        ScreenSpacer.smallIndent();

        // авторизация
        User user = AuthorizationController.authorize();

        // личный кабинет
        ScreenSpacer.safelyClean();
        AccountController account = new AccountController(user);
        account.menuNavigate();
    }
}
