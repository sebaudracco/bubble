package com.google.android.gms.ads.internal.overlay;

import android.graphics.drawable.Drawable;

final class zzk implements Runnable {
    private final /* synthetic */ Drawable zzbyj;
    private final /* synthetic */ zzj zzbyk;

    zzk(zzj com_google_android_gms_ads_internal_overlay_zzj, Drawable drawable) {
        this.zzbyk = com_google_android_gms_ads_internal_overlay_zzj;
        this.zzbyj = drawable;
    }

    public final void run() {
        this.zzbyk.zzbyg.mActivity.getWindow().setBackgroundDrawable(this.zzbyj);
    }
}
