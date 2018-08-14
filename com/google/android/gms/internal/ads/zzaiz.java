package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

final /* synthetic */ class zzaiz implements Callable {
    private final zzaiy zzcnx;
    private final Context zzcny;

    zzaiz(zzaiy com_google_android_gms_internal_ads_zzaiy, Context context) {
        this.zzcnx = com_google_android_gms_internal_ads_zzaiy;
        this.zzcny = context;
    }

    public final Object call() {
        return this.zzcnx.zzad(this.zzcny);
    }
}
