package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzaqw;
import java.util.Map;

final class zzu implements zzv<zzaqw> {
    zzu() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) obj;
        if (map.keySet().contains("start")) {
            com_google_android_gms_internal_ads_zzaqw.zzak(true);
        }
        if (map.keySet().contains("stop")) {
            com_google_android_gms_internal_ads_zzaqw.zzak(false);
        }
    }
}
