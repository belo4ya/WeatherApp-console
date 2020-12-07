package com.java.controllers;

import com.java.exceptions.ApiException;
import com.java.exceptions.CityNotExistException;
import com.java.exceptions.ServiceNotExistException;
import com.java.models.*;
import com.java.models.weather.OpenWeatherMap;
import com.java.models.weather.WeatherObject;
import com.java.models.weather.YandexWeather;
import com.java.views.AccountView;
import com.java.views.Menu;
import com.java.views.ScreenSpacer;
import com.java.views.WeatherView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountController {
    private User user;
    private final Menu menu = new Menu();
    private final OpenWeatherMap openWeatherMap = OpenWeatherMap.getInstance();
    private final YandexWeather yandexWeather = YandexWeather.getInstance();
    private final HashMap<Menu.Commands, MenuItem> addresses = new HashMap<>() {{
        put(Menu.Commands.logOut, AccountController.this::logOut);
        put(Menu.Commands.setCity, AccountController.this::setCity);
        put(Menu.Commands.setService, AccountController.this::setService);
        put(Menu.Commands.updateCity, AccountController.this::updateCity);
        put(Menu.Commands.updateService, AccountController.this::updateService);

        put(Menu.Commands.openWeatherServiceCurrent, AccountController.this::displayCurrentWeather);
        put(Menu.Commands.openWeatherServiceHourly, AccountController.this::displayHourlyForecast);
        put(Menu.Commands.openWeatherServiceDaily, AccountController.this::displayDailyForecast);

        put(Menu.Commands.yandexWeatherCurrent, AccountController.this::displayCurrentWeather);
        put(Menu.Commands.yandexWeatherHourly, AccountController.this::displayHourlyForecast);
        put(Menu.Commands.yandexWeatherDaily, AccountController.this::displayDailyForecast);
    }};

    public AccountController(User user) {
        this.user = user;
    }

    public void displayHeader() {
        String city = user.getCity();
        Integer serviceId = user.getServiceId();
        String weatherService;
        try {
            weatherService = DataBase.getInstance().getService(serviceId);
        } catch (ServiceNotExistException e) {
            weatherService = "unspecified";
        }

        if (city == null) {
            city = "unspecified";
        }

        AccountView.displayHeader(user.getUsername(), city, weatherService);
        ScreenSpacer.smallIndent();
    }

    public void updateMenu() {
        menu.reset();

        if (user.getCity() == null) {
            menu.put(Menu.Commands.setCity);
        }

        if (user.getServiceId() == null || user.getServiceId() == 0) {
            menu.put(Menu.Commands.setService);
        }

        if (user.getCity() != null && user.getServiceId() != null && user.getServiceId() != 0) {
            Integer id = user.getServiceId();
            if (id == 1) {
                menu.put(Menu.Commands.openWeatherServiceCurrent);
                menu.put(Menu.Commands.openWeatherServiceHourly);
                menu.put(Menu.Commands.openWeatherServiceDaily);
            } else if (id == 2) {
                menu.put(Menu.Commands.yandexWeatherCurrent);
                menu.put(Menu.Commands.yandexWeatherHourly);
                menu.put(Menu.Commands.yandexWeatherDaily);
            }

            menu.put(Menu.Commands.updateCity);
            menu.put(Menu.Commands.updateService);
        }

        menu.put(Menu.Commands.logOut);
    }

    public void menuNavigate() {
        updateMenu();
        displayHeader();
        menu.display();
        ScreenSpacer.smallIndent();
        int item = InputController.menuItemInput(menu);
        addresses.get(menu.get(item)).select();
    }

    public void setCity() {
        ScreenSpacer.safelyClean();
        displayHeader();
        String city = InputController.cityInput();
        user.setCity(city);
        DataBase.getInstance().setCity(user);
        ScreenSpacer.safelyClean();
        menuNavigate();
    }

    public void setService() {
        ScreenSpacer.safelyClean();
        displayHeader();

        menu.reset();
        menu.put(Menu.Commands.openWeatherService);
        menu.put(Menu.Commands.yandexWeather);
        menu.display();

        ScreenSpacer.smallIndent();
        int item = InputController.menuItemInput(menu);
        user.setServiceId(item);
        DataBase.getInstance().setServiceId(user);
        ScreenSpacer.safelyClean();
        menuNavigate();
    }

    public void updateCity() {
        setCity();
    }

    public void updateService() {
        setService();
    }

    public void logOut() {
        menu.reset();
        ScreenSpacer.safelyClean();
        user = AuthorizationController.authorize();
        ScreenSpacer.safelyClean();
        menuNavigate();
    }

    public void displayCurrentWeather() {
        WeatherObject weatherObject = null;
        try {
            Integer id = user.getServiceId();
            if (id == 1) {
                weatherObject = openWeatherMap.getCurrent(user.getCity());
            } else if (id == 2) {
                weatherObject = yandexWeather.getCurrent(user.getCity());
            }
        } catch (ApiException | CityNotExistException | IOException e) {
            WeatherView.badApiResponse();
            return;
        }

        if (weatherObject == null) {  // TODO
            System.out.println("Ошибка");
            return;
        }

        ScreenSpacer.safelyClean();
        displayHeader();
        WeatherView.current(weatherObject);
        menu.backToMainView();
        InputController.in.nextLine();
        ScreenSpacer.safelyClean();
        menuNavigate();
    }

    public void displayHourlyForecast() {
        ArrayList<WeatherObject> weatherObjects = null;
        try {
            Integer id = user.getServiceId();
            if (id == 1) {
                weatherObjects = openWeatherMap.getHourly(user.getCity());
            } else if (id == 2) {
                weatherObjects = yandexWeather.getHourly(user.getCity());
            }
        } catch (ApiException | CityNotExistException | IOException e) {
            WeatherView.badApiResponse();
            return;
        }

        if (weatherObjects == null) {  // TODO
            System.out.println("Ошибка");
            return;
        }

        ScreenSpacer.safelyClean();
        displayHeader();
        WeatherView.hourlyForecast(weatherObjects);
        menu.backToMainView();
        InputController.in.nextLine();
        ScreenSpacer.safelyClean();
        menuNavigate();
    }

    public void displayDailyForecast() {
        ArrayList<WeatherObject> weatherObjects = null;
        try {
            Integer id = user.getServiceId();
            if (id == 1) {
                weatherObjects = openWeatherMap.getDaily(user.getCity());
            } else if (id == 2) {
                weatherObjects = yandexWeather.getDaily(user.getCity());
            }
        } catch (ApiException | CityNotExistException | IOException e) {
            WeatherView.badApiResponse();
            return;
        }

        if (weatherObjects == null) {  // TODO
            System.out.println("Ошибка");
            return;
        }

        ScreenSpacer.safelyClean();
        displayHeader();
        WeatherView.dailyForecast(weatherObjects);
        menu.backToMainView();
        InputController.in.nextLine();
        ScreenSpacer.safelyClean();
        menuNavigate();
    }

}

@FunctionalInterface
interface MenuItem {
    void select();
}
