package org.altbeacon.bluetooth;

import android.annotation.TargetApi;

public class Pdu {
    public static final byte GATT_SERVICE_UUID_PDU_TYPE = (byte) 22;
    public static final byte MANUFACTURER_DATA_PDU_TYPE = (byte) -1;
    private static final String TAG = "Pdu";
    private byte[] mBytes;
    private int mDeclaredLength;
    private int mEndIndex;
    private int mStartIndex;
    private byte mType;

    @TargetApi(9)
    public static Pdu parse(byte[] bytes, int startIndex) {
        Pdu pdu = null;
        if (bytes.length - startIndex >= 2) {
            byte length = bytes[startIndex];
            if (length > (byte) 0) {
                byte type = bytes[startIndex + 1];
                int firstIndex = startIndex + 2;
                if (firstIndex < bytes.length) {
                    pdu = new Pdu();
                    pdu.mEndIndex = startIndex + length;
                    if (pdu.mEndIndex >= bytes.length) {
                        pdu.mEndIndex = bytes.length - 1;
                    }
                    pdu.mType = type;
                    pdu.mDeclaredLength = length;
                    pdu.mStartIndex = firstIndex;
                    pdu.mBytes = bytes;
                }
            }
        }
        return pdu;
    }

    public byte getType() {
        return this.mType;
    }

    public int getDeclaredLength() {
        return this.mDeclaredLength;
    }

    public int getActualLength() {
        return (this.mEndIndex - this.mStartIndex) + 1;
    }

    public int getStartIndex() {
        return this.mStartIndex;
    }

    public int getEndIndex() {
        return this.mEndIndex;
    }
}
