package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;

final class zzava implements zzaug<zzaue> {
    zzava() {
    }

    private final zzaue zzf(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            zzaws zzx = zzaws.zzx(com_google_android_gms_internal_ads_zzbah);
            if (zzx instanceof zzaws) {
                zzx = zzx;
                zzazq.zzj(zzx.getVersion(), 0);
                zzavh.zza(zzx.zzxz().zzxs());
                zzawq zzxs = zzx.zzxz().zzxs();
                zzaww zzxu = zzxs.zzxu();
                zzayv zza = zzavh.zza(zzxu.zzyh());
                byte[] toByteArray = zzx.zzwv().toByteArray();
                return new zzayo((ECPrivateKey) ((KeyFactory) zzayy.zzdof.zzek("EC")).generatePrivate(new ECPrivateKeySpec(new BigInteger(1, toByteArray), zzayt.zza(zza))), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
            }
            throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPrivateKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return zzf(com_google_android_gms_internal_ads_zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzaws) {
            zzaws com_google_android_gms_internal_ads_zzaws = (zzaws) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzj(com_google_android_gms_internal_ads_zzaws.getVersion(), 0);
            zzavh.zza(com_google_android_gms_internal_ads_zzaws.zzxz().zzxs());
            zzawq zzxs = com_google_android_gms_internal_ads_zzaws.zzxz().zzxs();
            zzaww zzxu = zzxs.zzxu();
            zzayv zza = zzavh.zza(zzxu.zzyh());
            byte[] toByteArray = com_google_android_gms_internal_ads_zzaws.zzwv().toByteArray();
            return new zzayo((ECPrivateKey) ((KeyFactory) zzayy.zzdof.zzek("EC")).generatePrivate(new ECPrivateKeySpec(new BigInteger(1, toByteArray), zzayt.zza(zza))), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
    }

    public final zzbcu zzb(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            return zzb(zzawo.zzw(com_google_android_gms_internal_ads_zzbah));
        } catch (Throwable e) {
            throw new GeneralSecurityException("invalid EciesAeadHkdf key format", e);
        }
    }

    public final zzbcu zzb(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzawo) {
            zzawo com_google_android_gms_internal_ads_zzawo = (zzawo) com_google_android_gms_internal_ads_zzbcu;
            zzavh.zza(com_google_android_gms_internal_ads_zzawo.zzxs());
            KeyPair zza = zzayt.zza(zzayt.zza(zzavh.zza(com_google_android_gms_internal_ads_zzawo.zzxs().zzxu().zzyh())));
            ECPublicKey eCPublicKey = (ECPublicKey) zza.getPublic();
            ECPrivateKey eCPrivateKey = (ECPrivateKey) zza.getPrivate();
            ECPoint w = eCPublicKey.getW();
            return zzaws.zzya().zzar(0).zzb((zzawu) zzawu.zzye().zzas(0).zzc(com_google_android_gms_internal_ads_zzawo.zzxs()).zzac(zzbah.zzo(w.getAffineX().toByteArray())).zzad(zzbah.zzo(w.getAffineY().toByteArray())).zzadi()).zzy(zzbah.zzo(eCPrivateKey.getS().toByteArray())).zzadi();
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfKeyFormat proto");
    }

    public final zzaxi zzc(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey").zzai(((zzaws) zzb(com_google_android_gms_internal_ads_zzbah)).zzaav()).zzb(zzb.ASYMMETRIC_PRIVATE).zzadi();
    }
}
