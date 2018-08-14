package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class zzauw implements zzaug<zzatz> {
    zzauw() {
    }

    private final zzatz zzd(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            zzawi zzu = zzawi.zzu(com_google_android_gms_internal_ads_zzbah);
            if (zzu instanceof zzawi) {
                zzu = zzu;
                zzazq.zzj(zzu.getVersion(), 0);
                if (zzu.zzwv().size() == 32) {
                    return new zzaym(zzu.zzwv().toByteArray());
                }
                throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
            }
            throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
        } catch (Throwable e) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305 key", e);
        }
    }

    private static zzawi zzwl() throws GeneralSecurityException {
        return (zzawi) zzawi.zzxn().zzap(0).zzv(zzbah.zzo(zzazl.zzbh(32))).zzadi();
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return zzd(com_google_android_gms_internal_ads_zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzawi) {
            zzawi com_google_android_gms_internal_ads_zzawi = (zzawi) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzj(com_google_android_gms_internal_ads_zzawi.getVersion(), 0);
            if (com_google_android_gms_internal_ads_zzawi.zzwv().size() == 32) {
                return new zzaym(com_google_android_gms_internal_ads_zzawi.zzwv().toByteArray());
            }
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
        throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
    }

    public final zzbcu zzb(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return zzwl();
    }

    public final zzbcu zzb(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        return zzwl();
    }

    public final zzaxi zzc(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key").zzai(zzwl().zzaav()).zzb(zzb.SYMMETRIC).zzadi();
    }
}
