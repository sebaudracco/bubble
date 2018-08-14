package com.yandex.metrica.impl;

import android.text.TextUtils;
import java.util.regex.Pattern;

public final class bi {
    static {
        Pattern.compile("[^0-9a-zA-Z,`’\\.\\+\\-'\\s\"]");
        Pattern.compile("\\s+");
    }

    public static boolean m14958a(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    public static boolean m14957a(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean m14959a(String... strArr) {
        if (strArr == null) {
            return false;
        }
        for (String a : strArr) {
            if (m14957a(a)) {
                return true;
            }
        }
        return false;
    }

    public static String m14961b(String str, String str2) {
        return str == null ? str2 : str;
    }

    public static String m14963c(String str, String str2) {
        return m14957a(str) ? str2 : str;
    }

    public static String m14960b(String str) {
        if (m14957a(str)) {
            return "";
        }
        char charAt = str.charAt(0);
        return !Character.isUpperCase(charAt) ? Character.toUpperCase(charAt) + str.substring(1) : str;
    }

    public static byte[] m14964c(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (Exception e) {
            return new byte[0];
        }
    }

    public static final String m14962b(String... strArr) {
        return TextUtils.join(",", strArr);
    }
}
