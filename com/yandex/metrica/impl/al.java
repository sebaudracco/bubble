package com.yandex.metrica.impl;

import android.content.Context;

public final class al {
    public static boolean m14592a(Context context, String str) {
        if (str != null) {
            try {
                if (context.checkCallingOrSelfPermission(str) != 0) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static boolean m14591a(Context context) {
        return m14592a(context, "android.permission.ACCESS_COARSE_LOCATION") || m14592a(context, "android.permission.ACCESS_FINE_LOCATION");
    }
}
