package com.appsgeyser.sdk;

import android.util.Log;
import com.appsgeyser.sdk.configuration.Constants;

public class Logger {
    public static void DebugLog(String message) {
        Log.d(Constants.LOG_DEBUG_TAG, message);
    }

    static void ErrorLog(String message) {
        Log.e(Constants.LOG_ERROR_TAG, message);
    }

    public static void InfoLog(String message) {
        Log.i(Constants.LOG_INFO_TAG, message);
    }
}
