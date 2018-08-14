package com.appnext.core;

public class AppnextError {
    public static final String CONNECTION_ERROR = "Connection Error";
    public static final String INTERNAL_ERROR = "Internal error";
    public static final String NO_ADS = "No Ads";
    public static final String NO_MARKET = "No market installed on the device";
    public static final String NULL_CONTEXT = "Null context";
    public static final String SLOW_CONNECTION = "Too Slow Connection";
    public static final String TIMEOUT = "Timeout";
    private String lD = "";

    public AppnextError(String str) {
        this.lD = str;
    }

    public String getErrorMessage() {
        return this.lD;
    }
}
