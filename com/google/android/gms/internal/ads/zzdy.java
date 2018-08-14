package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class zzdy extends zzei {
    private List<Long> zztt = null;

    public zzdy(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 31);
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdy = Long.valueOf(-1);
        this.zztq.zzdz = Long.valueOf(-1);
        if (this.zztt == null) {
            this.zztt = (List) this.zztz.invoke(null, new Object[]{this.zzps.getContext()});
        }
        if (this.zztt != null && this.zztt.size() == 2) {
            synchronized (this.zztq) {
                this.zztq.zzdy = Long.valueOf(((Long) this.zztt.get(0)).longValue());
                this.zztq.zzdz = Long.valueOf(((Long) this.zztt.get(1)).longValue());
            }
        }
    }
}
