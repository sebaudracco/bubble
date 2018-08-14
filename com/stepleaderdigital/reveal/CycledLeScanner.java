package com.stepleaderdigital.reveal;

import android.annotation.SuppressLint;
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
import android.support.v4.app.NotificationCompat;
import com.stepleaderdigital.reveal.Reveal.RevealLogger;
import java.util.Date;

@TargetApi(18)
public abstract class CycledLeScanner {
    private static final long ANDROID_N_MIN_SCAN_CYCLE_MILLIS = 6000;
    private static final String TAG = "CycledLeScanner";
    protected boolean mBackgroundFlag = false;
    protected long mBetweenScanPeriod;
    private BluetoothAdapter mBluetoothAdapter;
    protected final BluetoothCrashResolver mBluetoothCrashResolver;
    protected final Context mContext;
    protected final CycledLeScanCallback mCycledLeScanCallback;
    protected final Handler mHandler = new Handler(Looper.getMainLooper());
    private long mLastScanCycleEndTime = 0;
    private long mLastScanCycleStartTime = 0;
    protected long mNextScanCycleStartTime = 0;
    protected boolean mRestartNeeded = false;
    private long mScanCycleStopTime = 0;
    private boolean mScanCyclerStarted = false;
    protected final Handler mScanHandler;
    private long mScanPeriod;
    private final HandlerThread mScanThread;
    private boolean mScanning;
    private boolean mScanningEnabled = false;
    protected boolean mScanningPaused;
    private PendingIntent mWakeUpOperation = null;

    class C39881 implements Runnable {
        C39881() {
        }

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

    public static CycledLeScanner createScanner(Context context, long scanPeriod, long betweenScanPeriod, boolean backgroundFlag, CycledLeScanCallback cycledLeScanCallback, BluetoothCrashResolver crashResolver, boolean disableAndroidL) {
        if (VERSION.SDK_INT < 18) {
            RevealLogger.m12441w("Not supported prior to API 18.");
            return null;
        }
        boolean useAndroidLScanner;
        if (VERSION.SDK_INT < 21) {
            RevealLogger.m12437i("This is not Android 5.0.  We are using old scanning APIs");
            useAndroidLScanner = false;
        } else if (disableAndroidL) {
            RevealLogger.m12437i("This Android 5.0, but L scanning is disabled. We are using old scanning APIs");
            useAndroidLScanner = false;
        } else {
            RevealLogger.m12437i("This Android 5.0.  We are using new scanning APIs");
            useAndroidLScanner = true;
        }
        if (useAndroidLScanner) {
            return new CycledLeScannerForLollipop(context, scanPeriod, betweenScanPeriod, backgroundFlag, cycledLeScanCallback, crashResolver);
        }
        return new CycledLeScannerForJellyBeanMr2(context, scanPeriod, betweenScanPeriod, backgroundFlag, cycledLeScanCallback, crashResolver);
    }

    public void setScanPeriods(long scanPeriod, long betweenScanPeriod, boolean backgroundFlag) {
        RevealLogger.m12430d("Set scan periods called with " + scanPeriod + ", " + betweenScanPeriod + " Background mode must have changed.");
        if (this.mBackgroundFlag != backgroundFlag) {
            this.mRestartNeeded = true;
        }
        this.mBackgroundFlag = backgroundFlag;
        this.mScanPeriod = scanPeriod;
        this.mBetweenScanPeriod = betweenScanPeriod;
        if (this.mBackgroundFlag) {
            RevealLogger.m12430d("We are in the background.  Setting wakeup alarm");
            setWakeUpAlarm();
        } else {
            RevealLogger.m12430d("We are not in the background.  Cancelling wakeup alarm");
            cancelWakeUpAlarm();
        }
        long now = SystemClock.elapsedRealtime();
        if (this.mNextScanCycleStartTime > now) {
            long proposedNextScanStartTime = this.mLastScanCycleEndTime + betweenScanPeriod;
            if (proposedNextScanStartTime < this.mNextScanCycleStartTime) {
                this.mNextScanCycleStartTime = proposedNextScanStartTime;
                RevealLogger.m12437i("Adjusted nextScanStartTime to be " + new Date((this.mNextScanCycleStartTime - SystemClock.elapsedRealtime()) + System.currentTimeMillis()));
            }
        }
        if (this.mScanCycleStopTime > now) {
            long proposedScanStopTime = this.mLastScanCycleStartTime + scanPeriod;
            if (proposedScanStopTime < this.mScanCycleStopTime) {
                this.mScanCycleStopTime = proposedScanStopTime;
                RevealLogger.m12437i("Adjusted scanStopTime to be " + this.mScanCycleStopTime);
            }
        }
    }

    public void start() {
        RevealLogger.m12430d("start called");
        this.mScanningEnabled = true;
        if (this.mScanCyclerStarted) {
            RevealLogger.m12430d("scanning already started");
        } else {
            scanLeDevice(Boolean.valueOf(true));
        }
    }

    @SuppressLint({"NewApi"})
    public void stop() {
        RevealLogger.m12430d("stop called");
        this.mScanningEnabled = false;
        if (this.mScanCyclerStarted) {
            scanLeDevice(Boolean.valueOf(false));
        } else {
            RevealLogger.m12430d("scanning already stopped");
        }
    }

    public void destroy() {
        this.mScanThread.quit();
    }

    @SuppressLint({"NewApi"})
    protected void scanLeDevice(Boolean enable) {
        try {
            this.mScanCyclerStarted = true;
            if (getBluetoothAdapter() == null) {
                RevealLogger.m12433e("No Bluetooth adapter.  beaconService cannot scan.");
            }
            if (!enable.booleanValue()) {
                RevealLogger.m12430d("disabling scan");
                this.mScanning = false;
                this.mScanCyclerStarted = false;
                stopScan();
                this.mLastScanCycleEndTime = SystemClock.elapsedRealtime();
            } else if (!deferScanIfNeeded()) {
                RevealLogger.m12430d("starting a new scan cycle");
                if (!this.mScanning || this.mScanningPaused || this.mRestartNeeded) {
                    this.mScanning = true;
                    this.mScanningPaused = false;
                    try {
                        if (getBluetoothAdapter() != null) {
                            if (getBluetoothAdapter().isEnabled()) {
                                if (this.mBluetoothCrashResolver != null && this.mBluetoothCrashResolver.isRecoveryInProgress()) {
                                    RevealLogger.m12441w("Skipping scan because crash recovery is in progress.");
                                } else if (this.mScanningEnabled) {
                                    if (this.mRestartNeeded) {
                                        this.mRestartNeeded = false;
                                        RevealLogger.m12430d("restarting a bluetooth le scan");
                                    } else {
                                        RevealLogger.m12430d("starting a new bluetooth le scan");
                                    }
                                    try {
                                        if (VERSION.SDK_INT < 23 || checkLocationPermission()) {
                                            startScan();
                                        }
                                    } catch (Exception e) {
                                        RevealLogger.m12433e("Internal Android exception scanning for beacons");
                                    }
                                } else {
                                    RevealLogger.m12430d("Scanning unnecessary - no monitoring or ranging active.");
                                }
                                this.mLastScanCycleStartTime = SystemClock.elapsedRealtime();
                            } else {
                                RevealLogger.m12430d("Bluetooth is disabled.  Cannot scan for beacons.");
                            }
                        }
                    } catch (Exception e2) {
                        RevealLogger.m12433e("Exception starting Bluetooth scan.  Perhaps Bluetooth is disabled or unavailable?");
                    }
                } else {
                    RevealLogger.m12430d("We are already scanning");
                }
                this.mScanCycleStopTime = SystemClock.elapsedRealtime() + this.mScanPeriod;
                scheduleScanCycleStop();
                RevealLogger.m12430d("Scan started");
                Reveal.getInstance().setStatus(Reveal.STATUS_SCAN, 2, "BLE Scan underway");
            }
        } catch (SecurityException e3) {
            RevealLogger.m12441w("SecurityException working accessing bluetooth.");
        }
    }

    protected void scheduleScanCycleStop() {
        long millisecondsUntilStop = this.mScanCycleStopTime - SystemClock.elapsedRealtime();
        if (millisecondsUntilStop > 0) {
            RevealLogger.m12440v("Waiting to stop scan cycle for another " + millisecondsUntilStop + " milliseconds");
            if (this.mBackgroundFlag) {
                setWakeUpAlarm();
            }
            Handler handler = this.mHandler;
            Runnable c39881 = new C39881();
            if (millisecondsUntilStop > 1000) {
                millisecondsUntilStop = 1000;
            }
            handler.postDelayed(c39881, millisecondsUntilStop);
            return;
        }
        finishScanCycle();
        Reveal.getInstance().getDwellManager().processInProgress(Reveal.getInstance().getIncompleteBeaconSendTime());
    }

    private void finishScanCycle() {
        RevealLogger.m12430d("Done with scan cycle");
        Reveal.getInstance().setStatus(Reveal.STATUS_SCAN, 1, "Not currently scanning");
        try {
            this.mCycledLeScanCallback.onCycleEnd();
            if (this.mScanning) {
                if (getBluetoothAdapter() != null) {
                    if (getBluetoothAdapter().isEnabled()) {
                        long now = SystemClock.elapsedRealtime();
                        if (VERSION.SDK_INT < 24 || this.mBetweenScanPeriod + this.mScanPeriod >= ANDROID_N_MIN_SCAN_CYCLE_MILLIS || now - this.mLastScanCycleStartTime >= ANDROID_N_MIN_SCAN_CYCLE_MILLIS) {
                            try {
                                RevealLogger.m12430d("stopping bluetooth le scan");
                                finishScan();
                            } catch (Exception e) {
                                RevealLogger.m12441w("Internal Android exception scanning for beacons" + e);
                            }
                        } else {
                            RevealLogger.m12430d("Not stopping scan because this is Android N and we keep scanning for a minimum of 6 seconds at a time. We will stop in " + (ANDROID_N_MIN_SCAN_CYCLE_MILLIS - (now - this.mLastScanCycleStartTime)) + " millisconds.");
                        }
                        this.mLastScanCycleEndTime = SystemClock.elapsedRealtime();
                    } else {
                        RevealLogger.m12430d("Bluetooth is disabled.  Cannot scan for beacons.");
                    }
                }
                this.mNextScanCycleStartTime = getNextScanStartTime();
                if (this.mScanningEnabled) {
                    scanLeDevice(Boolean.valueOf(true));
                }
            }
            if (!this.mScanningEnabled) {
                RevealLogger.m12430d("Scanning disabled.  No ranging or monitoring regions are active.");
                this.mScanCyclerStarted = false;
                cancelWakeUpAlarm();
            }
        } catch (SecurityException e2) {
            RevealLogger.m12441w("SecurityException working accessing bluetooth.");
        }
    }

    protected BluetoothAdapter getBluetoothAdapter() {
        try {
            if (this.mBluetoothAdapter == null) {
                this.mBluetoothAdapter = ((BluetoothManager) this.mContext.getApplicationContext().getSystemService(Reveal.STATUS_BLUETOOTH)).getAdapter();
                if (this.mBluetoothAdapter == null) {
                    RevealLogger.m12441w("Failed to construct a BluetoothAdapter");
                }
            }
        } catch (SecurityException e) {
            RevealLogger.m12433e("Cannot construct bluetooth adapter.  Security Exception");
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
        RevealLogger.m12430d("Set a wakeup alarm to go off in " + milliseconds + " ms: " + getWakeUpOperation());
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
        RevealLogger.m12430d("cancel wakeup alarm: " + this.mWakeUpOperation);
        ((AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(2, Long.MAX_VALUE, getWakeUpOperation());
        RevealLogger.m12430d("Set a wakeup alarm to go off in " + (Long.MAX_VALUE - SystemClock.elapsedRealtime()) + " ms: " + getWakeUpOperation());
    }

    private long getNextScanStartTime() {
        if (this.mBetweenScanPeriod == 0) {
            return SystemClock.elapsedRealtime();
        }
        long normalizedBetweenScanPeriod = this.mBetweenScanPeriod - (SystemClock.elapsedRealtime() % (this.mScanPeriod + this.mBetweenScanPeriod));
        RevealLogger.m12430d("Normalizing between scan period from " + this.mBetweenScanPeriod + " to " + normalizedBetweenScanPeriod);
        return SystemClock.elapsedRealtime() + normalizedBetweenScanPeriod;
    }

    private boolean checkLocationPermission() {
        return checkPermission("android.permission.ACCESS_COARSE_LOCATION") || checkPermission("android.permission.ACCESS_FINE_LOCATION");
    }

    private boolean checkPermission(String permission) {
        return this.mContext.checkPermission(permission, Process.myPid(), Process.myUid()) == 0;
    }
}
