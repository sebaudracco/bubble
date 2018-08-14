package com.facebook.ads.internal.adapters;

import android.text.TextUtils;
import java.util.Locale;

public enum C1895g {
    f4239a,
    AN,
    ADMOB,
    INMOBI,
    YAHOO;

    public static C1895g m5821a(String str) {
        if (TextUtils.isEmpty(str)) {
            return f4239a;
        }
        try {
            return (C1895g) Enum.valueOf(C1895g.class, str.toUpperCase(Locale.getDefault()));
        } catch (Exception e) {
            return f4239a;
        }
    }
}
