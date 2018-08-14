package com.fyber.utils;

import java.net.URI;

public class StringUtils {
    public static final String EMPTY_STRING = "";

    public static boolean nullOrEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    public static boolean notNullNorEmpty(String str) {
        return !nullOrEmpty(str);
    }

    public static String trim(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    public static String nullToEmpty(String str) {
        return str != null ? str : "";
    }

    public static boolean equals(String str, String str2) {
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public static boolean isURIValid(String str) {
        boolean z = false;
        if (nullOrEmpty(str)) {
            return z;
        }
        try {
            return notNullNorEmpty(URI.create(str).getScheme());
        } catch (IllegalArgumentException e) {
            return z;
        }
    }
}
