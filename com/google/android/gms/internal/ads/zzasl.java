package com.google.android.gms.internal.ads;

import android.view.View;

final class zzasl implements Runnable {
    private final /* synthetic */ View val$view;
    private final /* synthetic */ zzait zzdcg;
    private final /* synthetic */ int zzdch;
    private final /* synthetic */ zzasj zzdes;

    zzasl(zzasj com_google_android_gms_internal_ads_zzasj, View view, zzait com_google_android_gms_internal_ads_zzait, int i) {
        this.zzdes = com_google_android_gms_internal_ads_zzasj;
        this.val$view = view;
        this.zzdcg = com_google_android_gms_internal_ads_zzait;
        this.zzdch = i;
    }

    public final void run() {
        zzasj.zza(this.zzdes, this.val$view, this.zzdcg, this.zzdch - 1);
    }
}
