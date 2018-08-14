package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class zzauv implements zzaug<zzatz> {
    zzauv() {
    }

    private final zzatz zzd(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            zzawe zzr = zzawe.zzr(com_google_android_gms_internal_ads_zzbah);
            if (zzr instanceof zzawe) {
                zzr = zzr;
                zzazq.zzj(zzr.getVersion(), 0);
                zzazq.zzbi(zzr.zzwv().size());
                return new zzayj(zzr.zzwv().toByteArray());
            }
            throw new GeneralSecurityException("expected AesGcmKey proto");
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected AesGcmKey proto");
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return zzd(com_google_android_gms_internal_ads_zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzawe) {
            zzawe com_google_android_gms_internal_ads_zzawe = (zzawe) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzj(com_google_android_gms_internal_ads_zzawe.getVersion(), 0);
            zzazq.zzbi(com_google_android_gms_internal_ads_zzawe.zzwv().size());
            return new zzayj(com_google_android_gms_internal_ads_zzawe.zzwv().toByteArray());
        }
        throw new GeneralSecurityException("expected AesGcmKey proto");
    }

    public final zzbcu zzb(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            return zzb(zzawg.zzt(com_google_android_gms_internal_ads_zzbah));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized AesGcmKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzawg) {
            zzawg com_google_android_gms_internal_ads_zzawg = (zzawg) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzbi(com_google_android_gms_internal_ads_zzawg.getKeySize());
            return zzawe.zzxk().zzs(zzbah.zzo(zzazl.zzbh(com_google_android_gms_internal_ads_zzawg.getKeySize()))).zzao(0).zzadi();
        }
        throw new GeneralSecurityException("expected AesGcmKeyFormat proto");
    }

    public final zzaxi zzc(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.AesGcmKey").zzai(((zzawe) zzb(com_google_android_gms_internal_ads_zzbah)).zzaav()).zzb(zzb.SYMMETRIC).zzadi();
    }
}
