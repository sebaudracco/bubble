package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzmc implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzmb zzath;

    zzmc(zzmb com_google_android_gms_internal_ads_zzmb, Context context) {
        this.zzath = com_google_android_gms_internal_ads_zzmb;
        this.val$context = context;
    }

    public final void run() {
        this.zzath.getRewardedVideoAdInstance(this.val$context);
    }
}
