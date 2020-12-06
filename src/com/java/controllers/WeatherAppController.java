package com.java.controllers;

import com.java.models.User;
import com.java.views.*;

public class WeatherAppController {

    public static void run() {
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
