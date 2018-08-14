package com.appsgeyser.sdk.utils;

public class BannerUtils {
    public static boolean isDataTextHtmlUrl(String url) {
        return url.startsWith("data:text/html");
    }
}
