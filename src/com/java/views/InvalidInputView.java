package com.java.views;

public class InvalidInputView {
    private static final String INVALID = "Вы ввели неверный %s.";
    private static final String REPEAT = "Попробуйте ещё раз.";

    public static void invalidUsername(boolean repeat) {
        System.out.println(String.format(INVALID, "username") + " " +
                "Username не должен содержать спецсимволы.");
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

}
