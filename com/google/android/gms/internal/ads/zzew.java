package com.google.android.gms.internal.ads;

import android.database.ContentObserver;
import android.os.Handler;

final class zzew extends ContentObserver {
    private final /* synthetic */ zzet zzafk;

    public zzew(zzet com_google_android_gms_internal_ads_zzet, Handler handler) {
        this.zzafk = com_google_android_gms_internal_ads_zzet;
        super(handler);
    }

    public final void onChange(boolean z) {
        super.onChange(z);
        this.zzafk.zzgb();
    }
}
