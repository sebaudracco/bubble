package com.integralads.avid.library.adcolony;

import android.support.annotation.NonNull;
import android.text.TextUtils;

public class AvidBridge {
    public static final String APP_STATE_ACTIVE = "active";
    public static final String APP_STATE_INACTIVE = "inactive";
    private static String f8290a;

    public static void setAvidJs(@NonNull String avidJSContent) {
        f8290a = avidJSContent;
    }

    public static boolean isAvidJsReady() {
        return !TextUtils.isEmpty(f8290a);
    }

    public static String getAvidJs() {
        return f8290a;
    }

    public static void cleanUpAvidJS() {
        f8290a = null;
    }
}
