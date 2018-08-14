package org.altbeacon.beacon.service.scanner;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

@TargetApi(18)
public class CycledLeScannerForJellyBeanMr2 extends CycledLeScanner {
    private static final String TAG = "CycledLeScannerForJellyBeanMr2";
    private LeScanCallback leScanCallback;

    class C47961 implements Runnable {
        C47961() {
        }

        @MainThread
        public void run() {
            CycledLeScannerForJellyBeanMr2.this.scanLeDevice(Boolean.valueOf(true));
        }
    }

    class C47994 implements LeScanCallback {
        C47994() {
        }

        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            LogManager.m16371d(CycledLeScannerForJellyBeanMr2.TAG, "got record", new Object[0]);
            CycledLeScannerForJellyBeanMr2.this.mCycledLeScanCallback.onLeScan(device, rssi, scanRecord);
            if (CycledLeScannerForJellyBeanMr2.this.mBluetoothCrashResolver != null) {
                CycledLeScannerForJellyBeanMr2.this.mBluetoothCrashResolver.notifyScannedDevice(device, CycledLeScannerForJellyBeanMr2.this.getLeScanCallback());
            }
        }
    }

    public CycledLeScannerForJellyBeanMr2(Context context, long scanPeriod, long betweenScanPeriod, boolean backgroundFlag, CycledLeScanCallback cycledLeScanCallback, BluetoothCrashResolver crashResolver) {
        super(context, scanPeriod, betweenScanPeriod, backgroundFlag, cycledLeScanCallback, crashResolver);
    }

    protected void stopScan() {
        postStopLeScan();
    }

    protected boolean deferScanIfNeeded() {
        long millisecondsUntilStart = this.mNextScanCycleStartTime - SystemClock.elapsedRealtime();
        if (millisecondsUntilStart <= 0) {
            return false;
        }
        LogManager.m16371d(TAG, "Waiting to start next Bluetooth scan for another %s milliseconds", Long.valueOf(millisecondsUntilStart));
        if (this.mBackgroundFlag) {
            setWakeUpAlarm();
        }
        Handler handler = this.mHandler;
        Runnable c47961 = new C47961();
        if (millisecondsUntilStart > 1000) {
            millisecondsUntilStart = 1000;
        }
        handler.postDelayed(c47961, millisecondsUntilStart);
        return true;
    }

    protected void startScan() {
        postStartLeScan();
    }

    protected void finishScan() {
        postStopLeScan();
        this.mScanningPaused = true;
    }

    private void postStartLeScan() {
        final BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
        if (bluetoothAdapter != null) {
            final LeScanCallback leScanCallback = getLeScanCallback();
            this.mScanHandler.removeCallbacksAndMessages(null);
            this.mScanHandler.post(new Runnable() {
                @WorkerThread
                public void run() {
                    try {
                        bluetoothAdapter.startLeScan(leScanCallback);
                    } catch (Exception e) {
                        LogManager.m16374e(e, CycledLeScannerForJellyBeanMr2.TAG, "Internal Android exception in startLeScan()", new Object[0]);
                    }
                }
            });
        }
    }

    private void postStopLeScan() {
        final BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
        if (bluetoothAdapter != null) {
            final LeScanCallback leScanCallback = getLeScanCallback();
            this.mScanHandler.removeCallbacksAndMessages(null);
            this.mScanHandler.post(new Runnable() {
                @WorkerThread
                public void run() {
                    try {
                        bluetoothAdapter.stopLeScan(leScanCallback);
                    } catch (Exception e) {
                        LogManager.m16374e(e, CycledLeScannerForJellyBeanMr2.TAG, "Internal Android exception in stopLeScan()", new Object[0]);
                    }
                }
            });
        }
    }

    private LeScanCallback getLeScanCallback() {
        if (this.leScanCallback == null) {
            this.leScanCallback = new C47994();
        }
        return this.leScanCallback;
    }
}
