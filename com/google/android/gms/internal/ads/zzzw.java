package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzn;

final class zzzw implements zzn {
    private final /* synthetic */ zzzv zzbvr;

    zzzw(zzzv com_google_android_gms_internal_ads_zzzv) {
        this.zzbvr = com_google_android_gms_internal_ads_zzzv;
    }

    public final void onPause() {
        zzane.zzck("AdMobCustomTabsAdapter overlay is paused.");
    }

    public final void onResume() {
        zzane.zzck("AdMobCustomTabsAdapter overlay is resumed.");
    }

    public final void zzcb() {
        zzane.zzck("AdMobCustomTabsAdapter overlay is closed.");
        zzzv.zza(this.zzbvr).onAdClosed(this.zzbvr);
    }

    public final void zzcc() {
        zzane.zzck("Opening AdMobCustomTabsAdapter overlay.");
        zzzv.zza(this.zzbvr).onAdOpened(this.zzbvr);
    }
}
