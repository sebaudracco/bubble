package com.google.android.gms.internal.ads;

import java.util.Comparator;

final class zzgs implements Comparator<zzgy> {
    zzgs(zzgr com_google_android_gms_internal_ads_zzgr) {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzgy com_google_android_gms_internal_ads_zzgy = (zzgy) obj;
        zzgy com_google_android_gms_internal_ads_zzgy2 = (zzgy) obj2;
        int i = com_google_android_gms_internal_ads_zzgy.zzajg - com_google_android_gms_internal_ads_zzgy2.zzajg;
        return i != 0 ? i : (int) (com_google_android_gms_internal_ads_zzgy.value - com_google_android_gms_internal_ads_zzgy2.value);
    }
}
