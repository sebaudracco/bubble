package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

public final class zzdt implements Callable {
    private final zzcz zzps;
    private final zzba zztq;

    public zzdt(zzcz com_google_android_gms_internal_ads_zzcz, zzba com_google_android_gms_internal_ads_zzba) {
        this.zzps = com_google_android_gms_internal_ads_zzcz;
        this.zztq = com_google_android_gms_internal_ads_zzba;
    }

    private final Void zzat() throws Exception {
        if (this.zzps.zzak() != null) {
            this.zzps.zzak().get();
        }
        zzbfi zzaj = this.zzps.zzaj();
        if (zzaj != null) {
            try {
                synchronized (this.zztq) {
                    zzbfi.zza(this.zztq, zzbfi.zzb(zzaj));
                }
            } catch (zzbfh e) {
            }
        }
        return null;
    }

    public final /* synthetic */ Object call() throws Exception {
        return zzat();
    }
}
