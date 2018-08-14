package org.altbeacon.bluetooth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import org.altbeacon.beacon.logging.LogManager;

class BluetoothCrashResolver$1 extends BroadcastReceiver {
    final /* synthetic */ BluetoothCrashResolver this$0;

    BluetoothCrashResolver$1(BluetoothCrashResolver this$0) {
        this.this$0 = this$0;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
            if (BluetoothCrashResolver.access$000(this.this$0)) {
                LogManager.m16371d("BluetoothCrashResolver", "Bluetooth discovery finished", new Object[0]);
                BluetoothCrashResolver.access$100(this.this$0);
            } else {
                LogManager.m16371d("BluetoothCrashResolver", "Bluetooth discovery finished (external)", new Object[0]);
            }
        }
        if (action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
            if (BluetoothCrashResolver.access$000(this.this$0)) {
                BluetoothCrashResolver.access$202(this.this$0, true);
                LogManager.m16371d("BluetoothCrashResolver", "Bluetooth discovery started", new Object[0]);
            } else {
                LogManager.m16371d("BluetoothCrashResolver", "Bluetooth discovery started (external)", new Object[0]);
            }
        }
        if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
            switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE)) {
                case Integer.MIN_VALUE:
                    LogManager.m16371d("BluetoothCrashResolver", "Bluetooth state is ERROR", new Object[0]);
                    return;
                case 10:
                    LogManager.m16371d("BluetoothCrashResolver", "Bluetooth state is OFF", new Object[0]);
                    BluetoothCrashResolver.access$302(this.this$0, SystemClock.elapsedRealtime());
                    return;
                case 11:
                    BluetoothCrashResolver.access$402(this.this$0, SystemClock.elapsedRealtime());
                    LogManager.m16371d("BluetoothCrashResolver", "Bluetooth state is TURNING_ON", new Object[0]);
                    return;
                case 12:
                    LogManager.m16371d("BluetoothCrashResolver", "Bluetooth state is ON", new Object[0]);
                    LogManager.m16371d("BluetoothCrashResolver", "Bluetooth was turned off for %s milliseconds", Long.valueOf(BluetoothCrashResolver.access$400(this.this$0) - BluetoothCrashResolver.access$300(this.this$0)));
                    if (BluetoothCrashResolver.access$400(this.this$0) - BluetoothCrashResolver.access$300(this.this$0) < 600) {
                        this.this$0.crashDetected();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
