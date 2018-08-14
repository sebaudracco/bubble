package com.fyber.utils;

import android.os.Build.VERSION;

/* compiled from: OSUtils */
public final class C2665m {
    public static boolean m8522a() {
        return VERSION.SDK_INT == 21 || VERSION.SDK_INT == 22;
    }

    public static boolean m8523a(int i) {
        return VERSION.SDK_INT >= i;
    }

    public static boolean m8524b(int i) {
        return VERSION.SDK_INT < i;
    }
}
