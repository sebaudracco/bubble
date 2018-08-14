package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

final class zzavh {
    public static zzayv zza(zzawy com_google_android_gms_internal_ads_zzawy) throws GeneralSecurityException {
        switch (zzavi.zzdia[com_google_android_gms_internal_ads_zzawy.ordinal()]) {
            case 1:
                return zzayv.NIST_P256;
            case 2:
                return zzayv.NIST_P384;
            case 3:
                return zzayv.NIST_P521;
            default:
                String valueOf = String.valueOf(com_google_android_gms_internal_ads_zzawy);
                throw new GeneralSecurityException(new StringBuilder(String.valueOf(valueOf).length() + 20).append("unknown curve type: ").append(valueOf).toString());
        }
    }

    public static zzayw zza(zzawk com_google_android_gms_internal_ads_zzawk) throws GeneralSecurityException {
        switch (zzavi.zzdib[com_google_android_gms_internal_ads_zzawk.ordinal()]) {
            case 1:
                return zzayw.UNCOMPRESSED;
            case 2:
                return zzayw.COMPRESSED;
            default:
                String valueOf = String.valueOf(com_google_android_gms_internal_ads_zzawk);
                throw new GeneralSecurityException(new StringBuilder(String.valueOf(valueOf).length() + 22).append("unknown point format: ").append(valueOf).toString());
        }
    }

    public static String zza(zzaxa com_google_android_gms_internal_ads_zzaxa) throws NoSuchAlgorithmException {
        switch (zzavi.zzdhz[com_google_android_gms_internal_ads_zzaxa.ordinal()]) {
            case 1:
                return "HmacSha1";
            case 2:
                return "HmacSha256";
            case 3:
                return "HmacSha512";
            default:
                String valueOf = String.valueOf(com_google_android_gms_internal_ads_zzaxa);
                throw new NoSuchAlgorithmException(new StringBuilder(String.valueOf(valueOf).length() + 27).append("hash unsupported for HMAC: ").append(valueOf).toString());
        }
    }

    public static void zza(zzawq com_google_android_gms_internal_ads_zzawq) throws GeneralSecurityException {
        zzayt.zza(zza(com_google_android_gms_internal_ads_zzawq.zzxu().zzyh()));
        zza(com_google_android_gms_internal_ads_zzawq.zzxu().zzyi());
        if (com_google_android_gms_internal_ads_zzawq.zzxw() == zzawk.UNKNOWN_FORMAT) {
            throw new GeneralSecurityException("unknown EC point format");
        }
        zzauo.zza(com_google_android_gms_internal_ads_zzawq.zzxv().zzxp());
    }
}
