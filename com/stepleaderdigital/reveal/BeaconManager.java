package com.stepleaderdigital.reveal;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.stepleaderdigital.reveal.Reveal.RevealLogger;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BeaconManager {
    public static final long DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD = 300000;
    public static final long DEFAULT_BACKGROUND_SCAN_PERIOD = 60000;
    public static final long DEFAULT_EXIT_PERIOD = 10000;
    public static final long DEFAULT_FOREGROUND_BETWEEN_SCAN_PERIOD = 20000;
    public static final long DEFAULT_FOREGROUND_SCAN_PERIOD = 1100;
    private static final Object SINGLETON_LOCK = new Object();
    protected static volatile BeaconManager client = null;
    protected static String distanceModelUpdateUrl = "http://static.revealmobile.com/android-distance.json";
    private static boolean manifestCheckingDisabled = false;
    private long backgroundBetweenScanPeriod = 300000;
    private boolean backgroundMode = false;
    private boolean backgroundModeUninitialized = true;
    private long backgroundScanPeriod = 60000;
    private BeaconLeScanCallback beaconCallback;
    private final ConcurrentMap<BeaconConsumer, ConsumerInfo> consumers = new ConcurrentHashMap();
    protected Context context;
    private long foregroundBetweenScanPeriod = 20000;
    private long foregroundScanPeriod = 1100;
    private Messenger serviceMessenger = null;

    public interface BeaconLeScanCallback {
        void onBeaconLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
    }

    private class BeaconServiceConnection implements ServiceConnection {
        private BeaconServiceConnection() {
        }

        public void onServiceConnected(ComponentName className, IBinder service) {
            RevealLogger.m12430d("we have a connection to the service now");
            BeaconManager.this.serviceMessenger = new Messenger(service);
            synchronized (BeaconManager.this.consumers) {
                for (Entry<BeaconConsumer, ConsumerInfo> entry : BeaconManager.this.consumers.entrySet()) {
                    if (!((ConsumerInfo) entry.getValue()).isConnected) {
                        ((BeaconConsumer) entry.getKey()).onBeaconServiceConnect();
                        ((ConsumerInfo) entry.getValue()).isConnected = true;
                    }
                }
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            RevealLogger.m12433e("onServiceDisconnected");
            BeaconManager.this.serviceMessenger = null;
        }
    }

    private class ConsumerInfo {
        public BeaconServiceConnection beaconServiceConnection;
        public boolean isConnected;

        public ConsumerInfo() {
            this.isConnected = false;
            this.isConnected = false;
            this.beaconServiceConnection = new BeaconServiceConnection();
        }
    }

    public class ServiceNotDeclaredException extends RuntimeException {
        public ServiceNotDeclaredException() {
            super("The BeaconService is not properly declared in AndroidManifest.xml.  If using Eclipse, please verify that your project.properties has manifestmerger.enabled=true");
        }
    }

    public static BeaconManager getInstanceForApplication(Context context) {
        BeaconManager instance = client;
        if (instance == null) {
            synchronized (SINGLETON_LOCK) {
                try {
                    instance = client;
                    if (instance == null) {
                        BeaconManager instance2 = new BeaconManager(context);
                        try {
                            client = instance2;
                            instance = instance2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            instance = instance2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return instance;
    }

    protected BeaconManager(Context context) {
        this.context = context.getApplicationContext();
        if (!manifestCheckingDisabled) {
            verifyServiceDeclaration();
        }
    }

    private String callbackPackageName() {
        RevealLogger.m12431d("callback packageName: %s", this.context.getPackageName());
        return this.context.getPackageName();
    }

    @TargetApi(18)
    public void startScanning() throws RemoteException {
        if (!isBleAvailable()) {
            RevealLogger.m12441w("No BLE so we can't scan");
        } else if (this.serviceMessenger == null) {
            throw new RemoteException("The BeaconManager is not bound to the service.  Call beaconManager.bind(BeaconConsumer consumer) and wait for a callback to onBeaconServiceConnect()");
        } else {
            RevealLogger.m12430d("Starting scanning");
            Message msg = Message.obtain(null, 2, 0, 0);
            msg.obj = new StartRMData(callbackPackageName(), getScanPeriod(), getBetweenScanPeriod(), this.backgroundMode);
            this.serviceMessenger.send(msg);
        }
    }

    @TargetApi(18)
    public void setScanPeriods() throws RemoteException {
        if (this.serviceMessenger == null) {
            throw new RemoteException("The BeaconManager is not bound to the service.  Call beaconManager.bind(BeaconConsumer consumer) and wait for a callback to onBeaconServiceConnect()");
        }
        RevealLogger.m12430d("Starting scanning");
        Message msg = Message.obtain(null, 6, 0, 0);
        msg.obj = new StartRMData(callbackPackageName(), getScanPeriod(), getBetweenScanPeriod(), this.backgroundMode);
        this.serviceMessenger.send(msg);
    }

    @TargetApi(18)
    public void stopScanning() throws RemoteException {
        if (!isBleAvailable()) {
            RevealLogger.m12441w("No BLE so we can't scan or stop one");
        } else if (this.serviceMessenger == null) {
            throw new RemoteException("The BeaconManager is not bound to the service.  Call beaconManager.bind(BeaconConsumer consumer) and wait for a callback to onBeaconServiceConnect()");
        } else {
            try {
                RevealLogger.m12430d("Stopping scanning");
                Message msg = Message.obtain(null, 3, 0, 0);
                msg.obj = new StartRMData(callbackPackageName(), getScanPeriod(), getBetweenScanPeriod(), this.backgroundMode);
                this.serviceMessenger.send(msg);
            } catch (RuntimeException exp) {
                RevealLogger.m12441w("A runtime exception occurred and is being suppressed to prevent a crash, please report the following exception to Reveal support");
                RevealLogger.m12441w(exp);
            }
        }
    }

    @TargetApi(18)
    public void updateScanPeriods() throws RemoteException {
        if (!isBleAvailable()) {
            RevealLogger.m12441w("Method invocation will be ignored.");
        } else if (this.serviceMessenger == null) {
            throw new RemoteException("The BeaconManager is not bound to the service.  Call beaconManager.bind(BeaconConsumer consumer) and wait for a callback to onBeaconServiceConnect()");
        } else {
            Message msg = Message.obtain(null, 6, 0, 0);
            RevealLogger.m12430d("updating background flag to " + this.backgroundMode);
            RevealLogger.m12430d("updating scan period to " + getScanPeriod() + ", " + getBetweenScanPeriod());
            msg.obj = new StartRMData(getScanPeriod(), getBetweenScanPeriod(), this.backgroundMode);
            this.serviceMessenger.send(msg);
        }
    }

    public void bind(BeaconConsumer consumer) {
        if (!isBleAvailable()) {
            RevealLogger.m12441w("Method invocation will be ignored.");
        } else if (this.context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            synchronized (this.consumers) {
                ConsumerInfo newConsumerInfo = new ConsumerInfo();
                if (((ConsumerInfo) this.consumers.putIfAbsent(consumer, newConsumerInfo)) != null) {
                    RevealLogger.m12430d("This consumer is already bound");
                } else {
                    RevealLogger.m12430d("This consumer is not bound.  binding: " + consumer);
                    consumer.bindService(new Intent(consumer.getApplicationContext(), BeaconService.class), newConsumerInfo.beaconServiceConnection, 1);
                    RevealLogger.m12430d("consumer count is now: " + this.consumers.size());
                }
            }
        } else {
            RevealLogger.m12441w("This device does not support bluetooth LE.  Will not start beacon scanning.");
        }
    }

    public void unbind(BeaconConsumer consumer) {
        if (isBleAvailable()) {
            synchronized (this.consumers) {
                if (this.consumers.containsKey(consumer)) {
                    RevealLogger.m12430d("Unbinding");
                    consumer.unbindService(((ConsumerInfo) this.consumers.get(consumer)).beaconServiceConnection);
                    this.consumers.remove(consumer);
                    if (this.consumers.size() == 0) {
                        this.serviceMessenger = null;
                        this.backgroundMode = false;
                    }
                } else {
                    RevealLogger.m12430d("This consumer is not bound to: " + consumer);
                    RevealLogger.m12430d("Bound consumers: ");
                    for (Entry<BeaconConsumer, ConsumerInfo> consumerEntry : this.consumers.entrySet()) {
                        RevealLogger.m12430d(String.valueOf(consumerEntry.getValue()));
                    }
                }
            }
            return;
        }
        RevealLogger.m12441w("Method invocation will be ignored.");
    }

    public boolean isBound(BeaconConsumer consumer) {
        boolean z;
        synchronized (this.consumers) {
            if (consumer != null) {
                if (!(this.consumers.get(consumer) == null || this.serviceMessenger == null)) {
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    public boolean isAnyConsumerBound() {
        boolean z;
        synchronized (this.consumers) {
            z = this.consumers.size() > 0 && this.serviceMessenger != null;
        }
        return z;
    }

    public void setBackgroundMode(boolean backgroundMode) {
        if (isBleAvailable()) {
            this.backgroundModeUninitialized = false;
            if (this.backgroundMode != backgroundMode) {
                this.backgroundMode = backgroundMode;
                try {
                    updateScanPeriods();
                    return;
                } catch (RemoteException e) {
                    RevealLogger.m12433e("Cannot contact service to set scan periods");
                    return;
                }
            }
            return;
        }
        RevealLogger.m12441w("Method invocation will be ignored.");
    }

    public boolean isBackgroundModeUninitialized() {
        return this.backgroundModeUninitialized;
    }

    private boolean isBleAvailable() {
        if (VERSION.SDK_INT < 18) {
            RevealLogger.m12441w("Bluetooth LE not supported prior to API 18.");
            return false;
        } else if (this.context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            return true;
        } else {
            RevealLogger.m12441w("This device does not support bluetooth LE.");
            return false;
        }
    }

    private long getScanPeriod() {
        if (this.backgroundMode) {
            return this.backgroundScanPeriod;
        }
        return this.foregroundScanPeriod;
    }

    private long getBetweenScanPeriod() {
        if (this.backgroundMode) {
            return this.backgroundBetweenScanPeriod;
        }
        return this.foregroundBetweenScanPeriod;
    }

    public void setForegroundScanPeriod(long p) {
        this.foregroundScanPeriod = p;
    }

    public void setForegroundBetweenScanPeriod(long p) {
        this.foregroundBetweenScanPeriod = p;
    }

    public void setBackgroundScanPeriod(long p) {
        this.backgroundScanPeriod = p;
    }

    public void setBackgroundBetweenScanPeriod(long p) {
        this.backgroundBetweenScanPeriod = p;
    }

    private void verifyServiceDeclaration() {
        if (this.context.getPackageManager().queryIntentServices(new Intent(this.context, BeaconService.class), 65536).size() == 0) {
            throw new ServiceNotDeclaredException();
        }
    }

    public static String getDistanceModelUpdateUrl() {
        return distanceModelUpdateUrl;
    }

    public static void setsManifestCheckingDisabled(boolean disabled) {
        manifestCheckingDisabled = disabled;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isBackgroundMode() {
        return this.backgroundMode;
    }

    public ConcurrentMap<BeaconConsumer, ConsumerInfo> getConsumers() {
        return this.consumers;
    }

    public Messenger getServiceMessenger() {
        return this.serviceMessenger;
    }

    public void setServiceMessenger(Messenger serviceMessenger) {
        this.serviceMessenger = serviceMessenger;
    }

    public BeaconLeScanCallback getBeaconCallback() {
        return this.beaconCallback;
    }

    public void setBeaconCallback(BeaconLeScanCallback beaconCallback) {
        this.beaconCallback = beaconCallback;
    }

    public long getForegroundScanPeriod() {
        return this.foregroundScanPeriod;
    }

    public long getForegroundBetweenScanPeriod() {
        return this.foregroundBetweenScanPeriod;
    }

    public long getBackgroundScanPeriod() {
        return this.backgroundScanPeriod;
    }

    public long getBackgroundBetweenScanPeriod() {
        return this.backgroundBetweenScanPeriod;
    }
}
