package com.integralads.avid.library.adcolony.utils;

public class AvidTimestamp {
    private static double f8401a = 1000000.0d;

    public static double getCurrentTime() {
        return ((double) System.nanoTime()) / f8401a;
    }
}
