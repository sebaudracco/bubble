package org.altbeacon.beacon.service.scanner;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.AnyThread;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.support.v4.app.NotificationCompat;
import com.stepleaderdigital.reveal.Reveal;
import java.util.Date;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.startup.StartupBroadcastReceiver;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

@TargetApi(18)
public abstract class CycledLeScanner {
    public static final long ANDROID_N_MAX_SCAN_DURATION_MILLIS = 1800000;
    private static final long ANDROID_N_MIN_SCAN_CYCLE_MILLIS = 6000;
    private static final String TAG = "CycledLeScanner";
    protected boolean mBackgroundFlag = false;
    protected long mBetweenScanPeriod;
    private BluetoothAdapter mBluetoothAdapter;
    protected final BluetoothCrashResolver mBluetoothCrashResolver;
    protected final Context mContext;
    private long mCurrentScanStartTime = 0;
    protected final CycledLeScanCallback mCycledLeScanCallback;
    private volatile boolean mDistinctPacketsDetectedPerScan = false;
    @NonNull
    protected final Handler mHandler = new Handler(Looper.getMainLooper());
    private long mLastScanCycleEndTime = 0;
    private long mLastScanCycleStartTime = 0;
    private boolean mLongScanForcingEnabled = false;
    protected long mNextScanCycleStartTime = 0;
    protected boolean mRestartNeeded = false;
    private long mScanCycleStopTime = 0;
    private boolean mScanCyclerStarted = false;
    @NonNull
    protected final Handler mScanHandler;
    private long mScanPeriod;
    @NonNull
    private final HandlerThread mScanThread;
    private boolean mScanning;
    private boolean mScanningEnabled = false;
    private boolean mScanningLeftOn = false;
    protected boolean mScanningPaused;
    private PendingIntent mWakeUpOperation = null;

    class C47941 implements Runnable {
        C47941() {
        }

        @WorkerThread
        public void run() {
            LogManager.m16371d(CycledLeScanner.TAG, "Quitting scan thread", new Object[0]);
            CycledLeScanner.this.mScanThread.quit();
        }
    }

    class C47952 implements Runnable {
        C47952() {
        }

        @MainThread
        public void run() {
            CycledLeScanner.this.scheduleScanCycleStop();
        }
    }

    protected abstract boolean deferScanIfNeeded();

    protected abstract void finishScan();

    protected abstract void startScan();

    protected abstract void stopScan();

    protected CycledLeScanner(Context context, long scanPeriod, long betweenScanPeriod, boolean backgroundFlag, CycledLeScanCallback cycledLeScanCallback, BluetoothCrashResolver crashResolver) {
        this.mScanPeriod = scanPeriod;
        this.mBetweenScanPeriod = betweenScanPeriod;
        this.mContext = context;
        this.mCycledLeScanCallback = cycledLeScanCallback;
        this.mBluetoothCrashResolver = crashResolver;
        this.mBackgroundFlag = backgroundFlag;
        this.mScanThread = new HandlerThread("CycledLeScannerThread");
        this.mScanThread.start();
        this.mScanHandler = new Handler(this.mScanThread.getLooper());
    }

    public static CycledLeScanner createScanner(Context context, long scanPeriod, long betweenScanPeriod, boolean backgroundFlag, CycledLeScanCallback cycledLeScanCallback, BluetoothCrashResolver crashResolver) {
        boolean useAndroidLScanner = false;
        boolean useAndroidOScanner = false;
        if (VERSION.SDK_INT < 18) {
            LogManager.m16379w(TAG, "Not supported prior to API 18.", new Object[0]);
            return null;
        }
        if (VERSION.SDK_INT < 21) {
            LogManager.m16375i(TAG, "This is pre Android 5.0.  We are using old scanning APIs", new Object[0]);
            useAndroidLScanner = false;
        } else if (VERSION.SDK_INT >= 26) {
            LogManager.m16375i(TAG, "Using Android O scanner", new Object[0]);
            useAndroidOScanner = true;
        } else if (BeaconManager.isAndroidLScanningDisabled()) {
            LogManager.m16375i(TAG, "This is Android 5.0, but L scanning is disabled. We are using old scanning APIs", new Object[0]);
            useAndroidLScanner = false;
        } else {
            LogManager.m16375i(TAG, "This is Android 5.0.  We are using new scanning APIs", new Object[0]);
            useAndroidLScanner = true;
        }
        if (useAndroidOScanner) {
            return new CycledLeScannerForAndroidO(context, scanPeriod, betweenScanPeriod, backgroundFlag, cycledLeScanCallback, crashResolver);
        }
        if (useAndroidLScanner) {
            return new CycledLeScannerForLollipop(context, scanPeriod, betweenScanPeriod, backgroundFlag, cycledLeScanCallback, crashResolver);
        }
        return new CycledLeScannerForJellyBeanMr2(context, scanPeriod, betweenScanPeriod, backgroundFlag, cycledLeScanCallback, crashResolver);
    }

    public void setLongScanForcingEnabled(boolean enabled) {
        this.mLongScanForcingEnabled = enabled;
    }

    @MainThread
    public void setScanPeriods(long scanPeriod, long betweenScanPeriod, boolean backgroundFlag) {
        LogManager.m16371d(TAG, "Set scan periods called with %s, %s Background mode must have changed.", Long.valueOf(scanPeriod), Long.valueOf(betweenScanPeriod));
        if (this.mBackgroundFlag != backgroundFlag) {
            this.mRestartNeeded = true;
        }
        this.mBackgroundFlag = backgroundFlag;
        this.mScanPeriod = scanPeriod;
        this.mBetweenScanPeriod = betweenScanPeriod;
        if (this.mBackgroundFlag) {
            LogManager.m16371d(TAG, "We are in the background.  Setting wakeup alarm", new Object[0]);
            setWakeUpAlarm();
        } else {
            LogManager.m16371d(TAG, "We are not in the background.  Cancelling wakeup alarm", new Object[0]);
            cancelWakeUpAlarm();
        }
        long now = SystemClock.elapsedRealtime();
        if (this.mNextScanCycleStartTime > now) {
            long proposedNextScanStartTime = this.mLastScanCycleEndTime + betweenScanPeriod;
            if (proposedNextScanStartTime < this.mNextScanCycleStartTime) {
                this.mNextScanCycleStartTime = proposedNextScanStartTime;
                LogManager.m16375i(TAG, "Adjusted nextScanStartTime to be %s", new Date((this.mNextScanCycleStartTime - SystemClock.elapsedRealtime()) + System.currentTimeMillis()));
            }
        }
        if (this.mScanCycleStopTime > now) {
            long proposedScanStopTime = this.mLastScanCycleStartTime + scanPeriod;
            if (proposedScanStopTime < this.mScanCycleStopTime) {
                this.mScanCycleStopTime = proposedScanStopTime;
                LogManager.m16375i(TAG, "Adjusted scanStopTime to be %s", Long.valueOf(this.mScanCycleStopTime));
            }
        }
    }

    @MainThread
    public void start() {
        LogManager.m16371d(TAG, "start called", new Object[0]);
        this.mScanningEnabled = true;
        if (this.mScanCyclerStarted) {
            LogManager.m16371d(TAG, "scanning already started", new Object[0]);
        } else {
            scanLeDevice(Boolean.valueOf(true));
        }
    }

    @MainThread
    public void stop() {
        LogManager.m16371d(TAG, "stop called", new Object[0]);
        this.mScanningEnabled = false;
        if (this.mScanCyclerStarted) {
            scanLeDevice(Boolean.valueOf(false));
            if (this.mScanningLeftOn) {
                LogManager.m16371d(TAG, "Stopping scanning previously left on.", new Object[0]);
                this.mScanningLeftOn = false;
                try {
                    LogManager.m16371d(TAG, "stopping bluetooth le scan", new Object[0]);
                    finishScan();
                    return;
                } catch (Exception e) {
                    LogManager.m16380w(e, TAG, "Internal Android exception scanning for beacons", new Object[0]);
                    return;
                }
            }
            return;
        }
        LogManager.m16371d(TAG, "scanning already stopped", new Object[0]);
    }

    @AnyThread
    public boolean getDistinctPacketsDetectedPerScan() {
        return this.mDistinctPacketsDetectedPerScan;
    }

    @AnyThread
    public void setDistinctPacketsDetectedPerScan(boolean detected) {
        this.mDistinctPacketsDetectedPerScan = detected;
    }

    @MainThread
    public void destroy() {
        LogManager.m16371d(TAG, "Destroying", new Object[0]);
        this.mHandler.removeCallbacksAndMessages(null);
        this.mScanHandler.post(new C47941());
    }

    @MainThread
    protected void scanLeDevice(Boolean enable) {
        try {
            this.mScanCyclerStarted = true;
            if (getBluetoothAdapter() == null) {
                LogManager.m16373e(TAG, "No Bluetooth adapter.  beaconService cannot scan.", new Object[0]);
            }
            if (!this.mScanningEnabled || !enable.booleanValue()) {
                LogManager.m16371d(TAG, "disabling scan", new Object[0]);
                this.mScanning = false;
                this.mScanCyclerStarted = false;
                stopScan();
                this.mCurrentScanStartTime = 0;
                this.mLastScanCycleEndTime = SystemClock.elapsedRealtime();
                this.mHandler.removeCallbacksAndMessages(null);
                finishScanCycle();
            } else if (!deferScanIfNeeded()) {
                LogManager.m16371d(TAG, "starting a new scan cycle", new Object[0]);
                if (!this.mScanning || this.mScanningPaused || this.mRestartNeeded) {
                    this.mScanning = true;
                    this.mScanningPaused = false;
                    try {
                        if (getBluetoothAdapter() != null) {
                            if (getBluetoothAdapter().isEnabled()) {
                                if (this.mBluetoothCrashResolver != null && this.mBluetoothCrashResolver.isRecoveryInProgress()) {
                                    LogManager.m16379w(TAG, "Skipping scan because crash recovery is in progress.", new Object[0]);
                                } else if (this.mScanningEnabled) {
                                    if (this.mRestartNeeded) {
                                        this.mRestartNeeded = false;
                                        LogManager.m16371d(TAG, "restarting a bluetooth le scan", new Object[0]);
                                    } else {
                                        LogManager.m16371d(TAG, "starting a new bluetooth le scan", new Object[0]);
                                    }
                                    try {
                                        if (VERSION.SDK_INT < 23 || checkLocationPermission()) {
                                            this.mCurrentScanStartTime = SystemClock.elapsedRealtime();
                                            startScan();
                                        }
                                    } catch (Exception e) {
                                        LogManager.m16374e(e, TAG, "Internal Android exception scanning for beacons", new Object[0]);
                                    }
                                } else {
                                    LogManager.m16371d(TAG, "Scanning unnecessary - no monitoring or ranging active.", new Object[0]);
                                }
                                this.mLastScanCycleStartTime = SystemClock.elapsedRealtime();
                            } else {
                                LogManager.m16371d(TAG, "Bluetooth is disabled.  Cannot scan for beacons.", new Object[0]);
                            }
                        }
                    } catch (Exception e2) {
                        LogManager.m16374e(e2, TAG, "Exception starting Bluetooth scan.  Perhaps Bluetooth is disabled or unavailable?", new Object[0]);
                    }
                } else {
                    LogManager.m16371d(TAG, "We are already scanning and have been for " + (SystemClock.elapsedRealtime() - this.mCurrentScanStartTime) + " millis", new Object[0]);
                }
                this.mScanCycleStopTime = SystemClock.elapsedRealtime() + this.mScanPeriod;
                scheduleScanCycleStop();
                LogManager.m16371d(TAG, "Scan started", new Object[0]);
            }
        } catch (SecurityException e3) {
            LogManager.m16379w(TAG, "SecurityException working accessing bluetooth.", new Object[0]);
        }
    }

    @MainThread
    protected void scheduleScanCycleStop() {
        long millisecondsUntilStop = this.mScanCycleStopTime - SystemClock.elapsedRealtime();
        if (!this.mScanningEnabled || millisecondsUntilStop <= 0) {
            finishScanCycle();
            return;
        }
        LogManager.m16371d(TAG, "Waiting to stop scan cycle for another %s milliseconds", Long.valueOf(millisecondsUntilStop));
        if (this.mBackgroundFlag) {
            setWakeUpAlarm();
        }
        Handler handler = this.mHandler;
        Runnable c47952 = new C47952();
        if (millisecondsUntilStop > 1000) {
            millisecondsUntilStop = 1000;
        }
        handler.postDelayed(c47952, millisecondsUntilStop);
    }

    @MainThread
    private void finishScanCycle() {
        LogManager.m16371d(TAG, "Done with scan cycle", new Object[0]);
        try {
            this.mCycledLeScanCallback.onCycleEnd();
            if (this.mScanning) {
                if (getBluetoothAdapter() != null) {
                    if (getBluetoothAdapter().isEnabled()) {
                        if (this.mDistinctPacketsDetectedPerScan && this.mBetweenScanPeriod == 0 && !mustStopScanToPreventAndroidNScanTimeout()) {
                            LogManager.m16371d(TAG, "Not stopping scanning.  Device capable of multiple indistinct detections per scan.", new Object[0]);
                            this.mScanningLeftOn = true;
                        } else {
                            long now = SystemClock.elapsedRealtime();
                            if (VERSION.SDK_INT < 24 || this.mBetweenScanPeriod + this.mScanPeriod >= ANDROID_N_MIN_SCAN_CYCLE_MILLIS || now - this.mLastScanCycleStartTime >= ANDROID_N_MIN_SCAN_CYCLE_MILLIS) {
                                try {
                                    LogManager.m16371d(TAG, "stopping bluetooth le scan", new Object[0]);
                                    finishScan();
                                    this.mScanningLeftOn = false;
                                } catch (Exception e) {
                                    LogManager.m16380w(e, TAG, "Internal Android exception scanning for beacons", new Object[0]);
                                }
                            } else {
                                LogManager.m16371d(TAG, "Not stopping scan because this is Android N and we keep scanning for a minimum of 6 seconds at a time. We will stop in " + (ANDROID_N_MIN_SCAN_CYCLE_MILLIS - (now - this.mLastScanCycleStartTime)) + " millisconds.", new Object[0]);
                                this.mScanningLeftOn = true;
                            }
                        }
                        this.mLastScanCycleEndTime = SystemClock.elapsedRealtime();
                    } else {
                        LogManager.m16371d(TAG, "Bluetooth is disabled.  Cannot scan for beacons.", new Object[0]);
                        this.mRestartNeeded = true;
                    }
                }
                this.mNextScanCycleStartTime = getNextScanStartTime();
                if (this.mScanningEnabled) {
                    scanLeDevice(Boolean.valueOf(true));
                }
            }
            if (!this.mScanningEnabled) {
                LogManager.m16371d(TAG, "Scanning disabled. ", new Object[0]);
                this.mScanCyclerStarted = false;
                cancelWakeUpAlarm();
            }
        } catch (SecurityException e2) {
            LogManager.m16379w(TAG, "SecurityException working accessing bluetooth.", new Object[0]);
        }
    }

    protected BluetoothAdapter getBluetoothAdapter() {
        try {
            if (this.mBluetoothAdapter == null) {
                this.mBluetoothAdapter = ((BluetoothManager) this.mContext.getApplicationContext().getSystemService(Reveal.STATUS_BLUETOOTH)).getAdapter();
                if (this.mBluetoothAdapter == null) {
                    LogManager.m16379w(TAG, "Failed to construct a BluetoothAdapter", new Object[0]);
                }
            }
        } catch (SecurityException e) {
            LogManager.m16373e(TAG, "Cannot consruct bluetooth adapter.  Security Exception", new Object[0]);
        }
        return this.mBluetoothAdapter;
    }

    protected void setWakeUpAlarm() {
        long milliseconds = 300000;
        if (300000 < this.mBetweenScanPeriod) {
            milliseconds = this.mBetweenScanPeriod;
        }
        if (milliseconds < this.mScanPeriod) {
            milliseconds = this.mScanPeriod;
        }
        ((AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(2, SystemClock.elapsedRealtime() + milliseconds, getWakeUpOperation());
        LogManager.m16371d(TAG, "Set a wakeup alarm to go off in %s ms: %s", Long.valueOf(milliseconds), getWakeUpOperation());
    }

    protected PendingIntent getWakeUpOperation() {
        if (this.mWakeUpOperation == null) {
            Intent wakeupIntent = new Intent(this.mContext, StartupBroadcastReceiver.class);
            wakeupIntent.putExtra("wakeup", true);
            this.mWakeUpOperation = PendingIntent.getBroadcast(this.mContext, 0, wakeupIntent, 134217728);
        }
        return this.mWakeUpOperation;
    }

    protected void cancelWakeUpAlarm() {
        LogManager.m16371d(TAG, "cancel wakeup alarm: %s", this.mWakeUpOperation);
        ((AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(2, Long.MAX_VALUE, getWakeUpOperation());
        LogManager.m16371d(TAG, "Set a wakeup alarm to go off in %s ms: %s", Long.valueOf(Long.MAX_VALUE - SystemClock.elapsedRealtime()), getWakeUpOperation());
    }

    private long getNextScanStartTime() {
        if (this.mBetweenScanPeriod == 0) {
            return SystemClock.elapsedRealtime();
        }
        LogManager.m16371d(TAG, "Normalizing between scan period from %s to %s", Long.valueOf(this.mBetweenScanPeriod), Long.valueOf(this.mBetweenScanPeriod - (SystemClock.elapsedRealtime() % (this.mScanPeriod + this.mBetweenScanPeriod))));
        return SystemClock.elapsedRealtime() + (this.mBetweenScanPeriod - (SystemClock.elapsedRealtime() % (this.mScanPeriod + this.mBetweenScanPeriod)));
    }

    private boolean checkLocationPermission() {
        return checkPermission("android.permission.ACCESS_COARSE_LOCATION") || checkPermission("android.permission.ACCESS_FINE_LOCATION");
    }

    private boolean checkPermission(String permission) {
        return this.mContext.checkPermission(permission, Process.myPid(), Process.myUid()) == 0;
    }

    private boolean mustStopScanToPreventAndroidNScanTimeout() {
        boolean timeoutAtRisk;
        long timeOfNextScanCycleEnd = (SystemClock.elapsedRealtime() + this.mBetweenScanPeriod) + this.mScanPeriod;
        if (VERSION.SDK_INT < 24 || this.mCurrentScanStartTime <= 0 || timeOfNextScanCycleEnd - this.mCurrentScanStartTime <= ANDROID_N_MAX_SCAN_DURATION_MILLIS) {
            timeoutAtRisk = false;
        } else {
            timeoutAtRisk = true;
        }
        if (timeoutAtRisk) {
            LogManager.m16371d(TAG, "The next scan cycle would go over the Android N max duration.", new Object[0]);
            if (this.mLongScanForcingEnabled) {
                LogManager.m16371d(TAG, "Stopping scan to prevent Android N scan timeout.", new Object[0]);
                return true;
            }
            LogManager.m16379w(TAG, "Allowing a long running scan to be stopped by the OS.  To prevent this, set longScanForcingEnabled in the AndroidBeaconLibrary.", new Object[0]);
        }
        return false;
    }
}
