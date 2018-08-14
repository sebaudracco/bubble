package org.altbeacon.beacon.service.scanner;

import android.annotation.TargetApi;
import android.content.Context;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

@TargetApi(26)
class CycledLeScannerForAndroidO extends CycledLeScannerForLollipop {
    private static final String TAG = CycledLeScannerForAndroidO.class.getSimpleName();

    CycledLeScannerForAndroidO(Context context, long scanPeriod, long betweenScanPeriod, boolean backgroundFlag, CycledLeScanCallback cycledLeScanCallback, BluetoothCrashResolver crashResolver) {
        super(context, scanPeriod, betweenScanPeriod, backgroundFlag, cycledLeScanCallback, crashResolver);
    }
}
