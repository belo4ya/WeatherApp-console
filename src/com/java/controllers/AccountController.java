package com.java.controllers;

import com.java.models.User;
import com.java.models.WeatherServices;
import com.java.views.AccountView;
import com.java.views.Menu;
import com.java.views.ScreenSpacer;

import java.util.HashMap;

public class AccountController {
    private final User user;
    private final Menu menu = new Menu();
    private final HashMap<Menu.Commands, MenuItem> addresses = new HashMap<Menu.Commands, MenuItem>(){{
        put(Menu.Commands.logOut, AccountController.this::menuNavigate);
        put(Menu.Commands.setCity, AccountController.this::menuNavigate);
        put(Menu.Commands.setService, AccountController.this::menuNavigate);
        put(Menu.Commands.updateCity, AccountController.this::menuNavigate);
        put(Menu.Commands.updateService, AccountController.this::menuNavigate);
    }};

    public AccountController(User user) {
        this.user = user;
    }

    public void logIn() {
        String city = user.getCity();
        Integer serviceId = user.getServiceId();
        String weatherService = WeatherServices.getServiceName(serviceId);

        if (city == null) {
            city = "unspecified";
        }

        AccountView.displayHeader(user.getUsername(), city, weatherService);

        if (user.getCity() == null) {
            menu.put(Menu.Commands.setCity);
        }

        if (user.getServiceId() == null || user.getServiceId() == 0) {
            menu.put(Menu.Commands.setService);
        }
        menu.put(Menu.Commands.logOut);

        ScreenSpacer.smallIndent();
        menu.display();

        menuNavigate();
    }

    public void menuNavigate() {
        ScreenSpacer.smallIndent();
        int item = InputController.menuItemInput(menu);
        addresses.get(menu.get(item)).select();
    }

}

@FunctionalInterface
interface MenuItem {
    void select();
}
