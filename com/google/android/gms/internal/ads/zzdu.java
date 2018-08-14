package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdu extends zzei {
    private static final Object zztn = new Object();
    private static volatile Long zztr = null;

    public zzdu(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 22);
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (zztr == null) {
            synchronized (zztn) {
                if (zztr == null) {
                    zztr = (Long) this.zztz.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zztq) {
            this.zztq.zzdv = zztr;
        }
    }
}
