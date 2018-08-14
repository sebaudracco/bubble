package com.google.android.gms.internal.ads;

final class zzaho implements Runnable {
    private final /* synthetic */ zzxq zzclu;
    private final /* synthetic */ zzahn zzclv;
    private final /* synthetic */ zzjj zzyh;

    zzaho(zzahn com_google_android_gms_internal_ads_zzahn, zzjj com_google_android_gms_internal_ads_zzjj, zzxq com_google_android_gms_internal_ads_zzxq) {
        this.zzclv = com_google_android_gms_internal_ads_zzahn;
        this.zzyh = com_google_android_gms_internal_ads_zzjj;
        this.zzclu = com_google_android_gms_internal_ads_zzxq;
    }

    public final void run() {
        zzahn.zza(this.zzclv, this.zzyh, this.zzclu);
    }
}
