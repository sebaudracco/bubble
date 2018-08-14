package com.appnext.base.p023b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;

public final class C1043d {
    @SuppressLint({"StaticFieldLeak"})
    private static Context jW;

    private C1043d() {
    }

    public static void init(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context shouldn't be null");
        }
        jW = context.getApplicationContext();
    }

    public static void cr() {
        jW = null;
    }

    public static Context getContext() {
        return jW;
    }

    public static AssetManager getAssets() {
        return C1043d.getContext().getAssets();
    }

    public static PackageManager getPackageManager() {
        return C1043d.getContext().getPackageManager();
    }

    public static String getPackageName() {
        return C1043d.getContext().getPackageName();
    }

    public static SharedPreferences getSharedPreferences(String str, int i) {
        return C1043d.getContext().getSharedPreferences(str, i);
    }
}
