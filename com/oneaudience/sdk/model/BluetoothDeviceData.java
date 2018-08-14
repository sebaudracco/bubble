package com.oneaudience.sdk.model;

public class BluetoothDeviceData {
    boolean activeScanned;
    String address;
    int bondState;
    String name;
    int type;

    public BluetoothDeviceData(String str, String str2, int i, int i2, boolean z) {
        this.name = str;
        this.address = str2;
        this.bondState = i;
        this.type = i2;
        this.activeScanned = z;
    }

    public boolean getActiveScanned() {
        return this.activeScanned;
    }

    public String getAdrress() {
        return this.address;
    }
}
