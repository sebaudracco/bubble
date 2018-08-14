package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class zzauu implements zzaug<zzatz> {
    zzauu() {
    }

    private final zzatz zzd(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            zzavy zzo = zzavy.zzo(com_google_android_gms_internal_ads_zzbah);
            if (zzo instanceof zzavy) {
                zzo = zzo;
                zzazq.zzj(zzo.getVersion(), 0);
                zzazq.zzbi(zzo.zzwv().size());
                if (zzo.zzxe().zzxb() == 12 || zzo.zzxe().zzxb() == 16) {
                    return new zzayi(zzo.zzwv().toByteArray(), zzo.zzxe().zzxb());
                }
                throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
            }
            throw new GeneralSecurityException("expected AesEaxKey proto");
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized AesEaxKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return zzd(com_google_android_gms_internal_ads_zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzavy) {
            zzavy com_google_android_gms_internal_ads_zzavy = (zzavy) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzj(com_google_android_gms_internal_ads_zzavy.getVersion(), 0);
            zzazq.zzbi(com_google_android_gms_internal_ads_zzavy.zzwv().size());
            if (com_google_android_gms_internal_ads_zzavy.zzxe().zzxb() == 12 || com_google_android_gms_internal_ads_zzavy.zzxe().zzxb() == 16) {
                return new zzayi(com_google_android_gms_internal_ads_zzavy.zzwv().toByteArray(), com_google_android_gms_internal_ads_zzavy.zzxe().zzxb());
            }
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
        throw new GeneralSecurityException("expected AesEaxKey proto");
    }

    public final zzbcu zzb(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            return zzb(zzawa.zzq(com_google_android_gms_internal_ads_zzbah));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized AesEaxKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzawa) {
            zzawa com_google_android_gms_internal_ads_zzawa = (zzawa) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzbi(com_google_android_gms_internal_ads_zzawa.getKeySize());
            if (com_google_android_gms_internal_ads_zzawa.zzxe().zzxb() == 12 || com_google_android_gms_internal_ads_zzawa.zzxe().zzxb() == 16) {
                return zzavy.zzxf().zzp(zzbah.zzo(zzazl.zzbh(com_google_android_gms_internal_ads_zzawa.getKeySize()))).zzb(com_google_android_gms_internal_ads_zzawa.zzxe()).zzan(0).zzadi();
            }
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
        throw new GeneralSecurityException("expected AesEaxKeyFormat proto");
    }

    public final zzaxi zzc(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.AesEaxKey").zzai(((zzavy) zzb(com_google_android_gms_internal_ads_zzbah)).zzaav()).zzb(zzb.SYMMETRIC).zzadi();
    }
}
