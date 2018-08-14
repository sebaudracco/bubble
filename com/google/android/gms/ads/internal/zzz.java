package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzasg;

final /* synthetic */ class zzz implements zzasg {
    private final zzajh zzxh;
    private final Runnable zzxi;

    zzz(zzajh com_google_android_gms_internal_ads_zzajh, Runnable runnable) {
        this.zzxh = com_google_android_gms_internal_ads_zzajh;
        this.zzxi = runnable;
    }

    public final void zzda() {
        zzajh com_google_android_gms_internal_ads_zzajh = this.zzxh;
        Runnable runnable = this.zzxi;
        if (!com_google_android_gms_internal_ads_zzajh.zzcoc) {
            zzbv.zzek();
            zzakk.zzd(runnable);
        }
    }
}
