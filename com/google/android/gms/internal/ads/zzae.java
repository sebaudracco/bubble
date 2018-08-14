package com.google.android.gms.internal.ads;

public class zzae extends Exception {
    private long zzad;
    private final zzp zzbj;

    public zzae() {
        this.zzbj = null;
    }

    public zzae(zzp com_google_android_gms_internal_ads_zzp) {
        this.zzbj = com_google_android_gms_internal_ads_zzp;
    }

    public zzae(String str) {
        super(str);
        this.zzbj = null;
    }

    public zzae(Throwable th) {
        super(th);
        this.zzbj = null;
    }

    final void zza(long j) {
        this.zzad = j;
    }
}
