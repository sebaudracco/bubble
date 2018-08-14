package com.stepleaderdigital.reveal;

import android.bluetooth.BluetoothDevice;

public interface BeaconLeScanCallback {
    void onBeaconLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
}
