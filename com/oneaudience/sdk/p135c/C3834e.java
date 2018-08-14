package com.oneaudience.sdk.p135c;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class C3834e {
    public static Object m12259a(String str) {
        Object obj = null;
        if (!(str == null || str.length() == 0)) {
            try {
                obj = new ObjectInputStream(new ByteArrayInputStream(C3834e.m12262b(str))).readObject();
            } catch (Exception e) {
            }
        }
        return obj;
    }

    public static String m12260a(Serializable serializable) {
        if (serializable == null) {
            return "";
        }
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
            return C3834e.m12261a(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }

    public static String m12261a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append((char) (((bArr[i] >> 4) & 15) + 97));
            stringBuffer.append((char) ((bArr[i] & 15) + 97));
        }
        return stringBuffer.toString();
    }

    public static byte[] m12262b(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length(); i += 2) {
            bArr[i / 2] = (byte) ((str.charAt(i) - 97) << 4);
            int i2 = i / 2;
            bArr[i2] = (byte) ((str.charAt(i + 1) - 97) + bArr[i2]);
        }
        return bArr;
    }
}
