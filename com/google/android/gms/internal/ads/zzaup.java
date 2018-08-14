package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxr.zzb;
import com.google.android.gms.internal.ads.zzbbo.zza;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

final class zzaup {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static zzaxt zzb(zzaxr com_google_android_gms_internal_ads_zzaxr) {
        zza zzbb = zzaxt.zzzu().zzbb(com_google_android_gms_internal_ads_zzaxr.zzzk());
        for (zzb com_google_android_gms_internal_ads_zzaxr_zzb : com_google_android_gms_internal_ads_zzaxr.zzzl()) {
            zzbb.zzb((zzaxt.zzb) zzaxt.zzb.zzzw().zzeh(com_google_android_gms_internal_ads_zzaxr_zzb.zzzp().zzyw()).zzb(com_google_android_gms_internal_ads_zzaxr_zzb.zzzq()).zzb(com_google_android_gms_internal_ads_zzaxr_zzb.zzzs()).zzbd(com_google_android_gms_internal_ads_zzaxr_zzb.zzzr()).zzadi());
        }
        return (zzaxt) zzbb.zzadi();
    }

    public static void zzc(zzaxr com_google_android_gms_internal_ads_zzaxr) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzaxr.zzzm() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        int zzzk = com_google_android_gms_internal_ads_zzaxr.zzzk();
        int i = 1;
        int i2 = 0;
        for (zzb com_google_android_gms_internal_ads_zzaxr_zzb : com_google_android_gms_internal_ads_zzaxr.zzzl()) {
            if (!com_google_android_gms_internal_ads_zzaxr_zzb.zzzo()) {
                throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(com_google_android_gms_internal_ads_zzaxr_zzb.zzzr())}));
            } else if (com_google_android_gms_internal_ads_zzaxr_zzb.zzzs() == zzayd.UNKNOWN_PREFIX) {
                throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(com_google_android_gms_internal_ads_zzaxr_zzb.zzzr())}));
            } else if (com_google_android_gms_internal_ads_zzaxr_zzb.zzzq() == zzaxl.UNKNOWN_STATUS) {
                throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(com_google_android_gms_internal_ads_zzaxr_zzb.zzzr())}));
            } else {
                if (com_google_android_gms_internal_ads_zzaxr_zzb.zzzq() == zzaxl.ENABLED && com_google_android_gms_internal_ads_zzaxr_zzb.zzzr() == zzzk) {
                    if (i2 != 0) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    i2 = 1;
                }
                i = com_google_android_gms_internal_ads_zzaxr_zzb.zzzp().zzyy() != zzaxi.zzb.ASYMMETRIC_PUBLIC ? 0 : i;
            }
        }
        if (i2 == 0 && i == 0) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
