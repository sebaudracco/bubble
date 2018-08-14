package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzaun<P> {
    private final P zzdhm;
    private final byte[] zzdhn;
    private final zzaxl zzdho;
    private final zzayd zzdhp;

    public zzaun(P p, byte[] bArr, zzaxl com_google_android_gms_internal_ads_zzaxl, zzayd com_google_android_gms_internal_ads_zzayd) {
        this.zzdhm = p;
        this.zzdhn = Arrays.copyOf(bArr, bArr.length);
        this.zzdho = com_google_android_gms_internal_ads_zzaxl;
        this.zzdhp = com_google_android_gms_internal_ads_zzayd;
    }

    public final P zzwi() {
        return this.zzdhm;
    }

    public final byte[] zzwj() {
        return this.zzdhn == null ? null : Arrays.copyOf(this.zzdhn, this.zzdhn.length);
    }
}
