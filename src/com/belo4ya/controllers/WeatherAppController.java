package com.belo4ya.controllers;

import com.belo4ya.models.User;
import com.belo4ya.views.*;

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
