package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzeb extends zzei {
    public zzeb(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 51);
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zztq) {
            zzcy com_google_android_gms_internal_ads_zzcy = new zzcy((String) this.zztz.invoke(null, new Object[0]));
            this.zztq.zzen = com_google_android_gms_internal_ads_zzcy.zzrr;
            this.zztq.zzeo = com_google_android_gms_internal_ads_zzcy.zzrs;
        }
    }
}
