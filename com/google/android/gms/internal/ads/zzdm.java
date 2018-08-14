package com.google.android.gms.internal.ads;

import android.provider.Settings.SettingNotFoundException;
import java.lang.reflect.InvocationTargetException;

public final class zzdm extends zzei {
    public zzdm(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 49);
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzem = Integer.valueOf(2);
        try {
            this.zztq.zzem = Integer.valueOf(((Boolean) this.zztz.invoke(null, new Object[]{this.zzps.getContext()})).booleanValue() ? 1 : 0);
        } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof SettingNotFoundException)) {
                throw e;
            }
        }
    }
}
