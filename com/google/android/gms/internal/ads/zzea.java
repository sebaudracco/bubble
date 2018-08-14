package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzea extends zzei {
    private final StackTraceElement[] zztv;

    public zzea(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2, StackTraceElement[] stackTraceElementArr) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 45);
        this.zztv = stackTraceElementArr;
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zztv != null) {
            zzcx com_google_android_gms_internal_ads_zzcx = new zzcx((String) this.zztz.invoke(null, new Object[]{this.zztv}));
            synchronized (this.zztq) {
                this.zztq.zzek = com_google_android_gms_internal_ads_zzcx.zzro;
                if (com_google_android_gms_internal_ads_zzcx.zzrp.booleanValue()) {
                    this.zztq.zzes = Integer.valueOf(com_google_android_gms_internal_ads_zzcx.zzrq.booleanValue() ? 0 : 1);
                }
            }
        }
    }
}
