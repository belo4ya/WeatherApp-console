package com.java.models;

public class UniversalConverter {

    public static long secToMilli(long sec) {
        return sec * 1000;
    }

    public static double pHaToMmHg(int pHa) {
        return pHa * 0.750064;
    }

}
