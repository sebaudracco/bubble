package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class zzayx implements zzatz {
    private final zzazi zzdnv;
    private final zzauk zzdnw;
    private final int zzdnx;

    public zzayx(zzazi com_google_android_gms_internal_ads_zzazi, zzauk com_google_android_gms_internal_ads_zzauk, int i) {
        this.zzdnv = com_google_android_gms_internal_ads_zzazi;
        this.zzdnw = com_google_android_gms_internal_ads_zzauk;
        this.zzdnx = i;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zzk = this.zzdnv.zzk(bArr);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        byte[] copyOf = Arrays.copyOf(ByteBuffer.allocate(8).putLong(8 * ((long) bArr2.length)).array(), 8);
        copyOf = this.zzdnw.zzg(zzayk.zza(bArr2, zzk, copyOf));
        return zzayk.zza(zzk, copyOf);
    }
}
