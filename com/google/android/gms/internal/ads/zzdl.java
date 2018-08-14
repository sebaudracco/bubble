package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

public final class zzdl extends zzei {
    private final Activity zztk;
    private final View zztl;

    public zzdl(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2, View view, Activity activity) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 62);
        this.zztl = view;
        this.zztk = activity;
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zztl != null) {
            boolean booleanValue = ((Boolean) zzkb.zzik().zzd(zznk.zzbas)).booleanValue();
            Object[] objArr = (Object[]) this.zztz.invoke(null, new Object[]{this.zztl, this.zztk, Boolean.valueOf(booleanValue)});
            synchronized (this.zztq) {
                this.zztq.zzfa = Long.valueOf(((Long) objArr[0]).longValue());
                this.zztq.zzfb = Long.valueOf(((Long) objArr[1]).longValue());
                if (booleanValue) {
                    this.zztq.zzfc = (String) objArr[2];
                }
            }
        }
    }
}
