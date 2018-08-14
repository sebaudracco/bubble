package com.mopub.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public final class SharedPreferencesHelper {
    public static final String DEFAULT_PREFERENCE_NAME = "mopubSettings";

    private SharedPreferencesHelper() {
    }

    public static SharedPreferences getSharedPreferences(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        return context.getSharedPreferences(DEFAULT_PREFERENCE_NAME, 0);
    }

    public static SharedPreferences getSharedPreferences(@NonNull Context context, @NonNull String preferenceName) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(preferenceName);
        return context.getSharedPreferences(preferenceName, 0);
    }
}
