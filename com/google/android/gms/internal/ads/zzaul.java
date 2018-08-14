package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxr.zzb;
import java.security.GeneralSecurityException;

public final class zzaul {
    @Deprecated
    public static final zzauh zzh(byte[] bArr) throws GeneralSecurityException {
        try {
            zzaxr zzj = zzaxr.zzj(bArr);
            for (zzb com_google_android_gms_internal_ads_zzaxr_zzb : zzj.zzzl()) {
                if (com_google_android_gms_internal_ads_zzaxr_zzb.zzzp().zzyy() == zzaxi.zzb.zzdkw || com_google_android_gms_internal_ads_zzaxr_zzb.zzzp().zzyy() == zzaxi.zzb.zzdkx) {
                    throw new GeneralSecurityException("keyset contains secret key material");
                } else if (com_google_android_gms_internal_ads_zzaxr_zzb.zzzp().zzyy() == zzaxi.zzb.zzdky) {
                    throw new GeneralSecurityException("keyset contains secret key material");
                }
            }
            return zzauh.zza(zzj);
        } catch (zzbbu e) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }
}
