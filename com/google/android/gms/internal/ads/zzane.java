package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.util.Log;
import com.google.ads.AdRequest;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public class zzane {
    public static void m3427e(String str) {
        if (isLoggable(6)) {
            Log.e(AdRequest.LOGTAG, str);
        }
    }

    public static boolean isLoggable(int i) {
        return i >= 5 || Log.isLoggable(AdRequest.LOGTAG, i);
    }

    public static void zza(String str, Throwable th) {
        if (isLoggable(3)) {
            Log.d(AdRequest.LOGTAG, str, th);
        }
    }

    public static void zzb(String str, Throwable th) {
        if (isLoggable(6)) {
            Log.e(AdRequest.LOGTAG, str, th);
        }
    }

    public static void zzc(String str, Throwable th) {
        if (isLoggable(5)) {
            Log.w(AdRequest.LOGTAG, str, th);
        }
    }

    public static void zzck(String str) {
        if (isLoggable(3)) {
            Log.d(AdRequest.LOGTAG, str);
        }
    }

    public static void zzd(String str, @Nullable Throwable th) {
        if (!isLoggable(5)) {
            return;
        }
        if (th != null) {
            zzc(zzdl(str), th);
        } else {
            zzdk(zzdl(str));
        }
    }

    public static void zzdj(String str) {
        if (isLoggable(4)) {
            Log.i(AdRequest.LOGTAG, str);
        }
    }

    public static void zzdk(String str) {
        if (isLoggable(5)) {
            Log.w(AdRequest.LOGTAG, str);
        }
    }

    @VisibleForTesting
    private static String zzdl(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length < 4) {
            return str;
        }
        return new StringBuilder(String.valueOf(str).length() + 13).append(str).append(" @").append(stackTrace[3].getLineNumber()).toString();
    }

    public static void zzdm(String str) {
        zzd(str, null);
    }
}
