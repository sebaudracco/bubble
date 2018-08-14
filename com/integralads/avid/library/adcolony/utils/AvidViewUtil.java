package com.integralads.avid.library.adcolony.utils;

import android.os.Build.VERSION;
import android.view.View;

public class AvidViewUtil {
    public static boolean isViewVisible(View view) {
        if (view.getVisibility() != 0) {
            return false;
        }
        if (VERSION.SDK_INT < 11 || ((double) view.getAlpha()) > 0.0d) {
            return true;
        }
        return false;
    }
}
