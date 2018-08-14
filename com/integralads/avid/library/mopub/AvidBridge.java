package com.integralads.avid.library.mopub;

import android.support.annotation.NonNull;
import android.text.TextUtils;

public class AvidBridge {
    public static final String APP_STATE_ACTIVE = "active";
    public static final String APP_STATE_INACTIVE = "inactive";
    private static String avidJS;

    public static void setAvidJs(@NonNull String avidJSContent) {
        avidJS = avidJSContent;
    }

    public static boolean isAvidJsReady() {
        return !TextUtils.isEmpty(avidJS);
    }

    public static String getAvidJs() {
        return avidJS;
    }

    public static void cleanUpAvidJS() {
        avidJS = null;
    }
}
