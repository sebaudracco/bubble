package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class zzauy implements zzatz {
    private static final byte[] zzdhv = new byte[0];
    private final zzaxn zzdhw;
    private final zzatz zzdhx;

    public zzauy(zzaxn com_google_android_gms_internal_ads_zzaxn, zzatz com_google_android_gms_internal_ads_zzatz) {
        this.zzdhw = com_google_android_gms_internal_ads_zzaxn;
        this.zzdhx = com_google_android_gms_internal_ads_zzatz;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] toByteArray = zzauo.zzb(this.zzdhw).toByteArray();
        byte[] zzc = this.zzdhx.zzc(toByteArray, zzdhv);
        toByteArray = ((zzatz) zzauo.zza(this.zzdhw.zzyw(), toByteArray)).zzc(bArr, bArr2);
        return ByteBuffer.allocate((zzc.length + 4) + toByteArray.length).putInt(zzc.length).put(zzc).put(toByteArray).array();
    }
}
