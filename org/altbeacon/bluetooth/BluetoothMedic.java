package org.altbeacon.bluetooth;

import android.app.NotificationManager;
import android.app.TaskStackBuilder;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.AdvertiseSettings.Builder;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import com.stepleaderdigital.reveal.Reveal;
import java.util.List;
import org.altbeacon.beacon.logging.LogManager;

public class BluetoothMedic {
    private static final long MIN_MILLIS_BETWEEN_BLUETOOTH_POWER_CYCLES = 60000;
    public static final int NO_TEST = 0;
    public static final int SCAN_TEST = 1;
    private static final String TAG = BluetoothMedic.class.getSimpleName();
    public static final int TRANSMIT_TEST = 2;
    @Nullable
    private static BluetoothMedic sInstance;
    @Nullable
    private BluetoothAdapter mAdapter;
    @RequiresApi(21)
    private BroadcastReceiver mBluetoothEventReceiver = new C48051();
    @NonNull
    private Handler mHandler = new Handler();
    private long mLastBluetoothPowerCycleTime = 0;
    @Nullable
    private LocalBroadcastManager mLocalBroadcastManager;
    private int mNotificationIcon = 0;
    private boolean mNotificationsEnabled = false;
    @Nullable
    private Boolean mScanTestResult = null;
    private int mTestType = 0;
    @Nullable
    private Boolean mTransmitterTestResult = null;

    class C48051 extends BroadcastReceiver {
        C48051() {
        }

        public void onReceive(Context context, Intent intent) {
            LogManager.m16371d(BluetoothMedic.TAG, "Broadcast notification received.", new Object[0]);
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            if (action.equalsIgnoreCase("onScanFailed")) {
                if (intent.getIntExtra("errorCode", -1) == 2) {
                    BluetoothMedic.this.sendNotification(context, "scan failed", "Power cycling bluetooth");
                    LogManager.m16371d(BluetoothMedic.TAG, "Detected a SCAN_FAILED_APPLICATION_REGISTRATION_FAILED.  We need to cycle bluetooth to recover", new Object[0]);
                    if (!BluetoothMedic.this.cycleBluetoothIfNotTooSoon()) {
                        BluetoothMedic.this.sendNotification(context, "scan failed", "Cannot power cycle bluetooth again");
                    }
                }
            } else if (!action.equalsIgnoreCase("onStartFailed")) {
                LogManager.m16371d(BluetoothMedic.TAG, "Unknown event.", new Object[0]);
            } else if (intent.getIntExtra("errorCode", -1) == 4) {
                BluetoothMedic.this.sendNotification(context, "advertising failed", "Expected failure.  Power cycling.");
                if (!BluetoothMedic.this.cycleBluetoothIfNotTooSoon()) {
                    BluetoothMedic.this.sendNotification(context, "advertising failed", "Cannot power cycle bluetooth again");
                }
            }
        }
    }

    class C48084 implements Runnable {
        C48084() {
        }

        public void run() {
            LogManager.m16371d(BluetoothMedic.TAG, "Turning Bluetooth back on.", new Object[0]);
            if (BluetoothMedic.this.mAdapter != null) {
                BluetoothMedic.this.mAdapter.enable();
            }
        }
    }

    public static BluetoothMedic getInstance() {
        if (sInstance == null) {
            sInstance = new BluetoothMedic();
        }
        return sInstance;
    }

    private BluetoothMedic() {
    }

    @RequiresApi(21)
    private void initializeWithContext(Context context) {
        if (this.mAdapter == null || this.mLocalBroadcastManager == null) {
            BluetoothManager manager = (BluetoothManager) context.getSystemService(Reveal.STATUS_BLUETOOTH);
            if (manager == null) {
                throw new NullPointerException("Cannot get BluetoothManager");
            }
            this.mAdapter = manager.getAdapter();
            this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
        }
    }

    @RequiresApi(21)
    public void enablePowerCycleOnFailures(Context context) {
        initializeWithContext(context);
        if (this.mLocalBroadcastManager != null) {
            this.mLocalBroadcastManager.registerReceiver(this.mBluetoothEventReceiver, new IntentFilter("onScanFailed"));
            this.mLocalBroadcastManager.registerReceiver(this.mBluetoothEventReceiver, new IntentFilter("onStartFailure"));
            LogManager.m16371d(TAG, "Medic monitoring for transmission and scan failure notifications with receiver: " + this.mBluetoothEventReceiver, new Object[0]);
        }
    }

    @RequiresApi(21)
    public void enablePeriodicTests(Context context, int testType) {
        initializeWithContext(context);
        this.mTestType = testType;
        LogManager.m16371d(TAG, "Medic scheduling periodic tests of types " + testType, new Object[0]);
        scheduleRegularTests(context);
    }

    @RequiresApi(21)
    public boolean runScanTest(final Context context) {
        initializeWithContext(context);
        this.mScanTestResult = null;
        LogManager.m16375i(TAG, "Starting scan test", new Object[0]);
        long testStartTime = System.currentTimeMillis();
        if (this.mAdapter != null) {
            final BluetoothLeScanner scanner = this.mAdapter.getBluetoothLeScanner();
            ScanCallback callback = new ScanCallback() {
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    BluetoothMedic.this.mScanTestResult = Boolean.valueOf(true);
                    LogManager.m16375i(BluetoothMedic.TAG, "Scan test succeeded", new Object[0]);
                    try {
                        scanner.stopScan(this);
                    } catch (IllegalStateException e) {
                    }
                }

                public void onBatchScanResults(List<ScanResult> results) {
                    super.onBatchScanResults(results);
                }

                public void onScanFailed(int errorCode) {
                    super.onScanFailed(errorCode);
                    LogManager.m16371d(BluetoothMedic.TAG, "Sending onScanFailed broadcast with " + BluetoothMedic.this.mLocalBroadcastManager, new Object[0]);
                    Intent intent = new Intent("onScanFailed");
                    intent.putExtra("errorCode", errorCode);
                    if (BluetoothMedic.this.mLocalBroadcastManager != null) {
                        BluetoothMedic.this.mLocalBroadcastManager.sendBroadcast(intent);
                    }
                    LogManager.m16371d(BluetoothMedic.TAG, "broadcast: " + intent + " should be received by " + BluetoothMedic.this.mBluetoothEventReceiver, new Object[0]);
                    if (errorCode == 2) {
                        LogManager.m16379w(BluetoothMedic.TAG, "Scan test failed in a way we consider a failure", new Object[0]);
                        BluetoothMedic.this.sendNotification(context, "scan failed", "bluetooth not ok");
                        BluetoothMedic.this.mScanTestResult = Boolean.valueOf(false);
                        return;
                    }
                    LogManager.m16375i(BluetoothMedic.TAG, "Scan test failed in a way we do not consider a failure", new Object[0]);
                    BluetoothMedic.this.mScanTestResult = Boolean.valueOf(true);
                }
            };
            if (scanner != null) {
                scanner.startScan(callback);
                while (this.mScanTestResult == null) {
                    LogManager.m16371d(TAG, "Waiting for scan test to complete...", new Object[0]);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    if (System.currentTimeMillis() - testStartTime > 5000) {
                        LogManager.m16371d(TAG, "Timeout running scan test", new Object[0]);
                        break;
                    }
                }
                try {
                    scanner.stopScan(callback);
                } catch (IllegalStateException e2) {
                }
            } else {
                LogManager.m16371d(TAG, "Cannot get scanner", new Object[0]);
            }
        }
        LogManager.m16371d(TAG, "scan test complete", new Object[0]);
        if (this.mScanTestResult == null || this.mScanTestResult.booleanValue()) {
            return true;
        }
        return false;
    }

    @RequiresApi(21)
    public boolean runTransmitterTest(final Context context) {
        initializeWithContext(context);
        this.mTransmitterTestResult = null;
        long testStartTime = System.currentTimeMillis();
        if (this.mAdapter != null) {
            final BluetoothLeAdvertiser advertiser = this.mAdapter.getBluetoothLeAdvertiser();
            if (advertiser != null) {
                AdvertiseSettings settings = new Builder().setAdvertiseMode(0).build();
                AdvertiseData data = new AdvertiseData.Builder().addManufacturerData(0, new byte[]{(byte) 0}).build();
                LogManager.m16375i(TAG, "Starting transmitter test", new Object[0]);
                advertiser.startAdvertising(settings, data, new AdvertiseCallback() {
                    public void onStartSuccess(AdvertiseSettings settingsInEffect) {
                        super.onStartSuccess(settingsInEffect);
                        LogManager.m16375i(BluetoothMedic.TAG, "Transmitter test succeeded", new Object[0]);
                        advertiser.stopAdvertising(this);
                        BluetoothMedic.this.mTransmitterTestResult = Boolean.valueOf(true);
                    }

                    public void onStartFailure(int errorCode) {
                        super.onStartFailure(errorCode);
                        Intent intent = new Intent("onStartFailed");
                        intent.putExtra("errorCode", errorCode);
                        LogManager.m16371d(BluetoothMedic.TAG, "Sending onStartFailure broadcast with " + BluetoothMedic.this.mLocalBroadcastManager, new Object[0]);
                        if (BluetoothMedic.this.mLocalBroadcastManager != null) {
                            BluetoothMedic.this.mLocalBroadcastManager.sendBroadcast(intent);
                        }
                        if (errorCode == 4) {
                            BluetoothMedic.this.mTransmitterTestResult = Boolean.valueOf(false);
                            LogManager.m16379w(BluetoothMedic.TAG, "Transmitter test failed in a way we consider a test failure", new Object[0]);
                            BluetoothMedic.this.sendNotification(context, "transmitter failed", "bluetooth not ok");
                            return;
                        }
                        BluetoothMedic.this.mTransmitterTestResult = Boolean.valueOf(true);
                        LogManager.m16375i(BluetoothMedic.TAG, "Transmitter test failed, but not in a way we consider a test failure", new Object[0]);
                    }
                });
            } else {
                LogManager.m16371d(TAG, "Cannot get advertiser", new Object[0]);
            }
            while (this.mTransmitterTestResult == null) {
                LogManager.m16371d(TAG, "Waiting for transmitter test to complete...", new Object[0]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                if (System.currentTimeMillis() - testStartTime > 5000) {
                    LogManager.m16371d(TAG, "Timeout running transmitter test", new Object[0]);
                    break;
                }
            }
        }
        LogManager.m16371d(TAG, "transmitter test complete", new Object[0]);
        if (this.mTransmitterTestResult == null || !this.mTransmitterTestResult.booleanValue()) {
            return false;
        }
        return true;
    }

    @RequiresApi(21)
    public void setNotificationsEnabled(boolean enabled, int icon) {
        this.mNotificationsEnabled = enabled;
        this.mNotificationIcon = icon;
    }

    @RequiresApi(21)
    private boolean cycleBluetoothIfNotTooSoon() {
        long millisSinceLastCycle = System.currentTimeMillis() - this.mLastBluetoothPowerCycleTime;
        if (millisSinceLastCycle < 60000) {
            LogManager.m16371d(TAG, "Not cycling bluetooth because we just did so " + millisSinceLastCycle + " milliseconds ago.", new Object[0]);
            return false;
        }
        this.mLastBluetoothPowerCycleTime = System.currentTimeMillis();
        LogManager.m16371d(TAG, "Power cycling bluetooth", new Object[0]);
        cycleBluetooth();
        return true;
    }

    @RequiresApi(21)
    private void cycleBluetooth() {
        LogManager.m16371d(TAG, "Power cycling bluetooth", new Object[0]);
        LogManager.m16371d(TAG, "Turning Bluetooth off.", new Object[0]);
        if (this.mAdapter != null) {
            this.mAdapter.disable();
            this.mHandler.postDelayed(new C48084(), 1000);
            return;
        }
        LogManager.m16379w(TAG, "Cannot cycle bluetooth.  Manager is null.", new Object[0]);
    }

    @RequiresApi(21)
    private void sendNotification(Context context, String message, String detail) {
        initializeWithContext(context);
        if (this.mNotificationsEnabled) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationCompat.CATEGORY_ERROR).setContentTitle("BluetoothMedic: " + message).setSmallIcon(this.mNotificationIcon).setVibrate(new long[]{200, 100, 200}).setContentText(detail);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntent(new Intent("NoOperation"));
            builder.setContentIntent(stackBuilder.getPendingIntent(0, 134217728));
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.notify(1, builder.build());
            }
        }
    }

    @RequiresApi(21)
    private void scheduleRegularTests(Context context) {
        initializeWithContext(context);
        JobInfo.Builder builder = new JobInfo.Builder(BluetoothTestJob.getJobId(context), new ComponentName(context, BluetoothTestJob.class));
        builder.setRequiresCharging(false);
        builder.setRequiresDeviceIdle(false);
        builder.setPeriodic(900000);
        builder.setPersisted(true);
        PersistableBundle bundle = new PersistableBundle();
        bundle.putInt("test_type", this.mTestType);
        builder.setExtras(bundle);
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.schedule(builder.build());
        }
    }
}
