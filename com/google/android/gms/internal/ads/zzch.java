package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class zzch extends zzcg {
    private zzch(Context context, String str, boolean z) {
        super(context, str, z);
    }

    public static zzch zza(String str, Context context, boolean z) {
        zzcg.zza(context, z);
        return new zzch(context, str, z);
    }

    protected final List<Callable<Void>> zza(zzcz com_google_android_gms_internal_ads_zzcz, zzba com_google_android_gms_internal_ads_zzba, zzax com_google_android_gms_internal_ads_zzax) {
        if (com_google_android_gms_internal_ads_zzcz.zzab() == null || !this.zzqt) {
            return super.zza(com_google_android_gms_internal_ads_zzcz, com_google_android_gms_internal_ads_zzba, com_google_android_gms_internal_ads_zzax);
        }
        int zzx = com_google_android_gms_internal_ads_zzcz.zzx();
        List<Callable<Void>> arrayList = new ArrayList();
        arrayList.addAll(super.zza(com_google_android_gms_internal_ads_zzcz, com_google_android_gms_internal_ads_zzba, com_google_android_gms_internal_ads_zzax));
        arrayList.add(new zzds(com_google_android_gms_internal_ads_zzcz, "1QeH3Cf7T53ayw17Ebbo9YTdhU+IFx0X5nCtC5gZQym4uicOVPXxYWmMK9k58i8n", "bHJRpFJ+2R5LAbYQUBDMyfYpLd1oiGixlpIqMJOBQPY=", com_google_android_gms_internal_ads_zzba, zzx, 24));
        return arrayList;
    }
}
