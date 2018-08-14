package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdr extends zzei {
    private long startTime;

    public zzdr(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, long j, int i, int i2) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 25);
        this.startTime = j;
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zztz.invoke(null, new Object[0])).longValue();
        synchronized (this.zztq) {
            this.zztq.zzfm = Long.valueOf(longValue);
            if (this.startTime != 0) {
                this.zztq.zzdr = Long.valueOf(longValue - this.startTime);
                this.zztq.zzdw = Long.valueOf(this.startTime);
            }
        }
    }
}
