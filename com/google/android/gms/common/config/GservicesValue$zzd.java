package com.google.android.gms.common.config;

import android.content.ContentResolver;
import com.google.android.gms.internal.stable.zzg;
import com.google.android.gms.internal.stable.zzi;

class GservicesValue$zzd implements GservicesValue$zza {
    private final ContentResolver mContentResolver;

    public GservicesValue$zzd(ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    public final Long getLong(String str, Long l) {
        return Long.valueOf(zzi.getLong(this.mContentResolver, str, l.longValue()));
    }

    public final String getString(String str, String str2) {
        return zzi.zza(this.mContentResolver, str, str2);
    }

    public final Boolean zza(String str, Boolean bool) {
        return Boolean.valueOf(zzi.zza(this.mContentResolver, str, bool.booleanValue()));
    }

    public final Double zza(String str, Double d) {
        String zza = zzi.zza(this.mContentResolver, str, null);
        if (zza != null) {
            try {
                d = Double.valueOf(Double.parseDouble(zza));
            } catch (NumberFormatException e) {
            }
        }
        return d;
    }

    public final Float zza(String str, Float f) {
        String zza = zzi.zza(this.mContentResolver, str, null);
        if (zza != null) {
            try {
                f = Float.valueOf(Float.parseFloat(zza));
            } catch (NumberFormatException e) {
            }
        }
        return f;
    }

    public final Integer zza(String str, Integer num) {
        return Integer.valueOf(zzi.getInt(this.mContentResolver, str, num.intValue()));
    }

    public final String zzb(String str, String str2) {
        return zzg.zza(this.mContentResolver, str, str2);
    }
}
