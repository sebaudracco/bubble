package com.appsgeyser.sdk.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.appsgeyser.sdk.configuration.PreferencesCoder;

public class VersionManager {
    private static final String APP_VERSION_ALIAS = "app_version_prefix";
    public static final int DEFAULT_VERSION = -1;

    public static int getPreviousVersion(Context context) {
        return new PreferencesCoder(context).getPrefInt(APP_VERSION_ALIAS, -1);
    }

    public static int getCurrentVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            return -1;
        }
    }

    public static void updateVersion(Context context, int newVersion) {
        new PreferencesCoder(context).savePrefInt(APP_VERSION_ALIAS, newVersion);
    }
}
