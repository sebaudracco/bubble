package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;

final class zzpo implements zzox {
    private final /* synthetic */ View zzbkc;
    private final /* synthetic */ zzpn zzbkd;

    zzpo(zzpn com_google_android_gms_internal_ads_zzpn, View view) {
        this.zzbkd = com_google_android_gms_internal_ads_zzpn;
        this.zzbkc = view;
    }

    public final void zzc(MotionEvent motionEvent) {
        this.zzbkd.onTouch(null, motionEvent);
    }

    public final void zzki() {
        this.zzbkd.onClick(this.zzbkc);
    }
}
