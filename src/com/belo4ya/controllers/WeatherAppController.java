package com.belo4ya.controllers;

import com.belo4ya.models.User;
import com.belo4ya.views.*;

public class WeatherAppController {

    public static void run() {
        try {
            // начало
            StartView.displayTitle();
            ScreenSpacer.smallIndent();

            // авторизация
            User user = AuthorizationController.authorize();

            // личный кабинет
            ScreenSpacer.safelyClean();
            AccountController account = new AccountController(user);
            account.menuNavigate();
        } catch (Exception e) {
            System.out.println("Приложение не отвечает.");
            System.out.println("Да, такое даже с word'ом бывает");
            System.exit(1);
        }
    }
}
