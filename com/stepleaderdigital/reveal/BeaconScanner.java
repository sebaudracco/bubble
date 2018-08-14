package com.stepleaderdigital.reveal;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.stepleaderdigital.reveal.Reveal.RevealLogger;

public class BeaconScanner {
    protected BluetoothAdapter adapter;
    protected Context applicationContext;
    protected BluetoothCrashResolver bluetoothCrashResolver;
    protected BeaconScannerCallback callback;
    protected BroadcastReceiver reciever;
    protected boolean useLowPowerOnNewerAndroidVersion;

    class C39851 extends BroadcastReceiver {
        C39851() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            RevealLogger.m12430d("BroadcastReceiver.onReceive() action=" + action);
            if ("android.bluetooth.device.action.FOUND".equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                RevealLogger.m12430d(device.getName() + "\n" + device.getAddress());
                int rssi = intent.getShortExtra("android.bluetooth.device.extra.RSSI", Short.MIN_VALUE);
                byte[] bytes = new byte[10];
                if (BeaconScanner.this.callback != null) {
                    BeaconScanner.this.callback.onBeaconFound(device, rssi, bytes);
                }
            }
        }
    }

    public interface BeaconScannerCallback {
        void onBeaconFound(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
    }

    public void setup(Context appContext) {
        this.applicationContext = appContext;
        RevealLogger.m12430d("beaconService version 1.4.21 is starting up");
        if (this.adapter != null) {
            RevealLogger.m12430d("we have a good adapter");
            if (!this.adapter.isEnabled()) {
            }
        }
    }

    public void start() {
        this.adapter = BluetoothAdapter.getDefaultAdapter();
        this.bluetoothCrashResolver = new BluetoothCrashResolver(this.applicationContext);
        this.reciever = new C39851();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        filter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        filter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        filter.addAction("android.bluetooth.device.action.FOUND");
        filter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        filter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
        this.applicationContext.registerReceiver(this.reciever, filter);
        this.bluetoothCrashResolver.start();
        this.adapter.startDiscovery();
    }

    public BluetoothAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(BluetoothAdapter adapter) {
        this.adapter = adapter;
    }

    public boolean isUseLowPowerOnNewerAndroidVersion() {
        return this.useLowPowerOnNewerAndroidVersion;
    }

    public void setUseLowPowerOnNewerAndroidVersion(boolean useLowPowerOnNewerAndroidVersion) {
        this.useLowPowerOnNewerAndroidVersion = useLowPowerOnNewerAndroidVersion;
    }

    public BeaconScannerCallback getCallback() {
        return this.callback;
    }

    public void setCallback(BeaconScannerCallback callback) {
        this.callback = callback;
    }
}
