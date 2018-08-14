package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

@zzadh
public abstract class zzna<T> {
    private final T mDefaultValue;
    private final String mKey;
    private final int zzatr;

    private zzna(int i, String str, T t) {
        this.zzatr = i;
        this.mKey = str;
        this.mDefaultValue = t;
        zzkb.zzij().zza(this);
    }

    public static zzna<String> zza(int i, String str) {
        zzna<String> zza = zza(i, str, null);
        zzkb.zzij().zzb(zza);
        return zza;
    }

    public static zzna<Float> zza(int i, String str, float f) {
        return new zzne(i, str, Float.valueOf(f));
    }

    public static zzna<Integer> zza(int i, String str, int i2) {
        return new zznc(i, str, Integer.valueOf(i2));
    }

    public static zzna<Long> zza(int i, String str, long j) {
        return new zznd(i, str, Long.valueOf(j));
    }

    public static zzna<Boolean> zza(int i, String str, Boolean bool) {
        return new zznb(i, str, bool);
    }

    public static zzna<String> zza(int i, String str, String str2) {
        return new zznf(i, str, str2);
    }

    public static zzna<String> zzb(int i, String str) {
        zzna<String> zza = zza(i, str, null);
        zzkb.zzij().zzc(zza);
        return zza;
    }

    public final String getKey() {
        return this.mKey;
    }

    public final int getSource() {
        return this.zzatr;
    }

    protected abstract T zza(SharedPreferences sharedPreferences);

    public abstract void zza(Editor editor, T t);

    protected abstract T zzb(JSONObject jSONObject);

    public final T zzja() {
        return this.mDefaultValue;
    }
}
