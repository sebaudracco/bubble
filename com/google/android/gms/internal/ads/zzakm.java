package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzakm implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzakk zzcru;

    zzakm(zzakk com_google_android_gms_internal_ads_zzakk, Context context) {
        this.zzcru = com_google_android_gms_internal_ads_zzakk;
        this.val$context = context;
    }

    public final void run() {
        synchronized (zzakk.zza(this.zzcru)) {
            zzakk.zza(this.zzcru, zzakk.zzam(this.val$context));
            zzakk.zza(this.zzcru).notifyAll();
        }
    }
}
