package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

public final class zzayp implements zzauf {
    private static final byte[] zzdhv = new byte[0];
    private final String zzdne;
    private final byte[] zzdnf;
    private final zzayw zzdng;
    private final zzayn zzdnh;
    private final zzayr zzdni;

    public zzayp(ECPublicKey eCPublicKey, byte[] bArr, String str, zzayw com_google_android_gms_internal_ads_zzayw, zzayn com_google_android_gms_internal_ads_zzayn) throws GeneralSecurityException {
        zzayt.zza(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
        this.zzdni = new zzayr(eCPublicKey);
        this.zzdnf = bArr;
        this.zzdne = str;
        this.zzdng = com_google_android_gms_internal_ads_zzayw;
        this.zzdnh = com_google_android_gms_internal_ads_zzayn;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzays zza = this.zzdni.zza(this.zzdne, this.zzdnf, bArr2, this.zzdnh.zzwm(), this.zzdng);
        byte[] zzc = this.zzdnh.zzi(zza.zzaaq()).zzc(bArr, zzdhv);
        byte[] zzaap = zza.zzaap();
        return ByteBuffer.allocate(zzaap.length + zzc.length).put(zzaap).put(zzc).array();
    }
}
