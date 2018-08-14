package org.altbeacon.beacon.service.scanner;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.MainThread;

@MainThread
public interface CycledLeScanCallback {
    void onCycleEnd();

    void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
}
