package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdn extends zzei {
    private static volatile String zztm = null;
    private static final Object zztn = new Object();

    public zzdn(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 29);
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdx = "E";
        if (zztm == null) {
            synchronized (zztn) {
                if (zztm == null) {
                    zztm = (String) this.zztz.invoke(null, new Object[]{this.zzps.getContext()});
                }
            }
        }
        synchronized (this.zztq) {
            this.zztq.zzdx = zzbi.zza(zztm.getBytes(), true);
        }
    }
}
