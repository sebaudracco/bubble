package com.integralads.avid.library.mopub.utils;

import android.text.TextUtils;
import android.util.Log;

public class AvidLogs {
    private static final boolean DEBUG = true;
    private static final String TAG = "AVID";

    public static void m11436d(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.d("AVID", msg);
        }
    }

    public static void m11440w(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.w("AVID", msg);
        }
    }

    public static void m11439i(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.i("AVID", msg);
        }
    }

    public static void m11437e(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.e("AVID", msg);
        }
    }

    public static void m11438e(String msg, Exception e) {
        if (!TextUtils.isEmpty(msg) || e != null) {
            Log.e("AVID", msg, e);
        }
    }
}
