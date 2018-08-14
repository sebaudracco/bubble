package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzayi implements zzatz {
    private final SecretKeySpec zzdmu;
    private final byte[] zzdmx;
    private final byte[] zzdmy;
    private final int zzdmz;

    public zzayi(byte[] bArr, int i) throws GeneralSecurityException {
        if (i == 12 || i == 16) {
            this.zzdmz = i;
            this.zzdmu = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/NOPADDING");
            instance.init(1, this.zzdmu);
            this.zzdmx = zzl(instance.doFinal(new byte[16]));
            this.zzdmy = zzl(this.zzdmx);
            return;
        }
        throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
    }

    private final byte[] zza(Cipher cipher, int i, byte[] bArr, int i2, int i3) throws IllegalBlockSizeException, BadPaddingException {
        int i4 = 0;
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) i;
        if (i3 == 0) {
            return cipher.doFinal(zzd(bArr2, this.zzdmx));
        }
        byte[] zzd;
        int i5 = 0;
        byte[] doFinal = cipher.doFinal(bArr2);
        while (i3 - i5 > 16) {
            for (int i6 = 0; i6 < 16; i6++) {
                doFinal[i6] = (byte) (doFinal[i6] ^ bArr[(i2 + i5) + i6]);
            }
            doFinal = cipher.doFinal(doFinal);
            i5 += 16;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i2 + i5, i2 + i3);
        if (copyOfRange.length == 16) {
            zzd = zzd(copyOfRange, this.zzdmx);
        } else {
            bArr2 = Arrays.copyOf(this.zzdmy, 16);
            while (i4 < copyOfRange.length) {
                bArr2[i4] = (byte) (bArr2[i4] ^ copyOfRange[i4]);
                i4++;
            }
            bArr2[copyOfRange.length] = (byte) (bArr2[copyOfRange.length] ^ 128);
            zzd = bArr2;
        }
        return cipher.doFinal(zzd(doFinal, zzd));
    }

    private static byte[] zzd(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    private static byte[] zzl(byte[] bArr) {
        int i;
        int i2 = 0;
        byte[] bArr2 = new byte[16];
        for (i = 0; i < 15; i++) {
            bArr2[i] = (byte) ((bArr[i] << 1) ^ ((bArr[i + 1] & 255) >>> 7));
        }
        i = bArr[15] << 1;
        if ((bArr[0] & 128) != 0) {
            i2 = 135;
        }
        bArr2[15] = (byte) (i2 ^ i);
        return bArr2;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int i = 0;
        if (bArr.length > (Integer.MAX_VALUE - this.zzdmz) - 16) {
            throw new GeneralSecurityException("plaintext too long");
        }
        Object obj = new byte[((this.zzdmz + bArr.length) + 16)];
        Object zzbh = zzazl.zzbh(this.zzdmz);
        System.arraycopy(zzbh, 0, obj, 0, this.zzdmz);
        Cipher instance = Cipher.getInstance("AES/ECB/NOPADDING");
        instance.init(1, this.zzdmu);
        byte[] zza = zza(instance, 0, zzbh, 0, zzbh.length);
        byte[] bArr3 = bArr2 == null ? new byte[0] : bArr2;
        byte[] zza2 = zza(instance, 1, bArr3, 0, bArr3.length);
        Cipher instance2 = Cipher.getInstance("AES/CTR/NOPADDING");
        instance2.init(1, this.zzdmu, new IvParameterSpec(zza));
        instance2.doFinal(bArr, 0, bArr.length, obj, this.zzdmz);
        byte[] zza3 = zza(instance, 2, obj, this.zzdmz, bArr.length);
        int length = bArr.length + this.zzdmz;
        while (i < 16) {
            obj[length + i] = (byte) ((zza2[i] ^ zza[i]) ^ zza3[i]);
            i++;
        }
        return obj;
    }
}
