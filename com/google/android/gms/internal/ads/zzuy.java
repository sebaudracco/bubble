package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.common.util.Predicate;

final /* synthetic */ class zzuy implements Predicate {
    private final zzv zzbps;

    zzuy(zzv com_google_android_gms_ads_internal_gmsg_zzv) {
        this.zzbps = com_google_android_gms_ads_internal_gmsg_zzv;
    }

    public final boolean apply(Object obj) {
        zzv com_google_android_gms_ads_internal_gmsg_zzv = (zzv) obj;
        return (com_google_android_gms_ads_internal_gmsg_zzv instanceof zzvd) && ((zzvd) com_google_android_gms_ads_internal_gmsg_zzv).zzbpw.equals(this.zzbps);
    }
}
