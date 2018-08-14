package com.oneaudience.sdk.model;

import java.util.ArrayList;

public class BluetoothData {
    ArrayList<BluetoothDeviceData> devices;
    int state;
    long timestamp;

    public BluetoothData(int i, long j, ArrayList<BluetoothDeviceData> arrayList) {
        this.state = i;
        this.devices = arrayList;
        this.timestamp = j;
    }
}
