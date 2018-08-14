package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdz extends zzei {
    private final boolean zztu;

    public zzdz(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 61);
        this.zztu = com_google_android_gms_internal_ads_zzcz.zzai();
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zztz.invoke(null, new Object[]{this.zzps.getContext(), Boolean.valueOf(this.zztu)})).longValue();
        synchronized (this.zztq) {
            this.zztq.zzez = Long.valueOf(longValue);
        }
    }
}
