package com.appsgeyser.sdk.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public abstract class DeviceInfoGetter {
    private static final String AMPERSAND = "&";
    private static final String ANDROID_VERSION_KEY = "androidversion=";
    private static final String CONNECTION_TYPE_KEY = "connectiontype=";
    private static final String DEVICE_NAME_KEY = "devicename=";
    private static final String EMPTY_STRING = "";
    private static final String IS_TABLET_KEY = "istablet=";
    private static final String MANUFACTURER_KEY = "manufacturer=";
    private static final int MAX_SIZE = 9;
    private static final String NETWORK_TYPE_UNKNOWN = "unknowntype";
    private static final String NETWORK_TYPE_WIFI = "wifi";
    private static final String OPERATOR_NAME_KEY = "operator=";
    private static final String SCREEN_RESOLUTION = "%sx%s";
    private static final String SCREEN_RESOLUTION_KEY = "screenresolution=";
    private static final String SCREEN_SIZE_DPI_KEY = "dpi=";
    private static final String UTF_8_ENCODING = "utf-8";

    @NonNull
    public static String getDeviceInfo(@NonNull Context context) {
        try {
            return "&dpi=" + URLEncoder.encode(String.valueOf(getScreenSizeDpi(context)), "utf-8") + AMPERSAND + SCREEN_RESOLUTION_KEY + URLEncoder.encode(getScreenResolution(context), "utf-8") + AMPERSAND + ANDROID_VERSION_KEY + URLEncoder.encode(String.valueOf(getAndroidOsVersionInt()), "utf-8") + AMPERSAND + IS_TABLET_KEY + URLEncoder.encode(String.valueOf(isTablet(context)), "utf-8") + AMPERSAND + MANUFACTURER_KEY + URLEncoder.encode(getManufacturer(), "utf-8") + AMPERSAND + DEVICE_NAME_KEY + URLEncoder.encode(getDeviceName(), "utf-8") + AMPERSAND + CONNECTION_TYPE_KEY + URLEncoder.encode(getConnectType(context), "utf-8") + AMPERSAND + OPERATOR_NAME_KEY + URLEncoder.encode(getOperatorName(context), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Nullable
    public static HashMap<String, String> getDeviceInfoMap(@NonNull Context context) {
        HashMap<String, String> info = new HashMap(9);
        try {
            info.put(SCREEN_SIZE_DPI_KEY, URLEncoder.encode(String.valueOf(getScreenSizeDpi(context)), "utf-8"));
            info.put(SCREEN_RESOLUTION_KEY, URLEncoder.encode(getScreenResolution(context), "utf-8"));
            info.put(ANDROID_VERSION_KEY, URLEncoder.encode(String.valueOf(getAndroidOsVersionInt()), "utf-8"));
            info.put(IS_TABLET_KEY, URLEncoder.encode(String.valueOf(isTablet(context)), "utf-8"));
            info.put(MANUFACTURER_KEY, URLEncoder.encode(getManufacturer(), "utf-8"));
            info.put(DEVICE_NAME_KEY, URLEncoder.encode(getDeviceName(), "utf-8"));
            info.put(CONNECTION_TYPE_KEY, URLEncoder.encode(getConnectType(context), "utf-8"));
            info.put(OPERATOR_NAME_KEY, URLEncoder.encode(getOperatorName(context), "utf-8"));
            return info;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getScreenResolution(@NonNull Context context) {
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        return String.format(SCREEN_RESOLUTION, new Object[]{Integer.valueOf(width), Integer.valueOf(height)});
    }

    private static int getScreenSizeDpi(@NonNull Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    @TargetApi(9)
    private static boolean isTablet(@NonNull Context context) {
        boolean xlarge;
        if ((context.getResources().getConfiguration().screenLayout & 15) == 4) {
            xlarge = true;
        } else {
            xlarge = false;
        }
        boolean large;
        if ((context.getResources().getConfiguration().screenLayout & 15) == 3) {
            large = true;
        } else {
            large = false;
        }
        if (xlarge || large) {
            return true;
        }
        return false;
    }

    private static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    private static String getDeviceName() {
        return Build.DEVICE;
    }

    private static int getAndroidOsVersionInt() {
        return VERSION.SDK_INT;
    }

    private static String getConnectType(@NonNull Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            return NETWORK_TYPE_UNKNOWN;
        }
        int networkInfoType = networkInfo.getType();
        if (networkInfoType == 1) {
            return NETWORK_TYPE_WIFI;
        }
        if (networkInfoType == 0) {
            return networkInfo.getSubtypeName();
        }
        return NETWORK_TYPE_UNKNOWN;
    }

    private static String getOperatorName(@NonNull Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
    }
}
