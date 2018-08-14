package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class zzaux implements zzaug<zzatz> {
    zzaux() {
    }

    private static zzatz zzc(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzaxv) {
            zzaxv com_google_android_gms_internal_ads_zzaxv = (zzaxv) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzj(com_google_android_gms_internal_ads_zzaxv.getVersion(), 0);
            String zzaab = com_google_android_gms_internal_ads_zzaxv.zzzy().zzaab();
            return zzauj.zzdx(zzaab).zzdw(zzaab);
        }
        throw new GeneralSecurityException("expected KmsAeadKey proto");
    }

    private static zzatz zzd(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            return zzc(zzaxv.zzaj(com_google_android_gms_internal_ads_zzbah));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected KmsAeadKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return zzd(com_google_android_gms_internal_ads_zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        return zzc(com_google_android_gms_internal_ads_zzbcu);
    }

    public final zzbcu zzb(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            return zzb(zzaxx.zzak(com_google_android_gms_internal_ads_zzbah));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized KmsAeadKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzaxx) {
            return zzaxv.zzzz().zzb((zzaxx) com_google_android_gms_internal_ads_zzbcu).zzbe(0).zzadi();
        }
        throw new GeneralSecurityException("expected KmsAeadKeyFormat proto");
    }

    public final zzaxi zzc(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.KmsAeadKey").zzai(((zzaxv) zzb(com_google_android_gms_internal_ads_zzbah)).zzaav()).zzb(zzb.REMOTE).zzadi();
    }
}
