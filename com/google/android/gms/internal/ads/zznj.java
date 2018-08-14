package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zznj implements Callable<T> {
    private final /* synthetic */ zzna zzaty;
    private final /* synthetic */ zzni zzatz;

    zznj(zzni com_google_android_gms_internal_ads_zzni, zzna com_google_android_gms_internal_ads_zzna) {
        this.zzatz = com_google_android_gms_internal_ads_zzni;
        this.zzaty = com_google_android_gms_internal_ads_zzna;
    }

    public final T call() {
        return this.zzaty.zza(zzni.zza(this.zzatz));
    }
}
