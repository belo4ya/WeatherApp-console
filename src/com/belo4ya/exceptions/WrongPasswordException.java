package com.belo4ya.exceptions;

public class WrongPasswordException extends Exception {

    public WrongPasswordException() {
        super("Неверный пароль");
    }
}
