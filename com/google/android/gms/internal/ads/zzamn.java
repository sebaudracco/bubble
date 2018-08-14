package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;

@zzadh
@TargetApi(17)
public final class zzamn {
    private static zzamn zzcua = null;
    String zzcpq;

    private zzamn() {
    }

    public static zzamn zzsb() {
        if (zzcua == null) {
            zzcua = new zzamn();
        }
        return zzcua;
    }
}
