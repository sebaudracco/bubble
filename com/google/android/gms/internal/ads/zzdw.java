package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdw extends zzei {
    private static volatile String zzdc = null;
    private static final Object zztn = new Object();

    public zzdw(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 1);
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdc = "E";
        if (zzdc == null) {
            synchronized (zztn) {
                if (zzdc == null) {
                    zzdc = (String) this.zztz.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zztq) {
            this.zztq.zzdc = zzdc;
        }
    }
}
