package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzayf.zza;
import java.security.GeneralSecurityException;

public final class zzavn {
    public static final zzayf zzdht = ((zzayf) zzayf.zzaam().zzej("TINK_MAC_1_0_0").zzb(zzaub.zza("TinkMac", "Mac", "HmacKey", 0, true)).zzadi());
    private static final zzayf zzdhu = ((zzayf) ((zza) zzayf.zzaam().zza(zzdht)).zzej("TINK_MAC_1_1_0").zzadi());

    static {
        try {
            zzwk();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zzwk() throws GeneralSecurityException {
        zzauo.zza("TinkMac", new zzavm());
    }
}
