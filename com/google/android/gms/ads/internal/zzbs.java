package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzch;
import com.google.android.gms.internal.ads.zzci;
import java.util.concurrent.Callable;

final class zzbs implements Callable<zzci> {
    private final /* synthetic */ zzbp zzaba;

    zzbs(zzbp com_google_android_gms_ads_internal_zzbp) {
        this.zzaba = com_google_android_gms_ads_internal_zzbp;
    }

    public final /* synthetic */ Object call() throws Exception {
        return new zzci(zzch.zza(zzbp.zzc(this.zzaba).zzcw, zzbp.zzd(this.zzaba), false));
    }
}
