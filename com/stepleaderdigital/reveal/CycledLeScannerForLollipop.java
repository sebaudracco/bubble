package com.stepleaderdigital.reveal;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.bluetooth.le.ScanSettings.Builder;
import android.content.Context;
import android.os.Handler;
import android.os.ParcelUuid;
import android.os.SystemClock;
import com.stepleaderdigital.reveal.Reveal.RevealLogger;
import java.util.ArrayList;
import java.util.List;

@TargetApi(21)
public class CycledLeScannerForLollipop extends CycledLeScanner {
    private static final long BACKGROUND_L_SCAN_DETECTION_PERIOD_MILLIS = 10000;
    private static final String TAG = "CycledLeScannerForLollipop";
    private ScanCallback leScanCallback;
    private long mBackgroundLScanFirstDetectionTime = 0;
    private long mBackgroundLScanStartTime = 0;
    private final BeaconManager mBeaconManager = BeaconManager.getInstanceForApplication(this.mContext);
    private boolean mMainScanCycleActive = false;
    private boolean mScanDeferredBefore = false;
    private BluetoothLeScanner mScanner;

    class C39931 implements Runnable {
        C39931() {
        }

        public void run() {
            CycledLeScannerForLollipop.this.scanLeDevice(Boolean.valueOf(true));
        }
    }

    class C39964 extends ScanCallback {
        C39964() {
        }

        public void onScanResult(int callbackType, ScanResult scanResult) {
            if (RevealLogger.isVerboseLoggingEnabled()) {
                RevealLogger.m12430d("got record");
                List<ParcelUuid> uuids = scanResult.getScanRecord().getServiceUuids();
                if (uuids != null) {
                    for (ParcelUuid uuid : uuids) {
                        RevealLogger.m12430d("with service uuid: " + uuid);
                    }
                }
            }
            CycledLeScannerForLollipop.this.mCycledLeScanCallback.onLeScan(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes());
            if (CycledLeScannerForLollipop.this.mBackgroundLScanStartTime > 0) {
                RevealLogger.m12440v("got a filtered scan result in the background.");
            }
        }

        public void onBatchScanResults(List<ScanResult> results) {
            RevealLogger.m12430d("got batch records");
            for (ScanResult scanResult : results) {
                CycledLeScannerForLollipop.this.mCycledLeScanCallback.onLeScan(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes());
            }
            if (CycledLeScannerForLollipop.this.mBackgroundLScanStartTime > 0) {
                RevealLogger.m12430d("got a filtered batch scan result in the background.");
            }
        }

        public void onScanFailed(int i) {
            RevealLogger.m12433e("Scan Failed");
        }
    }

    public CycledLeScannerForLollipop(Context context, long scanPeriod, long betweenScanPeriod, boolean backgroundFlag, CycledLeScanCallback cycledLeScanCallback, BluetoothCrashResolver crashResolver) {
        super(context, scanPeriod, betweenScanPeriod, backgroundFlag, cycledLeScanCallback, crashResolver);
    }

    protected void stopScan() {
        postStopLeScan();
    }

    protected boolean deferScanIfNeeded() {
        long millisecondsUntilStart = this.mNextScanCycleStartTime - SystemClock.elapsedRealtime();
        if (millisecondsUntilStart > 0) {
            this.mMainScanCycleActive = false;
            long secsSinceLastDetection = SystemClock.elapsedRealtime() - DetectionTracker.getInstance().getLastDetectionTime();
            if (!this.mScanDeferredBefore) {
                if (secsSinceLastDetection > 10000) {
                    this.mBackgroundLScanStartTime = SystemClock.elapsedRealtime();
                    this.mBackgroundLScanFirstDetectionTime = 0;
                    RevealLogger.m12430d("This is Android L. Doing a filtered scan for the background.");
                    startScan();
                } else {
                    RevealLogger.m12431d("This is Android L, but we last saw a beacon only %s ago, so we will not keep scanning in background.", Long.valueOf(secsSinceLastDetection));
                }
            }
            if (this.mBackgroundLScanStartTime > 0 && DetectionTracker.getInstance().getLastDetectionTime() > this.mBackgroundLScanStartTime) {
                if (this.mBackgroundLScanFirstDetectionTime == 0) {
                    this.mBackgroundLScanFirstDetectionTime = DetectionTracker.getInstance().getLastDetectionTime();
                }
                if (SystemClock.elapsedRealtime() - this.mBackgroundLScanFirstDetectionTime >= 10000) {
                    RevealLogger.m12430d("We've been detecting for a bit.  Stopping Android L background scanning");
                    stopScan();
                    this.mBackgroundLScanStartTime = 0;
                } else {
                    RevealLogger.m12430d("Delivering Android L background scanning results");
                    this.mCycledLeScanCallback.onCycleEnd();
                }
            }
            RevealLogger.m12440v("Waiting to start full Bluetooth scan for another " + millisecondsUntilStart + " milliseconds " + millisecondsUntilStart);
            if (!this.mScanDeferredBefore && this.mBackgroundFlag) {
                setWakeUpAlarm();
            }
            Handler handler = this.mHandler;
            Runnable c39931 = new C39931();
            if (millisecondsUntilStart > 1000) {
                millisecondsUntilStart = 1000;
            }
            handler.postDelayed(c39931, millisecondsUntilStart);
            this.mScanDeferredBefore = true;
            return true;
        }
        if (this.mBackgroundLScanStartTime > 0) {
            stopScan();
            this.mBackgroundLScanStartTime = 0;
        }
        this.mScanDeferredBefore = false;
        this.mMainScanCycleActive = true;
        return false;
    }

    protected void startScan() {
        if (isBluetoothOn()) {
            ScanSettings settings;
            List<ScanFilter> filters = new ArrayList();
            if (!this.mBackgroundFlag || this.mMainScanCycleActive) {
                RevealLogger.m12430d("starting non-filtered scan in SCAN_MODE_LOW_LATENCY");
                settings = new Builder().setScanMode(2).build();
            } else {
                RevealLogger.m12430d("starting filtered scan in SCAN_MODE_LOW_POWER");
                settings = new Builder().setScanMode(0).build();
            }
            postStartLeScan(filters, settings);
            return;
        }
        RevealLogger.m12430d("Not starting scan because bluetooth is off");
    }

    protected void finishScan() {
        RevealLogger.m12430d("Stopping scan");
        stopScan();
        this.mScanningPaused = true;
    }

    private void postStartLeScan(List<ScanFilter> filters, ScanSettings settings) {
        final BluetoothLeScanner scanner = getScanner();
        if (scanner != null) {
            final ScanCallback scanCallback = getNewLeScanCallback();
            this.mScanHandler.removeCallbacksAndMessages(null);
            final List<ScanFilter> list = filters;
            final ScanSettings scanSettings = settings;
            this.mScanHandler.post(new Runnable() {
                public void run() {
                    try {
                        scanner.startScan(list, scanSettings, scanCallback);
                    } catch (IllegalStateException e) {
                        RevealLogger.m12441w("Cannot start scan. Bluetooth may be turned off.");
                    } catch (NullPointerException npe) {
                        RevealLogger.m12434e("Cannot start scan. Unexpected NPE.", npe);
                    } catch (SecurityException e2) {
                        RevealLogger.m12433e("Cannot start scan.  Security Exception");
                    }
                }
            });
        }
    }

    private void postStopLeScan() {
        if (isBluetoothOn()) {
            final BluetoothLeScanner scanner = getScanner();
            if (scanner != null) {
                final ScanCallback scanCallback = getNewLeScanCallback();
                this.mScanHandler.removeCallbacksAndMessages(null);
                this.mScanHandler.post(new Runnable() {
                    public void run() {
                        try {
                            RevealLogger.m12430d("Stopping LE scan on scan handler");
                            scanner.stopScan(scanCallback);
                        } catch (IllegalStateException e) {
                            RevealLogger.m12441w("Cannot stop scan. Bluetooth may be turned off.");
                        } catch (NullPointerException npe) {
                            RevealLogger.m12434e("Cannot stop scan. Unexpected NPE.", npe);
                        } catch (SecurityException e2) {
                            RevealLogger.m12433e("Cannot stop scan.  Security Exception");
                        }
                    }
                });
                return;
            }
            return;
        }
        RevealLogger.m12430d("Not stopping scan because bluetooth is off");
    }

    private boolean isBluetoothOn() {
        try {
            BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
            if (bluetoothAdapter == null) {
                RevealLogger.m12441w("Cannot get bluetooth adapter");
                return false;
            } else if (bluetoothAdapter.getState() == 12) {
                return true;
            } else {
                return false;
            }
        } catch (SecurityException e) {
            RevealLogger.m12441w("SecurityException checking if bluetooth is on");
            return false;
        }
    }

    private BluetoothLeScanner getScanner() {
        try {
            if (this.mScanner == null) {
                RevealLogger.m12430d("Making new Android L scanner");
                if (getBluetoothAdapter() != null) {
                    this.mScanner = getBluetoothAdapter().getBluetoothLeScanner();
                }
                if (this.mScanner == null) {
                    RevealLogger.m12441w("Failed to make new Android L scanner");
                }
            }
        } catch (SecurityException e) {
            RevealLogger.m12441w("SecurityException making new Android L scanner");
        }
        return this.mScanner;
    }

    private ScanCallback getNewLeScanCallback() {
        if (this.leScanCallback == null) {
            this.leScanCallback = new C39964();
        }
        return this.leScanCallback;
    }
}
