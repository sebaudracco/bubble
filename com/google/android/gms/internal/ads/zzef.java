package com.google.android.gms.internal.ads;

import android.util.DisplayMetrics;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

public final class zzef extends zzei {
    private final View zztl;

    public zzef(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2, View view) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 57);
        this.zztl = view;
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zztl != null) {
            DisplayMetrics displayMetrics = this.zzps.getContext().getResources().getDisplayMetrics();
            zzdh com_google_android_gms_internal_ads_zzdh = new zzdh((String) this.zztz.invoke(null, new Object[]{this.zztl, displayMetrics}));
            zzbc com_google_android_gms_internal_ads_zzbc = new zzbc();
            com_google_android_gms_internal_ads_zzbc.zzgi = com_google_android_gms_internal_ads_zzdh.zzsx;
            com_google_android_gms_internal_ads_zzbc.zzgj = com_google_android_gms_internal_ads_zzdh.zzgj;
            com_google_android_gms_internal_ads_zzbc.zzgk = com_google_android_gms_internal_ads_zzdh.zzgk;
            this.zztq.zzev = com_google_android_gms_internal_ads_zzbc;
        }
    }
}
