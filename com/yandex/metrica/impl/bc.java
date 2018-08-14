package com.yandex.metrica.impl;

import android.os.Build;
import android.os.Build.VERSION;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class bc {

    static class C4354a {
        static final String f11772a = new C4354a().m14871a();

        C4354a() {
        }

        String m14871a() {
            String str = "native";
            if (m14872a("com.unity3d.player.UnityPlayer")) {
                return "unity";
            }
            if (m14872a("mono.MonoPackageManager")) {
                return "xamarin";
            }
            if (m14872a("org.apache.cordova.CordovaPlugin")) {
                return "cordova";
            }
            if (m14872a("com.facebook.react.ReactRootView")) {
                return "react";
            }
            return str;
        }

        boolean m14872a(String str) {
            return bc.m14878c(str);
        }
    }

    public static String m14873a() {
        String str = "2.73";
        if (str.length() - str.indexOf(46) < 3) {
            return str + SchemaSymbols.ATTVAL_FALSE_0;
        }
        return str;
    }

    public static boolean m14875b() {
        return m14878c("com.yandex.metrica.YandexMetricaInternal");
    }

    public static String m14877c() {
        return C4354a.f11772a;
    }

    public static String m14874a(String str) {
        String b;
        StringBuilder append = new StringBuilder().append(str).append(BridgeUtil.SPLIT_MARK).append(m14873a()).append(".7854 (");
        if (Build.MODEL.startsWith(Build.MANUFACTURER)) {
            b = bi.m14960b(Build.MODEL);
        } else {
            b = bi.m14960b(Build.MANUFACTURER) + " " + Build.MODEL;
        }
        return append.append(b).append("; Android ").append(VERSION.RELEASE).append(")").toString();
    }

    private static boolean m14878c(String str) {
        try {
            if (Class.forName(str) != null) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
