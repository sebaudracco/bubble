package com.appsgeyser.sdk.configuration;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

public class PreferencesCoder {
    private static final String PREFS_NAME = "AppsgeyserPrefs";
    private final SharedPreferences prefsSettings;

    public PreferencesCoder(@NonNull Context context) {
        this.prefsSettings = context.getSharedPreferences(PREFS_NAME, 0);
    }

    public String getPrefString(String name, String defValue) {
        return this.prefsSettings.getString(name, defValue);
    }

    public void savePrefString(String name, String value) {
        Editor editor = this.prefsSettings.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public int getPrefInt(String name, int defValue) {
        return this.prefsSettings.getInt(name, defValue);
    }

    public void savePrefInt(String name, int value) {
        Editor editor = this.prefsSettings.edit();
        editor.putInt(name, value);
        editor.commit();
    }

    public long getPrefLong(String name, long defValue) {
        return this.prefsSettings.getLong(name, defValue);
    }

    public void savePrefLong(String name, long value) {
        Editor editor = this.prefsSettings.edit();
        editor.putLong(name, value);
        editor.commit();
    }

    public boolean getPrefBoolean(String name, boolean defValue) {
        return this.prefsSettings.getBoolean(name, defValue);
    }

    public void savePrefBoolean(String name, boolean value) {
        Editor editor = this.prefsSettings.edit();
        editor.putBoolean(name, value);
        editor.commit();
    }
}
