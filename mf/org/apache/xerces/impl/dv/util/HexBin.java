package mf.org.apache.xerces.impl.dv.util;

public final class HexBin {
    private static final int BASELENGTH = 128;
    private static final int LOOKUPLENGTH = 16;
    private static final byte[] hexNumberTable = new byte[128];
    private static final char[] lookUpHexAlphabet = new char[16];

    static {
        int i;
        for (i = 0; i < 128; i++) {
            hexNumberTable[i] = (byte) -1;
        }
        for (i = 57; i >= 48; i--) {
            hexNumberTable[i] = (byte) (i - 48);
        }
        for (i = 70; i >= 65; i--) {
            hexNumberTable[i] = (byte) ((i - 65) + 10);
        }
        for (i = 102; i >= 97; i--) {
            hexNumberTable[i] = (byte) ((i - 97) + 10);
        }
        for (i = 0; i < 10; i++) {
            lookUpHexAlphabet[i] = (char) (i + 48);
        }
        for (i = 10; i <= 15; i++) {
            lookUpHexAlphabet[i] = (char) ((i + 65) - 10);
        }
    }

    public static String encode(byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        int lengthData = binaryData.length;
        char[] encodedData = new char[(lengthData * 2)];
        for (int i = 0; i < lengthData; i++) {
            int temp = binaryData[i];
            if (temp < 0) {
                temp += 256;
            }
            encodedData[i * 2] = lookUpHexAlphabet[temp >> 4];
            encodedData[(i * 2) + 1] = lookUpHexAlphabet[temp & 15];
        }
        return new String(encodedData);
    }

    public static byte[] decode(String encoded) {
        if (encoded == null) {
            return null;
        }
        int lengthData = encoded.length();
        if (lengthData % 2 != 0) {
            return null;
        }
        char[] binaryData = encoded.toCharArray();
        int lengthDecode = lengthData / 2;
        byte[] decodedData = new byte[lengthDecode];
        for (int i = 0; i < lengthDecode; i++) {
            byte temp1;
            char tempChar = binaryData[i * 2];
            if (tempChar < '') {
                temp1 = hexNumberTable[tempChar];
            } else {
                temp1 = (byte) -1;
            }
            if (temp1 == (byte) -1) {
                return null;
            }
            byte temp2;
            tempChar = binaryData[(i * 2) + 1];
            if (tempChar < '') {
                temp2 = hexNumberTable[tempChar];
            } else {
                temp2 = (byte) -1;
            }
            if (temp2 == (byte) -1) {
                return null;
            }
            decodedData[i] = (byte) ((temp1 << 4) | temp2);
        }
        return decodedData;
    }
}
