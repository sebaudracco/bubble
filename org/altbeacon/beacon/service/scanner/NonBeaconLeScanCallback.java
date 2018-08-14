package org.altbeacon.beacon.service.scanner;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.WorkerThread;

@WorkerThread
public interface NonBeaconLeScanCallback {
    void onNonBeaconLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
}
