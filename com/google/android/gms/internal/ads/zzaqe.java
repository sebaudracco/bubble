package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

@zzadh
public final class zzaqe extends zzajx {
    final zzapw zzcyg;
    final zzaqh zzdav;
    private final String zzdaw;

    zzaqe(zzapw com_google_android_gms_internal_ads_zzapw, zzaqh com_google_android_gms_internal_ads_zzaqh, String str) {
        this.zzcyg = com_google_android_gms_internal_ads_zzapw;
        this.zzdav = com_google_android_gms_internal_ads_zzaqh;
        this.zzdaw = str;
        zzbv.zzff().zza(this);
    }

    public final void onStop() {
        this.zzdav.abort();
    }

    public final void zzdn() {
        try {
            this.zzdav.zzdp(this.zzdaw);
        } finally {
            zzakk.zzcrm.post(new zzaqf(this));
        }
    }
}
