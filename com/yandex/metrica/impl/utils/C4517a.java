package com.yandex.metrica.impl.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class C4517a {
    private final String f12571a;
    private final byte[] f12572b;
    private final byte[] f12573c;

    public C4517a(String str, byte[] bArr, byte[] bArr2) {
        this.f12571a = str;
        this.f12572b = bArr;
        this.f12573c = bArr2;
    }

    public byte[] m16235a(byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key secretKeySpec = new SecretKeySpec(this.f12572b, "AES");
        Cipher instance = Cipher.getInstance(this.f12571a);
        instance.init(1, secretKeySpec, new IvParameterSpec(this.f12573c));
        return instance.doFinal(bArr);
    }
}
