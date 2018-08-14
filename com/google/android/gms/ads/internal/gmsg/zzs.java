package com.google.android.gms.ads.internal.gmsg;

import com.appnext.base.p023b.C1042c;
import com.google.android.gms.internal.ads.zzaqw;
import java.util.Map;

final class zzs implements zzv<zzaqw> {
    zzs() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) obj;
        String str = (String) map.get(C1042c.jL);
        if ("pause".equals(str)) {
            com_google_android_gms_internal_ads_zzaqw.zzcl();
        } else if ("resume".equals(str)) {
            com_google_android_gms_internal_ads_zzaqw.zzcm();
        }
    }
}
