package com.stepleaderdigital.reveal;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.stepleaderdigital.reveal.Reveal.RevealLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

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
    private final BroadcastReceiver receiver = new C39871();
    private int recoveryAttemptCount = 0;
    private boolean recoveryInProgress = false;
    private UpdateNotifier updateNotifier;

    class C39871 extends BroadcastReceiver {
        C39871() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            RevealLogger.m12430d("BroadcastReciever.onRecieve action=" + action);
            if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                if (BluetoothCrashResolver.this.recoveryInProgress) {
                    RevealLogger.m12430d("Bluetooth discovery finished");
                    BluetoothCrashResolver.this.finishRecovery();
                } else {
                    RevealLogger.m12430d("Bluetooth discovery finished (external)");
                }
            }
            if (action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
                if (BluetoothCrashResolver.this.recoveryInProgress) {
                    BluetoothCrashResolver.this.discoveryStartConfirmed = true;
                    RevealLogger.m12430d("Bluetooth discovery started");
                } else {
                    RevealLogger.m12430d("Bluetooth discovery started (external)");
                }
            }
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE)) {
                    case Integer.MIN_VALUE:
                        RevealLogger.m12430d("Bluetooth state is ERROR");
                        return;
                    case 10:
                        RevealLogger.m12430d("Bluetooth state is OFF");
                        BluetoothCrashResolver.this.lastBluetoothOffTime = SystemClock.elapsedRealtime();
                        return;
                    case 11:
                        BluetoothCrashResolver.this.lastBluetoothTurningOnTime = SystemClock.elapsedRealtime();
                        RevealLogger.m12430d("Bluetooth state is TURNING_ON");
                        return;
                    case 12:
                        RevealLogger.m12430d("Bluetooth state is ON");
                        RevealLogger.m12430d("Bluetooth was turned off for " + (BluetoothCrashResolver.this.lastBluetoothTurningOnTime - BluetoothCrashResolver.this.lastBluetoothOffTime) + " milliseconds");
                        if (BluetoothCrashResolver.this.lastBluetoothTurningOnTime - BluetoothCrashResolver.this.lastBluetoothOffTime < BluetoothCrashResolver.SUSPICIOUSLY_SHORT_BLUETOOTH_OFF_INTERVAL_MILLIS) {
                            BluetoothCrashResolver.this.crashDetected();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public interface UpdateNotifier {
        void dataUpdated();
    }

    public BluetoothCrashResolver(Context context) {
        this.context = context.getApplicationContext();
        RevealLogger.m12430d("constructed");
        loadState();
    }

    public void start() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        filter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        filter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        this.context.registerReceiver(this.receiver, filter);
        RevealLogger.m12430d("started listening for BluetoothAdapter events");
    }

    public void stop() {
        this.context.unregisterReceiver(this.receiver);
        RevealLogger.m12430d("stopped listening for BluetoothAdapter events");
        saveState();
    }

    @TargetApi(18)
    public void notifyScannedDevice(BluetoothDevice device, LeScanCallback scanner) {
        int oldSize = this.distinctBluetoothAddresses.size();
        synchronized (this.distinctBluetoothAddresses) {
            this.distinctBluetoothAddresses.add(device.getAddress());
        }
        int newSize = this.distinctBluetoothAddresses.size();
        if (oldSize != newSize && newSize % 100 == 0) {
            RevealLogger.m12430d("Distinct Bluetooth devices seen: %s" + this.distinctBluetoothAddresses.size());
        }
        if (this.distinctBluetoothAddresses.size() > getCrashRiskDeviceCount() && !this.recoveryInProgress) {
            RevealLogger.m12441w("Large number of Bluetooth devices detected: " + this.distinctBluetoothAddresses.size() + " Proactively " + "attempting to clear out address list to prevent a crash");
            RevealLogger.m12441w("Stopping LE Scan");
            BluetoothAdapter.getDefaultAdapter().stopLeScan(scanner);
            startRecovery();
            processStateChange();
        }
    }

    public void crashDetected() {
        if (VERSION.SDK_INT < 18) {
            RevealLogger.m12430d("Ignoring crashes before API 18, because BLE is unsupported.");
            return;
        }
        RevealLogger.m12441w("BluetoothService crash detected");
        if (this.distinctBluetoothAddresses.size() > 0) {
            RevealLogger.m12430d("Distinct Bluetooth devices seen at crash: %s" + this.distinctBluetoothAddresses.size());
        }
        this.lastBluetoothCrashDetectionTime = SystemClock.elapsedRealtime();
        this.detectedCrashCount++;
        if (this.recoveryInProgress) {
            RevealLogger.m12430d("Ignoring Bluetooth crash because recovery is already in progress.");
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
        RevealLogger.m12430d("about to check if discovery is active");
        if (adapter.isDiscovering()) {
            RevealLogger.m12441w("Already discovering.  Recovery attempt abandoned.");
            return;
        }
        RevealLogger.m12441w("Recovery attempt started");
        this.recoveryInProgress = true;
        this.discoveryStartConfirmed = false;
        RevealLogger.m12430d("about to command discovery");
        if (!adapter.startDiscovery()) {
            RevealLogger.m12441w("Can't start discovery.  Is Bluetooth turned on?");
        }
        RevealLogger.m12430d("startDiscovery commanded.  isDiscovering()=" + adapter.isDiscovering());
        RevealLogger.m12430d("We will be cancelling this discovery in5000 milliseconds.");
        cancelDiscovery();
    }

    private void finishRecovery() {
        RevealLogger.m12441w("Recovery attempt finished");
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
                RevealLogger.m12441w("Can't write macs to BluetoothCrashResolverState.txt");
                if (outputStreamWriter != null) {
                    try {
                        outputStreamWriter.close();
                    } catch (IOException e5) {
                    }
                }
                RevealLogger.m12430d("Wrote " + this.distinctBluetoothAddresses.size() + " Bluetooth addresses");
            } catch (Throwable th3) {
                th = th3;
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                throw th;
            }
        }
        RevealLogger.m12430d("Wrote " + this.distinctBluetoothAddresses.size() + " Bluetooth addresses");
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
                    this.lastRecoverySucceeded = line.equals(SchemaSymbols.ATTVAL_TRUE_1);
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
                    RevealLogger.m12441w("Can't read macs from BluetoothCrashResolverState.txt");
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e3) {
                        }
                    }
                    RevealLogger.m12430d("Read " + this.distinctBluetoothAddresses.size() + " Bluetooth addresses");
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
                RevealLogger.m12441w("Can't parse file BluetoothCrashResolverState.txt");
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e6) {
                    }
                }
                RevealLogger.m12430d("Read " + this.distinctBluetoothAddresses.size() + " Bluetooth addresses");
            } catch (Throwable th3) {
                th = th3;
                reader = reader2;
                if (reader != null) {
                    reader.close();
                }
                throw th;
            }
        } catch (IOException e7) {
            RevealLogger.m12441w("Can't read macs from BluetoothCrashResolverState.txt");
            if (reader != null) {
                reader.close();
            }
            RevealLogger.m12430d("Read " + this.distinctBluetoothAddresses.size() + " Bluetooth addresses");
        } catch (NumberFormatException e8) {
            RevealLogger.m12441w("Can't parse file BluetoothCrashResolverState.txt");
            if (reader != null) {
                reader.close();
            }
            RevealLogger.m12430d("Read " + this.distinctBluetoothAddresses.size() + " Bluetooth addresses");
        }
        RevealLogger.m12430d("Read " + this.distinctBluetoothAddresses.size() + " Bluetooth addresses");
    }

    private void cancelDiscovery() {
        try {
            Thread.sleep(5000);
            if (!this.discoveryStartConfirmed) {
                RevealLogger.m12441w("BluetoothAdapter.ACTION_DISCOVERY_STARTED never received.  Recovery may fail.");
            }
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            if (adapter.isDiscovering()) {
                RevealLogger.m12430d("Cancelling discovery");
                adapter.cancelDiscovery();
                return;
            }
            RevealLogger.m12430d("Discovery not running.  Won't cancel it");
        } catch (InterruptedException e) {
            RevealLogger.m12430d("DiscoveryCanceller sleep interrupted.");
        }
    }
}
