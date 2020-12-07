package com.belo4ya.controllers;

import com.belo4ya.exceptions.WrongPasswordException;
import com.belo4ya.models.DataBase;
import com.belo4ya.models.User;
import com.belo4ya.views.AuthorizationView;
import com.belo4ya.views.InvalidInputView;
import com.belo4ya.views.ScreenSpacer;

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
