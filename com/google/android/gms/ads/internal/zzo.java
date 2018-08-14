package com.google.android.gms.ads.internal;

import android.view.View;
import android.view.View.OnClickListener;

final class zzo implements OnClickListener {
    private final /* synthetic */ zzl zzwp;
    private final /* synthetic */ zzx zzwq;

    zzo(zzl com_google_android_gms_ads_internal_zzl, zzx com_google_android_gms_ads_internal_zzx) {
        this.zzwp = com_google_android_gms_ads_internal_zzl;
        this.zzwq = com_google_android_gms_ads_internal_zzx;
    }

    public final void onClick(View view) {
        this.zzwq.recordClick();
        if (this.zzwp.zzwn != null) {
            this.zzwp.zzwn.zzpi();
        }
    }
}
