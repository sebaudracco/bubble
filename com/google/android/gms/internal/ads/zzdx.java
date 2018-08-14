package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdx extends zzei {
    public zzdx(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 3);
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zztq) {
            zzcm com_google_android_gms_internal_ads_zzcm = new zzcm((String) this.zztz.invoke(null, new Object[]{this.zzps.getContext()}));
            synchronized (this.zztq) {
                this.zztq.zzdd = Long.valueOf(com_google_android_gms_internal_ads_zzcm.zzri);
                this.zztq.zzey = Long.valueOf(com_google_android_gms_internal_ads_zzcm.zzrj);
            }
        }
    }
}
