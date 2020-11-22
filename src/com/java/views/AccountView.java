package com.java.views;

public class AccountView {
    String username;

    public AccountView(String username) {
        this.username = username;
    }

    public void header() {
        System.out.print("+-----+");
        System.out.print("          ");
        System.out.print("+-----+");
        System.out.print("| WCS |");
        System.out.println("           Пользователь: " + username);
        System.out.println("+-----+");
    }
}
