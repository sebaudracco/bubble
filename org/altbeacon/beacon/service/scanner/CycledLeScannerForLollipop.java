package org.altbeacon.beacon.service.scanner;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.bluetooth.le.ScanSettings.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.ParcelUuid;
import android.os.SystemClock;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import android.support.v4.content.LocalBroadcastManager;
import java.util.ArrayList;
import java.util.List;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.service.DetectionTracker;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

@TargetApi(21)
public class CycledLeScannerForLollipop extends CycledLeScanner {
    private static final long BACKGROUND_L_SCAN_DETECTION_PERIOD_MILLIS = 10000;
    private static final String TAG = "CycledLeScannerForLollipop";
    private ScanCallback leScanCallback;
    private long mBackgroundLScanFirstDetectionTime = 0;
    private long mBackgroundLScanStartTime = 0;
    private final BeaconManager mBeaconManager = BeaconManager.getInstanceForApplication(this.mContext);
    private boolean mMainScanCycleActive = false;
    private BluetoothLeScanner mScanner;

    class C48001 implements Runnable {
        C48001() {
        }

        @MainThread
        public void run() {
            CycledLeScannerForLollipop.this.scanLeDevice(Boolean.valueOf(true));
        }
    }

    class C48034 extends ScanCallback {
        C48034() {
        }

        @MainThread
        public void onScanResult(int callbackType, ScanResult scanResult) {
            if (LogManager.isVerboseLoggingEnabled()) {
                LogManager.m16371d(CycledLeScannerForLollipop.TAG, "got record", new Object[0]);
                List<ParcelUuid> uuids = scanResult.getScanRecord().getServiceUuids();
                if (uuids != null) {
                    for (ParcelUuid uuid : uuids) {
                        LogManager.m16371d(CycledLeScannerForLollipop.TAG, "with service uuid: " + uuid, new Object[0]);
                    }
                }
            }
            CycledLeScannerForLollipop.this.mCycledLeScanCallback.onLeScan(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes());
            if (CycledLeScannerForLollipop.this.mBackgroundLScanStartTime > 0) {
                LogManager.m16371d(CycledLeScannerForLollipop.TAG, "got a filtered scan result in the background.", new Object[0]);
            }
        }

        @MainThread
        public void onBatchScanResults(List<ScanResult> results) {
            LogManager.m16371d(CycledLeScannerForLollipop.TAG, "got batch records", new Object[0]);
            for (ScanResult scanResult : results) {
                CycledLeScannerForLollipop.this.mCycledLeScanCallback.onLeScan(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes());
            }
            if (CycledLeScannerForLollipop.this.mBackgroundLScanStartTime > 0) {
                LogManager.m16371d(CycledLeScannerForLollipop.TAG, "got a filtered batch scan result in the background.", new Object[0]);
            }
        }

        @MainThread
        public void onScanFailed(int errorCode) {
            Intent intent = new Intent("onScanFailed");
            intent.putExtra("errorCode", errorCode);
            LocalBroadcastManager.getInstance(CycledLeScannerForLollipop.this.mContext).sendBroadcast(intent);
            switch (errorCode) {
                case 1:
                    LogManager.m16373e(CycledLeScannerForLollipop.TAG, "Scan failed: a BLE scan with the same settings is already started by the app", new Object[0]);
                    return;
                case 2:
                    LogManager.m16373e(CycledLeScannerForLollipop.TAG, "Scan failed: app cannot be registered", new Object[0]);
                    return;
                case 3:
                    LogManager.m16373e(CycledLeScannerForLollipop.TAG, "Scan failed: internal error", new Object[0]);
                    return;
                case 4:
                    LogManager.m16373e(CycledLeScannerForLollipop.TAG, "Scan failed: power optimized scan feature is not supported", new Object[0]);
                    return;
                default:
                    LogManager.m16373e(CycledLeScannerForLollipop.TAG, "Scan failed with unknown error (errorCode=" + errorCode + ")", new Object[0]);
                    return;
            }
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
        boolean deferScan = millisecondsUntilStart > 0;
        boolean scanActiveBefore = this.mMainScanCycleActive;
        this.mMainScanCycleActive = !deferScan;
        if (deferScan) {
            long secsSinceLastDetection = SystemClock.elapsedRealtime() - DetectionTracker.getInstance().getLastDetectionTime();
            if (scanActiveBefore) {
                if (secsSinceLastDetection > 10000) {
                    this.mBackgroundLScanStartTime = SystemClock.elapsedRealtime();
                    this.mBackgroundLScanFirstDetectionTime = 0;
                    LogManager.m16371d(TAG, "This is Android L. Preparing to do a filtered scan for the background.", new Object[0]);
                    if (this.mBetweenScanPeriod > 6000) {
                        startScan();
                    } else {
                        LogManager.m16371d(TAG, "Suppressing scan between cycles because the between scan cycle is too short.", new Object[0]);
                    }
                } else {
                    LogManager.m16371d(TAG, "This is Android L, but we last saw a beacon only %s ago, so we will not keep scanning in background.", Long.valueOf(secsSinceLastDetection));
                }
            }
            if (this.mBackgroundLScanStartTime > 0 && DetectionTracker.getInstance().getLastDetectionTime() > this.mBackgroundLScanStartTime) {
                if (this.mBackgroundLScanFirstDetectionTime == 0) {
                    this.mBackgroundLScanFirstDetectionTime = DetectionTracker.getInstance().getLastDetectionTime();
                }
                if (SystemClock.elapsedRealtime() - this.mBackgroundLScanFirstDetectionTime >= 10000) {
                    LogManager.m16371d(TAG, "We've been detecting for a bit.  Stopping Android L background scanning", new Object[0]);
                    stopScan();
                    this.mBackgroundLScanStartTime = 0;
                } else {
                    LogManager.m16371d(TAG, "Delivering Android L background scanning results", new Object[0]);
                    this.mCycledLeScanCallback.onCycleEnd();
                }
            }
            LogManager.m16371d(TAG, "Waiting to start full Bluetooth scan for another %s milliseconds", Long.valueOf(millisecondsUntilStart));
            if (scanActiveBefore && this.mBackgroundFlag) {
                setWakeUpAlarm();
            }
            Handler handler = this.mHandler;
            Runnable c48001 = new C48001();
            if (millisecondsUntilStart > 1000) {
                millisecondsUntilStart = 1000;
            }
            handler.postDelayed(c48001, millisecondsUntilStart);
        } else if (this.mBackgroundLScanStartTime > 0) {
            stopScan();
            this.mBackgroundLScanStartTime = 0;
        }
        return deferScan;
    }

    protected void startScan() {
        if (isBluetoothOn()) {
            ScanSettings settings;
            List<ScanFilter> filters = new ArrayList();
            if (this.mMainScanCycleActive) {
                LogManager.m16371d(TAG, "starting non-filtered scan in SCAN_MODE_LOW_LATENCY", new Object[0]);
                settings = new Builder().setScanMode(2).build();
                filters = new ScanFilterUtils().createWildcardScanFilters();
            } else {
                LogManager.m16371d(TAG, "starting filtered scan in SCAN_MODE_LOW_POWER", new Object[0]);
                settings = new Builder().setScanMode(0).build();
                filters = new ScanFilterUtils().createScanFiltersForBeaconParsers(this.mBeaconManager.getBeaconParsers());
            }
            if (settings != null) {
                postStartLeScan(filters, settings);
                return;
            }
            return;
        }
        LogManager.m16371d(TAG, "Not starting scan because bluetooth is off", new Object[0]);
    }

    protected void finishScan() {
        LogManager.m16371d(TAG, "Stopping scan", new Object[0]);
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
                @WorkerThread
                public void run() {
                    try {
                        scanner.startScan(list, scanSettings, scanCallback);
                    } catch (IllegalStateException e) {
                        LogManager.m16379w(CycledLeScannerForLollipop.TAG, "Cannot start scan. Bluetooth may be turned off.", new Object[0]);
                    } catch (NullPointerException npe) {
                        LogManager.m16374e(npe, CycledLeScannerForLollipop.TAG, "Cannot start scan. Unexpected NPE.", new Object[0]);
                    } catch (SecurityException e2) {
                        LogManager.m16373e(CycledLeScannerForLollipop.TAG, "Cannot start scan.  Security Exception", new Object[0]);
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
                    @WorkerThread
                    public void run() {
                        try {
                            LogManager.m16371d(CycledLeScannerForLollipop.TAG, "Stopping LE scan on scan handler", new Object[0]);
                            scanner.stopScan(scanCallback);
                        } catch (IllegalStateException e) {
                            LogManager.m16379w(CycledLeScannerForLollipop.TAG, "Cannot stop scan. Bluetooth may be turned off.", new Object[0]);
                        } catch (NullPointerException npe) {
                            LogManager.m16374e(npe, CycledLeScannerForLollipop.TAG, "Cannot stop scan. Unexpected NPE.", new Object[0]);
                        } catch (SecurityException e2) {
                            LogManager.m16373e(CycledLeScannerForLollipop.TAG, "Cannot stop scan.  Security Exception", new Object[0]);
                        }
                    }
                });
                return;
            }
            return;
        }
        LogManager.m16371d(TAG, "Not stopping scan because bluetooth is off", new Object[0]);
    }

    private boolean isBluetoothOn() {
        try {
            BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
            if (bluetoothAdapter == null) {
                LogManager.m16379w(TAG, "Cannot get bluetooth adapter", new Object[0]);
                return false;
            } else if (bluetoothAdapter.getState() == 12) {
                return true;
            } else {
                return false;
            }
        } catch (SecurityException e) {
            LogManager.m16379w(TAG, "SecurityException checking if bluetooth is on", new Object[0]);
            return false;
        }
    }

    private BluetoothLeScanner getScanner() {
        try {
            if (this.mScanner == null) {
                LogManager.m16371d(TAG, "Making new Android L scanner", new Object[0]);
                if (getBluetoothAdapter() != null) {
                    this.mScanner = getBluetoothAdapter().getBluetoothLeScanner();
                }
                if (this.mScanner == null) {
                    LogManager.m16379w(TAG, "Failed to make new Android L scanner", new Object[0]);
                }
            }
        } catch (SecurityException e) {
            LogManager.m16379w(TAG, "SecurityException making new Android L scanner", new Object[0]);
        }
        return this.mScanner;
    }

    private ScanCallback getNewLeScanCallback() {
        if (this.leScanCallback == null) {
            this.leScanCallback = new C48034();
        }
        return this.leScanCallback;
    }
}
