package com.appnext.base.receivers.imp;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1044e;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.receivers.C1080b;

public class bcon extends C1080b {
    protected static final String TAG = bcon.class.getSimpleName();

    public boolean hasPermission() {
        return C1045f.m2133g(C1043d.getContext(), "android.permission.BLUETOOTH");
    }

    public IntentFilter getBRFilter() {
        try {
            if (!hasPermission()) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
            return intentFilter;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    public void onReceive(Context context, final Intent intent) {
        try {
            C1058l.m2184k("Receiver", getClass().getSimpleName());
            super.onReceive(context, intent);
            new Thread(new Runnable(this) {
                final /* synthetic */ bcon hR;

                public void run() {
                    try {
                        C1021c av = C1044e.cs().av(bcon.TAG);
                        if (av == null || C1042c.jG.equalsIgnoreCase(av.ba())) {
                            C1058l.m2185l(bcon.TAG, "config is off , Not executing anything, unregisterReceiver");
                            C1043d.getContext().unregisterReceiver(this.hR);
                            return;
                        }
                        String action = intent.getAction();
                        if ("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED".equals(action) || "android.bluetooth.device.action.ACL_CONNECTED".equals(action) || "android.bluetooth.device.action.ACL_DISCONNECTED".equals(action)) {
                            Bundle extras = intent.getExtras();
                            if (extras != null && extras.size() > 0) {
                                String name;
                                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                                if (bluetoothDevice != null) {
                                    name = bluetoothDevice.getName();
                                } else {
                                    name = null;
                                }
                                Boolean valueOf = "android.bluetooth.device.action.FOUND".equals(action) ? null : "android.bluetooth.device.action.ACL_CONNECTED".equals(action) ? Boolean.valueOf(true) : "android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action) ? Boolean.valueOf(false) : "android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED".equals(action) ? Boolean.valueOf(false) : "android.bluetooth.device.action.ACL_DISCONNECTED".equals(action) ? Boolean.valueOf(false) : null;
                                if (name != null && valueOf != null) {
                                    this.hR.m2250b(bcon.TAG, this.hR.m2251a(valueOf, name), C1041a.JSONArray);
                                }
                            }
                        }
                    } catch (Throwable th) {
                        C1061b.m2191a(th);
                    }
                }
            }).start();
        } catch (Throwable th) {
        }
    }
}
