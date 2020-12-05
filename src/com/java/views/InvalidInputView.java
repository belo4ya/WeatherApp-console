package com.java.views;

public class InvalidInputView {
    private static final String INVALID = "You entered an invalid %s.";
    private static final String REPEAT = "Try again.";

    public static void invalidUsername(boolean repeat) {
        System.out.println(String.format(INVALID, "username") + " " +
                "Username must not contain special characters.");
        if (repeat) {
            System.out.println(REPEAT);
        }
        ScreenSpacer.smallIndent();
    }

    public static void invalidPassword(String message, boolean repeat) {
        System.out.println(String.format(INVALID, "password") + " " + message);
        if (repeat) {
            System.out.println(REPEAT);
        }
        ScreenSpacer.smallIndent();
    }

    public static void invalidMenuItem(boolean repeat) {
        System.out.println("You have selected an incorrect menu item.");
        if (repeat) {
            System.out.println(REPEAT);
        }
        ScreenSpacer.smallIndent();
    }

    public static void invalidCityName(boolean repeat) {
        System.out.println("I couldn't find such a city.");
        if (repeat) {
            System.out.println(REPEAT);
        }
        ScreenSpacer.smallIndent();
    }

}
