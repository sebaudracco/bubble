package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class zzn implements OnTouchListener {
    private final /* synthetic */ zzl zzwp;
    private final /* synthetic */ zzx zzwq;

    zzn(zzl com_google_android_gms_ads_internal_zzl, zzx com_google_android_gms_ads_internal_zzx) {
        this.zzwp = com_google_android_gms_ads_internal_zzl;
        this.zzwq = com_google_android_gms_ads_internal_zzx;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        this.zzwq.recordClick();
        if (this.zzwp.zzwn != null) {
            this.zzwp.zzwn.zzpi();
        }
        return false;
    }
}
