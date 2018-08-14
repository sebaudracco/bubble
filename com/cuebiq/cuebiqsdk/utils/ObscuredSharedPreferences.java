package com.cuebiq.cuebiqsdk.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Base64;
import java.util.Map;
import java.util.Set;

public class ObscuredSharedPreferences implements SharedPreferences {
    private static final String UTF8 = "utf-8";
    private final SharedPreferences delegate;

    public class Editor implements android.content.SharedPreferences.Editor {
        final android.content.SharedPreferences.Editor delegate;

        public Editor() {
            this.delegate = ObscuredSharedPreferences.this.delegate.edit();
        }

        public void apply() {
            this.delegate.apply();
        }

        public Editor clear() {
            this.delegate.clear();
            return this;
        }

        public boolean commit() {
            return this.delegate.commit();
        }

        public Editor putBoolean(String str, boolean z) {
            this.delegate.putString(str, ObscuredSharedPreferences.this.encrypt(Boolean.toString(z)));
            return this;
        }

        public Editor putFloat(String str, float f) {
            this.delegate.putString(str, ObscuredSharedPreferences.this.encrypt(Float.toString(f)));
            return this;
        }

        public Editor putInt(String str, int i) {
            this.delegate.putString(str, ObscuredSharedPreferences.this.encrypt(Integer.toString(i)));
            return this;
        }

        public Editor putLong(String str, long j) {
            this.delegate.putString(str, ObscuredSharedPreferences.this.encrypt(Long.toString(j)));
            return this;
        }

        public Editor putString(String str, String str2) {
            this.delegate.putString(str, ObscuredSharedPreferences.this.encrypt(str2));
            return this;
        }

        public android.content.SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            return this;
        }

        public Editor remove(String str) {
            this.delegate.remove(str);
            return this;
        }
    }

    public ObscuredSharedPreferences(SharedPreferences sharedPreferences) {
        this.delegate = sharedPreferences;
    }

    private String decrypt(String str) {
        try {
            return new String(Base64.decode(str, 2), "utf-8");
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private String encrypt(String str) {
        try {
            return new String(Base64.encode(str.getBytes(), 2), "utf-8");
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public boolean contains(String str) {
        return this.delegate.contains(str);
    }

    public Editor edit() {
        return new Editor();
    }

    public Map<String, ?> getAll() {
        throw new UnsupportedOperationException();
    }

    public boolean getBoolean(String str, boolean z) {
        String string = this.delegate.getString(str, null);
        return string != null ? Boolean.parseBoolean(decrypt(string)) : z;
    }

    public float getFloat(String str, float f) {
        String string = this.delegate.getString(str, null);
        return string != null ? Float.parseFloat(decrypt(string)) : f;
    }

    public int getInt(String str, int i) {
        String string = this.delegate.getString(str, null);
        return string != null ? Integer.parseInt(decrypt(string)) : i;
    }

    public long getLong(String str, long j) {
        String string = this.delegate.getString(str, null);
        return string != null ? Long.parseLong(decrypt(string)) : j;
    }

    public String getString(String str, String str2) {
        String string = this.delegate.getString(str, null);
        return (string == null || string.equals("")) ? str2 : decrypt(string);
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return set;
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.delegate.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.delegate.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }
}
