package com.java.controllers;

import com.java.models.User;
import com.java.views.AccountView;

public class AccountController {
    private User user;

    public void logIn(User user) {
        this.user = user;
        AccountView.displayTitle();

    }

}
