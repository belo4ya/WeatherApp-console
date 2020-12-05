package com.java.controllers;

import com.java.exceptions.WrongPasswordException;
import com.java.models.DataBase;
import com.java.models.User;
import com.java.views.AuthorizationView;
import com.java.views.InvalidInputView;
import com.java.views.ScreenSpacer;

public class AuthorizationController {

    public static User authorize() {
        User user;
        AuthorizationView.displayTitle();
        ScreenSpacer.smallIndent();

        String username = InputController.usernameInput();
        String password = InputController.passwordInput();

        try {
            user = DataBase.getInstance().getUser(username, password);
        } catch (WrongPasswordException e) {
            ScreenSpacer.safelyClean();
            InvalidInputView.invalidPassword("", true);
            return authorize();
        }

        return user;
    }

}
