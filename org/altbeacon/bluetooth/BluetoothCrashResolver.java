package org.altbeacon.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.SystemClock;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.altbeacon.beacon.logging.LogManager;

public class BluetoothCrashResolver {
    private static final int BLUEDROID_MAX_BLUETOOTH_MAC_COUNT = 1990;
    private static final int BLUEDROID_POST_DISCOVERY_ESTIMATED_BLUETOOTH_MAC_COUNT = 400;
    private static final String DISTINCT_BLUETOOTH_ADDRESSES_FILE = "BluetoothCrashResolverState.txt";
    private static final long MIN_TIME_BETWEEN_STATE_SAVES_MILLIS = 60000;
    private static final boolean PREEMPTIVE_ACTION_ENABLED = true;
    private static final long SUSPICIOUSLY_SHORT_BLUETOOTH_OFF_INTERVAL_MILLIS = 600;
    private static final String TAG = "BluetoothCrashResolver";
    private static final int TIME_TO_LET_DISCOVERY_RUN_MILLIS = 5000;
    private Context context = null;
    private int detectedCrashCount = 0;
    private boolean discoveryStartConfirmed = false;
    private final Set<String> distinctBluetoothAddresses = new HashSet();
    private long lastBluetoothCrashDetectionTime = 0;
    private long lastBluetoothOffTime = 0;
    private long lastBluetoothTurningOnTime = 0;
    private boolean lastRecoverySucceeded = false;
    private long lastStateSaveTime = 0;
    private final BroadcastReceiver receiver = new 1(this);
    private int recoveryAttemptCount = 0;
    private boolean recoveryInProgress = false;
    private UpdateNotifier updateNotifier;

    public BluetoothCrashResolver(Context context) {
        this.context = context.getApplicationContext();
        LogManager.d(TAG, "constructed", new Object[0]);
        loadState();
    }

    public void start() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        filter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        filter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        this.context.registerReceiver(this.receiver, filter);
        LogManager.d(TAG, "started listening for BluetoothAdapter events", new Object[0]);
    }

    public void stop() {
        this.context.unregisterReceiver(this.receiver);
        LogManager.d(TAG, "stopped listening for BluetoothAdapter events", new Object[0]);
        saveState();
    }

    @Deprecated
    public void enableDebug() {
    }

    @Deprecated
    public void disableDebug() {
    }

    @TargetApi(18)
    public void notifyScannedDevice(BluetoothDevice device, LeScanCallback scanner) {
        int oldSize = this.distinctBluetoothAddresses.size();
        synchronized (this.distinctBluetoothAddresses) {
            this.distinctBluetoothAddresses.add(device.getAddress());
        }
        int newSize = this.distinctBluetoothAddresses.size();
        if (oldSize != newSize && newSize % 100 == 0) {
            LogManager.d(TAG, "Distinct Bluetooth devices seen: %s", new Object[]{Integer.valueOf(this.distinctBluetoothAddresses.size())});
        }
        if (this.distinctBluetoothAddresses.size() > getCrashRiskDeviceCount() && !this.recoveryInProgress) {
            LogManager.w(TAG, "Large number of Bluetooth devices detected: %s Proactively attempting to clear out address list to prevent a crash", new Object[]{Integer.valueOf(this.distinctBluetoothAddresses.size())});
            LogManager.w(TAG, "Stopping LE Scan", new Object[0]);
            BluetoothAdapter.getDefaultAdapter().stopLeScan(scanner);
            startRecovery();
            processStateChange();
        }
    }

    public void crashDetected() {
        if (VERSION.SDK_INT < 18) {
            LogManager.d(TAG, "Ignoring crashes before API 18, because BLE is unsupported.", new Object[0]);
            return;
        }
        LogManager.w(TAG, "BluetoothService crash detected", new Object[0]);
        if (this.distinctBluetoothAddresses.size() > 0) {
            LogManager.d(TAG, "Distinct Bluetooth devices seen at crash: %s", new Object[]{Integer.valueOf(this.distinctBluetoothAddresses.size())});
        }
        this.lastBluetoothCrashDetectionTime = SystemClock.elapsedRealtime();
        this.detectedCrashCount++;
        if (this.recoveryInProgress) {
            LogManager.d(TAG, "Ignoring Bluetooth crash because recovery is already in progress.", new Object[0]);
        } else {
            startRecovery();
        }
        processStateChange();
    }

    public long getLastBluetoothCrashDetectionTime() {
        return this.lastBluetoothCrashDetectionTime;
    }

    public int getDetectedCrashCount() {
        return this.detectedCrashCount;
    }

    public int getRecoveryAttemptCount() {
        return this.recoveryAttemptCount;
    }

    public boolean isLastRecoverySucceeded() {
        return this.lastRecoverySucceeded;
    }

    public boolean isRecoveryInProgress() {
        return this.recoveryInProgress;
    }

    public void setUpdateNotifier(UpdateNotifier updateNotifier) {
        this.updateNotifier = updateNotifier;
    }

    public void forceFlush() {
        startRecovery();
        processStateChange();
    }

    private int getCrashRiskDeviceCount() {
        return 1590;
    }

    private void processStateChange() {
        if (this.updateNotifier != null) {
            this.updateNotifier.dataUpdated();
        }
        if (SystemClock.elapsedRealtime() - this.lastStateSaveTime > 60000) {
            saveState();
        }
    }

    @TargetApi(17)
    private void startRecovery() {
        this.recoveryAttemptCount++;
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        LogManager.d(TAG, "about to check if discovery is active", new Object[0]);
        if (adapter.isDiscovering()) {
            LogManager.w(TAG, "Already discovering.  Recovery attempt abandoned.", new Object[0]);
            return;
        }
        LogManager.w(TAG, "Recovery attempt started", new Object[0]);
        this.recoveryInProgress = true;
        this.discoveryStartConfirmed = false;
        LogManager.d(TAG, "about to command discovery", new Object[0]);
        if (!adapter.startDiscovery()) {
            LogManager.w(TAG, "Can't start discovery.  Is Bluetooth turned on?", new Object[0]);
        }
        LogManager.d(TAG, "startDiscovery commanded.  isDiscovering()=%s", new Object[]{Boolean.valueOf(adapter.isDiscovering())});
        LogManager.d(TAG, "We will be cancelling this discovery in %s milliseconds.", new Object[]{Integer.valueOf(5000)});
        cancelDiscovery();
    }

    private void finishRecovery() {
        LogManager.w(TAG, "Recovery attempt finished", new Object[0]);
        synchronized (this.distinctBluetoothAddresses) {
            this.distinctBluetoothAddresses.clear();
        }
        this.recoveryInProgress = false;
    }

    private void saveState() {
        Throwable th;
        OutputStreamWriter outputStreamWriter = null;
        this.lastStateSaveTime = SystemClock.elapsedRealtime();
        try {
            OutputStreamWriter writer = new OutputStreamWriter(this.context.openFileOutput(DISTINCT_BLUETOOTH_ADDRESSES_FILE, 0));
            try {
                writer.write(this.lastBluetoothCrashDetectionTime + "\n");
                writer.write(this.detectedCrashCount + "\n");
                writer.write(this.recoveryAttemptCount + "\n");
                writer.write(this.lastRecoverySucceeded ? "1\n" : "0\n");
                synchronized (this.distinctBluetoothAddresses) {
                    for (String mac : this.distinctBluetoothAddresses) {
                        writer.write(mac);
                        writer.write("\n");
                    }
                }
                if (writer != null) {
                    try {
                        writer.close();
                        outputStreamWriter = writer;
                    } catch (IOException e) {
                        outputStreamWriter = writer;
                    }
                }
            } catch (IOException e2) {
                outputStreamWriter = writer;
            } catch (Throwable th2) {
                th = th2;
                outputStreamWriter = writer;
                if (outputStreamWriter != null) {
                    try {
                        outputStreamWriter.close();
                    } catch (IOException e3) {
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            try {
                LogManager.w(TAG, "Can't write macs to %s", new Object[]{DISTINCT_BLUETOOTH_ADDRESSES_FILE});
                if (outputStreamWriter != null) {
                    try {
                        outputStreamWriter.close();
                    } catch (IOException e5) {
                    }
                }
                LogManager.d(TAG, "Wrote %s Bluetooth addresses", new Object[]{Integer.valueOf(this.distinctBluetoothAddresses.size())});
            } catch (Throwable th3) {
                th = th3;
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                throw th;
            }
        }
        LogManager.d(TAG, "Wrote %s Bluetooth addresses", new Object[]{Integer.valueOf(this.distinctBluetoothAddresses.size())});
    }

    private void loadState() {
        Throwable th;
        BufferedReader reader = null;
        try {
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(this.context.openFileInput(DISTINCT_BLUETOOTH_ADDRESSES_FILE)));
            try {
                String line = reader2.readLine();
                if (line != null) {
                    this.lastBluetoothCrashDetectionTime = Long.parseLong(line);
                }
                line = reader2.readLine();
                if (line != null) {
                    this.detectedCrashCount = Integer.parseInt(line);
                }
                line = reader2.readLine();
                if (line != null) {
                    this.recoveryAttemptCount = Integer.parseInt(line);
                }
                line = reader2.readLine();
                if (line != null) {
                    this.lastRecoverySucceeded = false;
                    if (line.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
                        this.lastRecoverySucceeded = true;
                    }
                }
                while (true) {
                    String mac = reader2.readLine();
                    if (mac == null) {
                        break;
                    }
                    this.distinctBluetoothAddresses.add(mac);
                }
                if (reader2 != null) {
                    try {
                        reader2.close();
                        reader = reader2;
                    } catch (IOException e) {
                        reader = reader2;
                    }
                }
            } catch (IOException e2) {
                reader = reader2;
                try {
                    LogManager.w(TAG, "Can't read macs from %s", new Object[]{DISTINCT_BLUETOOTH_ADDRESSES_FILE});
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e3) {
                        }
                    }
                    LogManager.d(TAG, "Read %s Bluetooth addresses", new Object[]{Integer.valueOf(this.distinctBluetoothAddresses.size())});
                } catch (Throwable th2) {
                    th = th2;
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (NumberFormatException e5) {
                reader = reader2;
                LogManager.w(TAG, "Can't parse file %s", new Object[]{DISTINCT_BLUETOOTH_ADDRESSES_FILE});
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e6) {
                    }
                }
                LogManager.d(TAG, "Read %s Bluetooth addresses", new Object[]{Integer.valueOf(this.distinctBluetoothAddresses.size())});
            } catch (Throwable th3) {
                th = th3;
                reader = reader2;
                if (reader != null) {
                    reader.close();
                }
                throw th;
            }
        } catch (IOException e7) {
            LogManager.w(TAG, "Can't read macs from %s", new Object[]{DISTINCT_BLUETOOTH_ADDRESSES_FILE});
            if (reader != null) {
                reader.close();
            }
            LogManager.d(TAG, "Read %s Bluetooth addresses", new Object[]{Integer.valueOf(this.distinctBluetoothAddresses.size())});
        } catch (NumberFormatException e8) {
            LogManager.w(TAG, "Can't parse file %s", new Object[]{DISTINCT_BLUETOOTH_ADDRESSES_FILE});
            if (reader != null) {
                reader.close();
            }
            LogManager.d(TAG, "Read %s Bluetooth addresses", new Object[]{Integer.valueOf(this.distinctBluetoothAddresses.size())});
        }
        LogManager.d(TAG, "Read %s Bluetooth addresses", new Object[]{Integer.valueOf(this.distinctBluetoothAddresses.size())});
    }

    private void cancelDiscovery() {
        try {
            Thread.sleep(5000);
            if (!this.discoveryStartConfirmed) {
                LogManager.w(TAG, "BluetoothAdapter.ACTION_DISCOVERY_STARTED never received.  Recovery may fail.", new Object[0]);
            }
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            if (adapter.isDiscovering()) {
                LogManager.d(TAG, "Cancelling discovery", new Object[0]);
                adapter.cancelDiscovery();
                return;
            }
            LogManager.d(TAG, "Discovery not running.  Won't cancel it", new Object[0]);
        } catch (InterruptedException e) {
            LogManager.d(TAG, "DiscoveryCanceller sleep interrupted.", new Object[0]);
        }
    }
}
