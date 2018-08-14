package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzn;

final class zzarb implements zzn {
    private zzn zzbnc;
    private zzaqw zzdcj;

    public zzarb(zzaqw com_google_android_gms_internal_ads_zzaqw, zzn com_google_android_gms_ads_internal_overlay_zzn) {
        this.zzdcj = com_google_android_gms_internal_ads_zzaqw;
        this.zzbnc = com_google_android_gms_ads_internal_overlay_zzn;
    }

    public final void onPause() {
    }

    public final void onResume() {
    }

    public final void zzcb() {
        this.zzbnc.zzcb();
        this.zzdcj.zzty();
    }

    public final void zzcc() {
        this.zzbnc.zzcc();
        this.zzdcj.zzno();
    }
}
