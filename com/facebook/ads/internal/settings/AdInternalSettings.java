package com.facebook.ads.internal.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class AdInternalSettings {
    static volatile boolean f5177a = false;
    private static final String f5178b = AdInternalSettings.class.getSimpleName();
    private static final Collection<String> f5179c = new HashSet();
    private static final Collection<String> f5180d = new HashSet();
    private static boolean f5181e;
    private static boolean f5182f;
    private static String f5183g;
    private static String f5184h;
    private static String f5185i;
    private static boolean f5186j = false;
    private static boolean f5187k;
    private static boolean f5188l;

    static {
        f5180d.add("sdk");
        f5180d.add("google_sdk");
        f5180d.add("vbox86p");
        f5180d.add("vbox86tp");
    }

    private static void m6932a(String str) {
        if (!f5177a) {
            f5177a = true;
            Log.d(f5178b, "Test mode device hash: " + str);
            Log.d(f5178b, "When testing your app with Facebook's ad units you must specify the device hashed ID to ensure the delivery of test ads, add the following code before loading an ad: AdSettings.addTestDevice(\"" + str + "\");");
        }
    }

    public static void addTestDevice(String str) {
        f5179c.add(str);
    }

    public static void addTestDevices(Collection<String> collection) {
        f5179c.addAll(collection);
    }

    public static void clearTestDevices() {
        f5179c.clear();
    }

    public static String getMediationService() {
        return f5184h;
    }

    public static String getUrlPrefix() {
        return f5183g;
    }

    public static boolean isDebugBuild() {
        return f5186j;
    }

    public static boolean isExplicitTestMode() {
        return f5181e;
    }

    public static boolean isTestMode(Context context) {
        if (f5186j || isExplicitTestMode() || f5180d.contains(Build.PRODUCT)) {
            return true;
        }
        if (f5185i == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("FBAdPrefs", 0);
            f5185i = sharedPreferences.getString("deviceIdHash", null);
            if (TextUtils.isEmpty(f5185i)) {
                f5185i = UUID.randomUUID().toString();
                sharedPreferences.edit().putString("deviceIdHash", f5185i).apply();
            }
        }
        if (f5179c.contains(f5185i)) {
            return true;
        }
        m6932a(f5185i);
        return false;
    }

    public static boolean isVideoAutoplay() {
        return f5187k;
    }

    public static boolean isVideoAutoplayOnMobile() {
        return f5188l;
    }

    public static boolean isVisibleAnimation() {
        return f5182f;
    }

    public static void setDebugBuild(boolean z) {
        f5186j = z;
    }

    public static void setMediationService(String str) {
        f5184h = str;
    }

    public static void setTestMode(boolean z) {
        f5181e = z;
    }

    public static void setUrlPrefix(String str) {
        f5183g = str;
    }

    public static void setVideoAutoplay(boolean z) {
        f5187k = z;
    }

    public static void setVideoAutoplayOnMobile(boolean z) {
        f5188l = z;
    }

    public static void setVisibleAnimation(boolean z) {
        f5182f = z;
    }
}
