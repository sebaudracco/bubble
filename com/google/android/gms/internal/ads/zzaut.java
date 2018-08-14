package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class zzaut implements zzaug<zzazi> {
    zzaut() {
    }

    private static void zza(zzavw com_google_android_gms_internal_ads_zzavw) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzavw.zzxb() < 12 || com_google_android_gms_internal_ads_zzavw.zzxb() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    private final zzayh zze(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            zzavs zzl = zzavs.zzl(com_google_android_gms_internal_ads_zzbah);
            if (zzl instanceof zzavs) {
                zzl = zzl;
                zzazq.zzj(zzl.getVersion(), 0);
                zzazq.zzbi(zzl.zzwv().size());
                zza(zzl.zzwu());
                return new zzayh(zzl.zzwv().toByteArray(), zzl.zzwu().zzxb());
            }
            throw new GeneralSecurityException("expected AesCtrKey proto");
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized AesCtrKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return zze(com_google_android_gms_internal_ads_zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzavs) {
            zzavs com_google_android_gms_internal_ads_zzavs = (zzavs) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzj(com_google_android_gms_internal_ads_zzavs.getVersion(), 0);
            zzazq.zzbi(com_google_android_gms_internal_ads_zzavs.zzwv().size());
            zza(com_google_android_gms_internal_ads_zzavs.zzwu());
            return new zzayh(com_google_android_gms_internal_ads_zzavs.zzwv().toByteArray(), com_google_android_gms_internal_ads_zzavs.zzwu().zzxb());
        }
        throw new GeneralSecurityException("expected AesCtrKey proto");
    }

    public final zzbcu zzb(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            return zzb(zzavu.zzn(com_google_android_gms_internal_ads_zzbah));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized AesCtrKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzavu) {
            zzavu com_google_android_gms_internal_ads_zzavu = (zzavu) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzbi(com_google_android_gms_internal_ads_zzavu.getKeySize());
            zza(com_google_android_gms_internal_ads_zzavu.zzwu());
            return zzavs.zzww().zzc(com_google_android_gms_internal_ads_zzavu.zzwu()).zzm(zzbah.zzo(zzazl.zzbh(com_google_android_gms_internal_ads_zzavu.getKeySize()))).zzam(0).zzadi();
        }
        throw new GeneralSecurityException("expected AesCtrKeyFormat proto");
    }

    public final zzaxi zzc(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.AesCtrKey").zzai(((zzavs) zzb(com_google_android_gms_internal_ads_zzbah)).zzaav()).zzb(zzb.SYMMETRIC).zzadi();
    }
}
