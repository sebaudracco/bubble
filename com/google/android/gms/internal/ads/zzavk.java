package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

final class zzavk implements zzaug<zzauk> {
    zzavk() {
    }

    private static void zza(zzaxg com_google_android_gms_internal_ads_zzaxg) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzaxg.zzyt() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        switch (zzavl.zzdhz[com_google_android_gms_internal_ads_zzaxg.zzys().ordinal()]) {
            case 1:
                if (com_google_android_gms_internal_ads_zzaxg.zzyt() > 20) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            case 2:
                if (com_google_android_gms_internal_ads_zzaxg.zzyt() > 32) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            case 3:
                if (com_google_android_gms_internal_ads_zzaxg.zzyt() > 64) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            default:
                throw new GeneralSecurityException("unknown hash type");
        }
    }

    private final zzauk zzh(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            zzaxc zzae = zzaxc.zzae(com_google_android_gms_internal_ads_zzbah);
            if (zzae instanceof zzaxc) {
                zzae = zzae;
                zzazq.zzj(zzae.getVersion(), 0);
                if (zzae.zzwv().size() < 16) {
                    throw new GeneralSecurityException("key too short");
                }
                zzazj com_google_android_gms_internal_ads_zzazj;
                zza(zzae.zzym());
                zzaxa zzys = zzae.zzym().zzys();
                Key secretKeySpec = new SecretKeySpec(zzae.zzwv().toByteArray(), "HMAC");
                int zzyt = zzae.zzym().zzyt();
                switch (zzavl.zzdhz[zzys.ordinal()]) {
                    case 1:
                        com_google_android_gms_internal_ads_zzazj = new zzazj("HMACSHA1", secretKeySpec, zzyt);
                        break;
                    case 2:
                        com_google_android_gms_internal_ads_zzazj = new zzazj("HMACSHA256", secretKeySpec, zzyt);
                        break;
                    case 3:
                        com_google_android_gms_internal_ads_zzazj = new zzazj("HMACSHA512", secretKeySpec, zzyt);
                        break;
                    default:
                        throw new GeneralSecurityException("unknown hash");
                }
                return com_google_android_gms_internal_ads_zzazj;
            }
            throw new GeneralSecurityException("expected HmacKey proto");
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized HmacKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return zzh(com_google_android_gms_internal_ads_zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzaxc) {
            zzaxc com_google_android_gms_internal_ads_zzaxc = (zzaxc) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzj(com_google_android_gms_internal_ads_zzaxc.getVersion(), 0);
            if (com_google_android_gms_internal_ads_zzaxc.zzwv().size() < 16) {
                throw new GeneralSecurityException("key too short");
            }
            zza(com_google_android_gms_internal_ads_zzaxc.zzym());
            zzaxa zzys = com_google_android_gms_internal_ads_zzaxc.zzym().zzys();
            Key secretKeySpec = new SecretKeySpec(com_google_android_gms_internal_ads_zzaxc.zzwv().toByteArray(), "HMAC");
            int zzyt = com_google_android_gms_internal_ads_zzaxc.zzym().zzyt();
            switch (zzavl.zzdhz[zzys.ordinal()]) {
                case 1:
                    return new zzazj("HMACSHA1", secretKeySpec, zzyt);
                case 2:
                    return new zzazj("HMACSHA256", secretKeySpec, zzyt);
                case 3:
                    return new zzazj("HMACSHA512", secretKeySpec, zzyt);
                default:
                    throw new GeneralSecurityException("unknown hash");
            }
        }
        throw new GeneralSecurityException("expected HmacKey proto");
    }

    public final zzbcu zzb(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            return zzb(zzaxe.zzag(com_google_android_gms_internal_ads_zzbah));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized HmacKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzaxe) {
            zzaxe com_google_android_gms_internal_ads_zzaxe = (zzaxe) com_google_android_gms_internal_ads_zzbcu;
            if (com_google_android_gms_internal_ads_zzaxe.getKeySize() < 16) {
                throw new GeneralSecurityException("key too short");
            }
            zza(com_google_android_gms_internal_ads_zzaxe.zzym());
            return zzaxc.zzyn().zzav(0).zzc(com_google_android_gms_internal_ads_zzaxe.zzym()).zzaf(zzbah.zzo(zzazl.zzbh(com_google_android_gms_internal_ads_zzaxe.getKeySize()))).zzadi();
        }
        throw new GeneralSecurityException("expected HmacKeyFormat proto");
    }

    public final zzaxi zzc(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.HmacKey").zzai(((zzaxc) zzb(com_google_android_gms_internal_ads_zzbah)).zzaav()).zzb(zzb.SYMMETRIC).zzadi();
    }
}
