package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaqw;
import java.util.Map;

final class zzn implements zzv<zzaqw> {
    zzn() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) obj;
        zzd zzub = com_google_android_gms_internal_ads_zzaqw.zzub();
        if (zzub != null) {
            zzub.close();
            return;
        }
        zzub = com_google_android_gms_internal_ads_zzaqw.zzuc();
        if (zzub != null) {
            zzub.close();
        } else {
            zzakb.zzdk("A GMSG tried to close something that wasn't an overlay.");
        }
    }
}
