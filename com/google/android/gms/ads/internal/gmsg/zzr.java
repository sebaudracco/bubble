package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.Map;

final class zzr implements zzv<zzaqw> {
    zzr() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) obj;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbad)).booleanValue()) {
            com_google_android_gms_internal_ads_zzaqw.zzaj(!Boolean.parseBoolean((String) map.get("disabled")));
        }
    }
}
