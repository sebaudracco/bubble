package com.inmobi.commons.core.utilities.p117a;

import android.annotation.SuppressLint;
import android.util.Base64;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

/* compiled from: UidEncryptionUtils */
public class C3151c {
    private static final String f7771a = C3151c.class.getSimpleName();

    @SuppressLint({"TrulyRandom"})
    public static String m10378a(String str) {
        String str2 = "RSA/ECB/nopadding";
        str2 = "C10F7968CFE2C76AC6F0650C877806D4514DE58FC239592D2385BCE5609A84B2A0FBDAF29B05505EAD1FDFEF3D7209ACBF34B5D0A806DF18147EA9C0337D6B5B";
        str2 = "010001";
        str2 = "RSA";
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger("C10F7968CFE2C76AC6F0650C877806D4514DE58FC239592D2385BCE5609A84B2A0FBDAF29B05505EAD1FDFEF3D7209ACBF34B5D0A806DF18147EA9C0337D6B5B", 16), new BigInteger("010001", 16)));
            Cipher instance = Cipher.getInstance("RSA/ECB/nopadding");
            instance.init(1, rSAPublicKey);
            return new String(Base64.encode(C3151c.m10379a(str.getBytes("UTF-8"), 1, instance), 0));
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7771a, "SDK encountered unexpected error in getting encrypted UID-map; " + e.getMessage());
            return null;
        }
    }

    private static byte[] m10379a(byte[] bArr, int i, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException {
        int length;
        byte[] bArr2;
        int i2;
        byte[] bArr3 = new byte[0];
        byte[] bArr4 = new byte[0];
        if (i == 1) {
            length = bArr.length;
            bArr2 = new byte[64];
            bArr3 = bArr4;
            i2 = 0;
        } else {
            length = bArr.length;
            bArr2 = new byte[64];
            bArr3 = bArr4;
            i2 = 0;
        }
        while (i2 < length) {
            if (i2 > 0 && i2 % 64 == 0) {
                int i3;
                bArr3 = C3151c.m10380a(bArr3, cipher.doFinal(bArr2));
                if (i2 + 64 > length) {
                    i3 = length - i2;
                } else {
                    i3 = 64;
                }
                bArr2 = new byte[i3];
            }
            bArr2[i2 % 64] = bArr[i2];
            i2++;
        }
        return C3151c.m10380a(bArr3, cipher.doFinal(bArr2));
    }

    private static byte[] m10380a(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }
}
