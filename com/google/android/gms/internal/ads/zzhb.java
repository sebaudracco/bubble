package com.google.android.gms.internal.ads;

import java.util.Comparator;

public final class zzhb implements Comparator<zzgp> {
    public zzhb(zzha com_google_android_gms_internal_ads_zzha) {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzgp com_google_android_gms_internal_ads_zzgp = (zzgp) obj;
        zzgp com_google_android_gms_internal_ads_zzgp2 = (zzgp) obj2;
        if (com_google_android_gms_internal_ads_zzgp.zzhc() < com_google_android_gms_internal_ads_zzgp2.zzhc()) {
            return -1;
        }
        if (com_google_android_gms_internal_ads_zzgp.zzhc() > com_google_android_gms_internal_ads_zzgp2.zzhc()) {
            return 1;
        }
        if (com_google_android_gms_internal_ads_zzgp.zzhb() < com_google_android_gms_internal_ads_zzgp2.zzhb()) {
            return -1;
        }
        if (com_google_android_gms_internal_ads_zzgp.zzhb() > com_google_android_gms_internal_ads_zzgp2.zzhb()) {
            return 1;
        }
        float zzhe = (com_google_android_gms_internal_ads_zzgp.zzhe() - com_google_android_gms_internal_ads_zzgp.zzhc()) * (com_google_android_gms_internal_ads_zzgp.zzhd() - com_google_android_gms_internal_ads_zzgp.zzhb());
        float zzhe2 = (com_google_android_gms_internal_ads_zzgp2.zzhe() - com_google_android_gms_internal_ads_zzgp2.zzhc()) * (com_google_android_gms_internal_ads_zzgp2.zzhd() - com_google_android_gms_internal_ads_zzgp2.zzhb());
        return zzhe <= zzhe2 ? zzhe < zzhe2 ? 1 : 0 : -1;
    }
}
