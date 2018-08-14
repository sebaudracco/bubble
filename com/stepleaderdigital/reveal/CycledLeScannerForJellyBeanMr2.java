package com.stepleaderdigital.reveal;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import com.stepleaderdigital.reveal.Reveal.RevealLogger;

@TargetApi(18)
public class CycledLeScannerForJellyBeanMr2 extends CycledLeScanner {
    private static final String TAG = "CycledLeScannerForJellyBeanMr2";
    private LeScanCallback leScanCallback;

    class C39891 implements Runnable {
        C39891() {
        }

        public void run() {
            CycledLeScannerForJellyBeanMr2.this.scanLeDevice(Boolean.valueOf(true));
        }
    }

    class C39924 implements LeScanCallback {
        C39924() {
        }

        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            RevealLogger.m12430d("got record");
            CycledLeScannerForJellyBeanMr2.this.mCycledLeScanCallback.onLeScan(device, rssi, scanRecord);
            CycledLeScannerForJellyBeanMr2.this.mBluetoothCrashResolver.notifyScannedDevice(device, CycledLeScannerForJellyBeanMr2.this.getLeScanCallback());
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
        RevealLogger.m12430d("Waiting to start next Bluetooth scan for another " + millisecondsUntilStart + " milliseconds");
        if (this.mBackgroundFlag) {
            setWakeUpAlarm();
        }
        Handler handler = this.mHandler;
        Runnable c39891 = new C39891();
        if (millisecondsUntilStart > 1000) {
            millisecondsUntilStart = 1000;
        }
        handler.postDelayed(c39891, millisecondsUntilStart);
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
                public void run() {
                    try {
                        bluetoothAdapter.startLeScan(leScanCallback);
                    } catch (Exception e) {
                        RevealLogger.m12433e("Internal Android exception in startLeScan()");
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
                public void run() {
                    try {
                        bluetoothAdapter.stopLeScan(leScanCallback);
                    } catch (Exception e) {
                        RevealLogger.m12433e("Internal Android exception in stopLeScan()");
                    }
                }
            });
        }
    }

    private LeScanCallback getLeScanCallback() {
        if (this.leScanCallback == null) {
            this.leScanCallback = new C39924();
        }
        return this.leScanCallback;
    }
}
