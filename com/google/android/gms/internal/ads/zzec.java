package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzec extends zzei {
    private final zzdi zzqx;
    private long zzti;

    public zzec(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2, zzdi com_google_android_gms_internal_ads_zzdi) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 53);
        this.zzqx = com_google_android_gms_internal_ads_zzdi;
        if (com_google_android_gms_internal_ads_zzdi != null) {
            this.zzti = com_google_android_gms_internal_ads_zzdi.zzap();
        }
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zzqx != null) {
            this.zztq.zzep = (Long) this.zztz.invoke(null, new Object[]{Long.valueOf(this.zzti)});
        }
    }
}
