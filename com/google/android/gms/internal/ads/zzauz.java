package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class zzauz implements zzaug<zzatz> {
    zzauz() {
    }

    private final zzatz zzd(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            zzaxz zzal = zzaxz.zzal(com_google_android_gms_internal_ads_zzbah);
            if (zzal instanceof zzaxz) {
                zzal = zzal;
                zzazq.zzj(zzal.getVersion(), 0);
                String zzaah = zzal.zzaae().zzaah();
                return new zzauy(zzal.zzaae().zzaai(), zzauj.zzdx(zzaah).zzdw(zzaah));
            }
            throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized KmSEnvelopeAeadKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return zzd(com_google_android_gms_internal_ads_zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzaxz) {
            zzaxz com_google_android_gms_internal_ads_zzaxz = (zzaxz) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzj(com_google_android_gms_internal_ads_zzaxz.getVersion(), 0);
            String zzaah = com_google_android_gms_internal_ads_zzaxz.zzaae().zzaah();
            return new zzauy(com_google_android_gms_internal_ads_zzaxz.zzaae().zzaai(), zzauj.zzdx(zzaah).zzdw(zzaah));
        }
        throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
    }

    public final zzbcu zzb(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            return zzb(zzayb.zzam(com_google_android_gms_internal_ads_zzbah));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized KmsEnvelopeAeadKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzayb) {
            return zzaxz.zzaaf().zzb((zzayb) com_google_android_gms_internal_ads_zzbcu).zzbf(0).zzadi();
        }
        throw new GeneralSecurityException("expected KmsEnvelopeAeadKeyFormat proto");
    }

    public final zzaxi zzc(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey").zzai(((zzaxz) zzb(com_google_android_gms_internal_ads_zzbah)).zzaav()).zzb(zzb.REMOTE).zzadi();
    }
}
