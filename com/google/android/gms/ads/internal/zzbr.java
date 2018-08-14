package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class zzbr implements OnTouchListener {
    private final /* synthetic */ zzbp zzaba;

    zzbr(zzbp com_google_android_gms_ads_internal_zzbp) {
        this.zzaba = com_google_android_gms_ads_internal_zzbp;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (zzbp.zzb(this.zzaba) != null) {
            zzbp.zzb(this.zzaba).zza(motionEvent);
        }
        return false;
    }
}
