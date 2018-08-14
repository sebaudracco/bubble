package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;

final class zzpr implements zzox {
    private final /* synthetic */ View zzbkc;
    private final /* synthetic */ zzpp zzbkj;

    zzpr(zzpp com_google_android_gms_internal_ads_zzpp, View view) {
        this.zzbkj = com_google_android_gms_internal_ads_zzpp;
        this.zzbkc = view;
    }

    public final void zzc(MotionEvent motionEvent) {
        this.zzbkj.onTouch(null, motionEvent);
    }

    public final void zzki() {
        if (zzpp.zza(this.zzbkj, zzpp.zzbjs)) {
            this.zzbkj.onClick(this.zzbkc);
        }
    }
}
