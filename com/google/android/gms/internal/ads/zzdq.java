package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdq extends zzei {
    private static volatile Long zzej = null;
    private static final Object zztn = new Object();

    public zzdq(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 44);
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (zzej == null) {
            synchronized (zztn) {
                if (zzej == null) {
                    zzej = (Long) this.zztz.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zztq) {
            this.zztq.zzej = zzej;
        }
    }
}
