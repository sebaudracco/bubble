package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzauh {
    private zzaxr zzdhi;

    private zzauh(zzaxr com_google_android_gms_internal_ads_zzaxr) {
        this.zzdhi = com_google_android_gms_internal_ads_zzaxr;
    }

    static final zzauh zza(zzaxr com_google_android_gms_internal_ads_zzaxr) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzaxr != null && com_google_android_gms_internal_ads_zzaxr.zzzm() > 0) {
            return new zzauh(com_google_android_gms_internal_ads_zzaxr);
        }
        throw new GeneralSecurityException("empty keyset");
    }

    public final String toString() {
        return zzaup.zzb(this.zzdhi).toString();
    }

    final zzaxr zzwg() {
        return this.zzdhi;
    }
}
