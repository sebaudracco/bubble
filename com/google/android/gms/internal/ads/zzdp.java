package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdp extends zzei {
    public zzdp(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 5);
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdf = Long.valueOf(-1);
        this.zztq.zzdg = Long.valueOf(-1);
        int[] iArr = (int[]) this.zztz.invoke(null, new Object[]{this.zzps.getContext()});
        synchronized (this.zztq) {
            this.zztq.zzdf = Long.valueOf((long) iArr[0]);
            this.zztq.zzdg = Long.valueOf((long) iArr[1]);
            if (iArr[2] != Integer.MIN_VALUE) {
                this.zztq.zzex = Long.valueOf((long) iArr[2]);
            }
        }
    }
}
