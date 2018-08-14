package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

@zzadh
final class zzst {
    private final List<zzts> zzxo = new ArrayList();

    zzst() {
    }

    final void zza(zztt com_google_android_gms_internal_ads_zztt) {
        Handler handler = zzakk.zzcrm;
        for (zzts com_google_android_gms_internal_ads_zztr : this.zzxo) {
            handler.post(new zztr(this, com_google_android_gms_internal_ads_zztr, com_google_android_gms_internal_ads_zztt));
        }
        this.zzxo.clear();
    }
}
