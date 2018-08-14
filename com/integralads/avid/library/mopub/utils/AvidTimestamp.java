package com.integralads.avid.library.mopub.utils;

public class AvidTimestamp {
    private static double NANOSEC_PER_MILLISEC = 1000000.0d;

    public static double getCurrentTime() {
        return ((double) System.nanoTime()) / NANOSEC_PER_MILLISEC;
    }
}
