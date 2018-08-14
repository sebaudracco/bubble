package com.adcolony.sdk;

import com.google.android.gms.common.util.AndroidUtilsLight;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ADCGeneratedCrypto {
    private static final byte[] f322a = new byte[]{(byte) -45, (byte) -6, (byte) -74, (byte) 58, (byte) -77, (byte) 8, (byte) 91, Byte.MIN_VALUE, (byte) -97, (byte) 10, (byte) 95, (byte) -80, (byte) 60, (byte) 27, (byte) -69, (byte) -13, (byte) -11, (byte) -25, (byte) 110, (byte) -57, (byte) -9, (byte) -38, (byte) -122, (byte) -36, (byte) 101, (byte) -119, (byte) 41, (byte) -86, (byte) 47, (byte) 85, (byte) 125, (byte) -75};
    private static final byte[] f323b = new byte[]{(byte) 40, (byte) -40, (byte) -46, (byte) -26, (byte) -42, (byte) -70, (byte) 119, (byte) 81, (byte) -118, (byte) 58, (byte) 66, (byte) 116, (byte) -102, (byte) 59, (byte) -43, (byte) -125, (byte) 14, (byte) 23, (byte) -51, (byte) 112, (byte) -8, (byte) -112, (byte) -124, (byte) -89};

    private native byte[] nativeDecryptBase64(byte[] bArr, byte[] bArr2, byte[] bArr3, int i);

    public static byte[] decrypt(byte[] base64Buffer) {
        byte[] bArr = null;
        int i = 0;
        try {
            ADCGeneratedCrypto aDCGeneratedCrypto = new ADCGeneratedCrypto();
            byte[] digest = MessageDigest.getInstance(AndroidUtilsLight.DIGEST_ALGORITHM_SHA512).digest(aDCGeneratedCrypto.getClass().getSimpleName().getBytes());
            byte[] bArr2 = new byte[f322a.length];
            byte[] bArr3 = new byte[f323b.length];
            for (int i2 = 0; i2 < bArr2.length; i2++) {
                bArr2[i2] = (byte) (f322a[i2] ^ digest[i2]);
            }
            while (i < bArr3.length) {
                bArr3[i] = (byte) (f323b[i] ^ digest[i]);
                i++;
            }
            if (new String(base64Buffer, 0, 10).equals("adc_base64")) {
                byte[] copyOfRange = Arrays.copyOfRange(base64Buffer, 10, base64Buffer.length);
                bArr = aDCGeneratedCrypto.nativeDecryptBase64(bArr2, bArr3, copyOfRange, copyOfRange.length);
            }
        } catch (NoSuchAlgorithmException e) {
        }
        return bArr;
    }
}
