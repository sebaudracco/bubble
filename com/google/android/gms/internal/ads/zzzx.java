package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.zzbv;

final class zzzx implements Runnable {
    private final /* synthetic */ zzzv zzbvr;
    private final /* synthetic */ AdOverlayInfoParcel zzzc;

    zzzx(zzzv com_google_android_gms_internal_ads_zzzv, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzbvr = com_google_android_gms_internal_ads_zzzv;
        this.zzzc = adOverlayInfoParcel;
    }

    public final void run() {
        zzbv.zzei();
        zzl.zza(zzzv.zzb(this.zzbvr), this.zzzc, true);
    }
}
