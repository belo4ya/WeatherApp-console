package com.java.views;

public class ScreenCleaner {

    public static void main(String[] args) {
        ScreenCleaner.safelyClean();
    }

    public static void safelyClean() {
        System.out.print("\n".repeat(15));
    }

}
