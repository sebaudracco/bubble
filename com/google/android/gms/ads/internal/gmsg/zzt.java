package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzaqw;
import java.util.Map;

final class zzt implements zzv<zzaqw> {
    zzt() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) obj;
        if (map.keySet().contains("start")) {
            com_google_android_gms_internal_ads_zzaqw.zzuf().zzva();
        } else if (map.keySet().contains("stop")) {
            com_google_android_gms_internal_ads_zzaqw.zzuf().zzvb();
        } else if (map.keySet().contains("cancel")) {
            com_google_android_gms_internal_ads_zzaqw.zzuf().zzvc();
        }
    }
}
