package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzavb implements zzaug<zzauf> {
    zzavb() {
    }

    private final zzauf zzg(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        try {
            zzawu zzab = zzawu.zzab(com_google_android_gms_internal_ads_zzbah);
            if (zzab instanceof zzawu) {
                zzab = zzab;
                zzazq.zzj(zzab.getVersion(), 0);
                zzavh.zza(zzab.zzxs());
                zzawq zzxs = zzab.zzxs();
                zzaww zzxu = zzxs.zzxu();
                return new zzayp(zzayt.zza(zzavh.zza(zzxu.zzyh()), zzab.zzyc().toByteArray(), zzab.zzyd().toByteArray()), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
            }
            throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPublicKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return zzg(com_google_android_gms_internal_ads_zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        if (com_google_android_gms_internal_ads_zzbcu instanceof zzawu) {
            zzawu com_google_android_gms_internal_ads_zzawu = (zzawu) com_google_android_gms_internal_ads_zzbcu;
            zzazq.zzj(com_google_android_gms_internal_ads_zzawu.getVersion(), 0);
            zzavh.zza(com_google_android_gms_internal_ads_zzawu.zzxs());
            zzawq zzxs = com_google_android_gms_internal_ads_zzawu.zzxs();
            zzaww zzxu = zzxs.zzxu();
            return new zzayp(zzayt.zza(zzavh.zza(zzxu.zzyh()), com_google_android_gms_internal_ads_zzawu.zzyc().toByteArray(), com_google_android_gms_internal_ads_zzawu.zzyd().toByteArray()), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
    }

    public final zzbcu zzb(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final zzbcu zzb(zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final zzaxi zzc(zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }
}
