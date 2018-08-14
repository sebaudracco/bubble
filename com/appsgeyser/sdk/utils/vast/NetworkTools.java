package com.appsgeyser.sdk.utils.vast;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkTools {
    private static final String TAG = HttpTools.class.getName();

    public static boolean connectedToInternet(Context context) {
        VASTLog.m2412d(TAG, "Testing connectivity:");
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo wifiNetwork = cm.getNetworkInfo(1);
        if (wifiNetwork == null || !wifiNetwork.isConnected()) {
            NetworkInfo mobileNetwork = cm.getNetworkInfo(0);
            if (mobileNetwork == null || !mobileNetwork.isConnected()) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork == null || !activeNetwork.isConnected()) {
                    VASTLog.m2412d(TAG, "No Internet connection");
                    return false;
                }
                VASTLog.m2412d(TAG, "Connected to Internet");
                return true;
            }
            VASTLog.m2412d(TAG, "Connected to Internet");
            return true;
        }
        VASTLog.m2412d(TAG, "Connected to Internet");
        return true;
    }
}
