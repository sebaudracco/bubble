package com.facebook.ads.internal.p059a;

import android.text.TextUtils;
import java.util.Locale;

public enum C1875c {
    NONE,
    INSTALLED,
    NOT_INSTALLED;

    public static C1875c m5634a(String str) {
        if (TextUtils.isEmpty(str)) {
            return NONE;
        }
        try {
            return C1875c.valueOf(str.toUpperCase(Locale.US));
        } catch (IllegalArgumentException e) {
            return NONE;
        }
    }
}
