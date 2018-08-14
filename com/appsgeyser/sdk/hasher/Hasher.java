package com.appsgeyser.sdk.hasher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class Hasher {
    public static String md5(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(aMessageDigest & 255);
                while (h.length() < 2) {
                    h = SchemaSymbols.ATTVAL_FALSE_0 + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
