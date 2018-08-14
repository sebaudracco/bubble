package com.google.android.gms.internal.ads;

import android.view.View;

final class zzaqz implements Runnable {
    private final /* synthetic */ View val$view;
    private final /* synthetic */ zzait zzdcg;
    private final /* synthetic */ int zzdch;
    private final /* synthetic */ zzaqx zzdci;

    zzaqz(zzaqx com_google_android_gms_internal_ads_zzaqx, View view, zzait com_google_android_gms_internal_ads_zzait, int i) {
        this.zzdci = com_google_android_gms_internal_ads_zzaqx;
        this.val$view = view;
        this.zzdcg = com_google_android_gms_internal_ads_zzait;
        this.zzdch = i;
    }

    public final void run() {
        zzaqx.zza(this.zzdci, this.val$view, this.zzdcg, this.zzdch - 1);
    }
}
