package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;
import java.util.logging.Logger;

class zzaus implements zzaug<zzatz> {
    private static final Logger logger = Logger.getLogger(zzaus.class.getName());

    zzaus() throws GeneralSecurityException {
        zzauo.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", new zzaut());
    }

    private final zzatz zzd(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            zzavo zzi = zzavo.zzi(com_google_android_gms_internal_ads_zzbah);
            if (zzi instanceof zzavo) {
                zzi = zzi;
                zzazq.zzj(zzi.getVersion(), 0);
                return new zzayx((zzazi) zzauo.zzb("type.googleapis.com/google.crypto.tink.AesCtrKey", zzi.zzwn()), (zzauk) zzauo.zzb("type.googleapis.com/google.crypto.tink.HmacKey", zzi.zzwo()), zzi.zzwo().zzym().zzyt());
            }
            throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return zzd(com_google_android_gms_internal_ads_zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzavo) {
            zzavo com_google_android_gms_internal_ads_zzavo = (zzavo) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzj(com_google_android_gms_internal_ads_zzavo.getVersion(), 0);
            return new zzayx((zzazi) zzauo.zzb("type.googleapis.com/google.crypto.tink.AesCtrKey", com_google_android_gms_internal_ads_zzavo.zzwn()), (zzauk) zzauo.zzb("type.googleapis.com/google.crypto.tink.HmacKey", com_google_android_gms_internal_ads_zzavo.zzwo()), com_google_android_gms_internal_ads_zzavo.zzwo().zzym().zzyt());
        }
        throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
    }

    public final zzbcu zzb(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            return zzb(zzavq.zzj(com_google_android_gms_internal_ads_zzbah));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzavq) {
            zzavq com_google_android_gms_internal_ads_zzavq = (zzavq) com_google_android_gms_internal_ads_zzbcu;
            return zzavo.zzwp().zzb((zzavs) zzauo.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", com_google_android_gms_internal_ads_zzavq.zzwr())).zzb((zzaxc) zzauo.zza("type.googleapis.com/google.crypto.tink.HmacKey", com_google_android_gms_internal_ads_zzavq.zzws())).zzal(0).zzadi();
        }
        throw new GeneralSecurityException("expected AesCtrHmacAeadKeyFormat proto");
    }

    public final zzaxi zzc(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey").zzai(((zzavo) zzb(com_google_android_gms_internal_ads_zzbah)).zzaav()).zzb(zzb.SYMMETRIC).zzadi();
    }
}
