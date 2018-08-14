package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzane;
import java.util.Map;

@zzadh
public final class zzc implements zzv<Object> {
    private final zzd zzblm;

    public zzc(zzd com_google_android_gms_ads_internal_gmsg_zzd) {
        this.zzblm = com_google_android_gms_ads_internal_gmsg_zzd;
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get("name");
        if (str == null) {
            zzane.zzdk("App event with no name parameter.");
        } else {
            this.zzblm.onAppEvent(str, (String) map.get("info"));
        }
    }
}
