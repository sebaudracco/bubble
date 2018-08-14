package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.SharedPreferences;

public class dl {
    public static SharedPreferences m15731a(Context context, String str) {
        return context.getSharedPreferences(context.getPackageName() + str, 0);
    }

    public static void m15732a(SharedPreferences sharedPreferences, String str, int i) {
        if (sharedPreferences != null && sharedPreferences.contains(str)) {
            try {
                sharedPreferences.edit().remove(str).putLong(str, (long) sharedPreferences.getInt(str, i)).apply();
            } catch (ClassCastException e) {
            }
        }
    }
}
