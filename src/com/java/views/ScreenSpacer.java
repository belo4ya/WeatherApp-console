package com.java.views;

public class ScreenSpacer {

    public static void smallIndent() {
        System.out.print("\n".repeat(1));
    }

    public static void normalIndent() {
        System.out.print("\n".repeat(3));
    }

    public static void largeIndent() {
        System.out.print("\n".repeat(7));
    }

    public static void safelyClean() {
        System.out.print("\n".repeat(15));
    }

}
