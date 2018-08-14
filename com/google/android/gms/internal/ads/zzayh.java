package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzayh implements zzazi {
    private final SecretKeySpec zzdmu;
    private final int zzdmv;
    private final int zzdmw = ((Cipher) zzayy.zzdnz.zzek("AES/CTR/NoPadding")).getBlockSize();

    public zzayh(byte[] bArr, int i) throws GeneralSecurityException {
        this.zzdmu = new SecretKeySpec(bArr, "AES");
        if (i < 12 || i > this.zzdmw) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.zzdmv = i;
    }

    public final byte[] zzk(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length > Integer.MAX_VALUE - this.zzdmv) {
            throw new GeneralSecurityException("plaintext length can not exceed " + (Integer.MAX_VALUE - this.zzdmv));
        }
        Object obj = new byte[(this.zzdmv + bArr.length)];
        Object zzbh = zzazl.zzbh(this.zzdmv);
        System.arraycopy(zzbh, 0, obj, 0, this.zzdmv);
        int length = bArr.length;
        int i = this.zzdmv;
        Cipher cipher = (Cipher) zzayy.zzdnz.zzek("AES/CTR/NoPadding");
        Object obj2 = new byte[this.zzdmw];
        System.arraycopy(zzbh, 0, obj2, 0, this.zzdmv);
        cipher.init(1, this.zzdmu, new IvParameterSpec(obj2));
        if (cipher.doFinal(bArr, 0, length, obj, i) == length) {
            return obj;
        }
        throw new GeneralSecurityException("stored output's length does not match input's length");
    }
}
