package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;

public final class zzayo implements zzaue {
    private static final byte[] zzdhv = new byte[0];
    private final ECPrivateKey zzdnc;
    private final zzayq zzdnd;
    private final String zzdne;
    private final byte[] zzdnf;
    private final zzayw zzdng;
    private final zzayn zzdnh;

    public zzayo(ECPrivateKey eCPrivateKey, byte[] bArr, String str, zzayw com_google_android_gms_internal_ads_zzayw, zzayn com_google_android_gms_internal_ads_zzayn) throws GeneralSecurityException {
        this.zzdnc = eCPrivateKey;
        this.zzdnd = new zzayq(eCPrivateKey);
        this.zzdnf = bArr;
        this.zzdne = str;
        this.zzdng = com_google_android_gms_internal_ads_zzayw;
        this.zzdnh = com_google_android_gms_internal_ads_zzayn;
    }
}
