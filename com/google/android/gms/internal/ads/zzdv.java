package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdv extends zzei {
    private long zzts = -1;

    public zzdv(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 12);
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdm = Long.valueOf(-1);
        this.zztq.zzdm = (Long) this.zztz.invoke(null, new Object[]{this.zzps.getContext()});
    }
}
