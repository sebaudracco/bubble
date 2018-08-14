package com.integralads.avid.library.adcolony.utils;

import android.text.TextUtils;
import android.util.Log;

public class AvidLogs {
    private static final boolean f8399a = true;
    private static final String f8400b = "AVID";

    public static void m11160d(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.d("AVID", msg);
        }
    }

    public static void m11164w(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.w("AVID", msg);
        }
    }

    public static void m11163i(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.i("AVID", msg);
        }
    }

    public static void m11161e(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.e("AVID", msg);
        }
    }

    public static void m11162e(String msg, Exception e) {
        if (!TextUtils.isEmpty(msg) || e != null) {
            Log.e("AVID", msg, e);
        }
    }
}
