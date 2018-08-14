package com.google.android.gms.internal.ads;

import android.view.View;

final class zzgl implements Runnable {
    private final /* synthetic */ View zzaij;
    private final /* synthetic */ zzgk zzaik;

    zzgl(zzgk com_google_android_gms_internal_ads_zzgk, View view) {
        this.zzaik = com_google_android_gms_internal_ads_zzgk;
        this.zzaij = view;
    }

    public final void run() {
        this.zzaik.zzk(this.zzaij);
    }
}
