package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzayj implements zzatz {
    private final SecretKey zzdna;

    public zzayj(byte[] bArr) {
        this.zzdna = new SecretKeySpec(bArr, "AES");
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        Object obj = new byte[((bArr.length + 12) + 16)];
        Object zzbh = zzazl.zzbh(12);
        System.arraycopy(zzbh, 0, obj, 0, 12);
        Cipher cipher = (Cipher) zzayy.zzdnz.zzek("AES/GCM/NoPadding");
        cipher.init(1, this.zzdna, new GCMParameterSpec(128, zzbh));
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        cipher.updateAAD(bArr2);
        cipher.doFinal(bArr, 0, bArr.length, obj, 12);
        return obj;
    }
}
