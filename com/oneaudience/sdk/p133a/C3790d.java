package com.oneaudience.sdk.p133a;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import com.oneaudience.sdk.C3843e;
import com.oneaudience.sdk.model.BluetoothData;
import com.oneaudience.sdk.model.BluetoothDeviceData;
import com.oneaudience.sdk.p135c.C3833d;
import java.util.ArrayList;
import java.util.Iterator;

public class C3790d extends C3784a {
    private static final String[] f9096f = new String[]{"android.permission.ACCESS_COARSE_LOCATION"};
    private ArrayList<BluetoothDeviceData> f9097g;
    private BluetoothAdapter f9098h;
    private int f9099i;
    private boolean f9100j;
    private Handler f9101k = new Handler(Looper.getMainLooper());
    private long f9102l;
    private final BroadcastReceiver f9103m = new C37881(this);
    private Runnable f9104n = new C37892(this);

    class C37881 extends BroadcastReceiver {
        final /* synthetic */ C3790d f9094a;

        C37881(C3790d c3790d) {
            this.f9094a = c3790d;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if ("android.bluetooth.device.action.FOUND".equals(action)) {
                    this.f9094a.m12101a((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), true);
                } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                    this.f9094a.d = true;
                    this.f9094a.f9100j = true;
                    this.f9094a.f9101k.removeCallbacks(this.f9094a.f9104n);
                    C3833d.m12246a("BLUETOOTH", "Saving list size after scan finished: " + this.f9094a.f9097g.size());
                    if (!this.f9094a.f9097g.isEmpty()) {
                        this.f9094a.m12084a(this.f9094a.m12083a((Object) new BluetoothData(this.f9094a.f9099i, this.f9094a.f9102l, this.f9094a.f9097g)));
                    }
                    this.f9094a.m12112j();
                }
            } catch (Throwable th) {
                C3833d.m12248a("BLUETOOTH", "Failed to collect bluetooth data: ", th);
            }
        }
    }

    class C37892 implements Runnable {
        final /* synthetic */ C3790d f9095a;

        C37892(C3790d c3790d) {
            this.f9095a = c3790d;
        }

        public void run() {
            try {
                if (!this.f9095a.f9100j) {
                    this.f9095a.d = true;
                    C3833d.m12246a("BLUETOOTH", "Saving list size after timeout: " + this.f9095a.f9097g.size());
                    if (!this.f9095a.f9097g.isEmpty()) {
                        this.f9095a.m12084a(this.f9095a.m12083a((Object) new BluetoothData(this.f9095a.f9099i, this.f9095a.f9102l, this.f9095a.f9097g)));
                    }
                    this.f9095a.f9098h.cancelDiscovery();
                }
                this.f9095a.m12112j();
            } catch (Throwable th) {
                C3833d.m12248a("BLUETOOTH", "Failed to collect bluetooth data: ", th);
            }
        }
    }

    protected C3790d(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "bluetooth_data", "disableBluetoothCollector", false, false);
    }

    private void m12101a(BluetoothDevice bluetoothDevice, boolean z) {
        Object obj = null;
        int type = VERSION.SDK_INT >= 18 ? bluetoothDevice.getType() : 0;
        Iterator it = this.f9097g.iterator();
        while (it.hasNext()) {
            BluetoothDeviceData bluetoothDeviceData = (BluetoothDeviceData) it.next();
            if (bluetoothDeviceData.getAdrress().equals(bluetoothDevice.getAddress()) && z == bluetoothDeviceData.getActiveScanned()) {
                obj = 1;
                break;
            }
        }
        if (obj == null) {
            this.f9097g.add(new BluetoothDeviceData(bluetoothDevice.getName(), bluetoothDevice.getAddress(), bluetoothDevice.getBondState(), type, z));
        }
    }

    private BluetoothData m12111i() {
        this.f9102l = System.currentTimeMillis();
        this.f9097g = new ArrayList();
        this.f9098h = BluetoothAdapter.getDefaultAdapter();
        this.f9099i = this.f9098h.getState();
        for (BluetoothDevice a : this.f9098h.getBondedDevices()) {
            m12101a(a, false);
        }
        if (m12086c() && this.f9099i == 12 && C3843e.m12285a(this.c, "android.permission.BLUETOOTH_ADMIN") && C3843e.m12285a(this.c, "android.permission.ACCESS_COARSE_LOCATION")) {
            IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.FOUND");
            intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
            this.c.getApplicationContext().registerReceiver(this.f9103m, intentFilter);
            this.f9100j = false;
            if (this.f9098h.startDiscovery()) {
                C3833d.m12246a("BLUETOOTH", "Discovery started");
                this.d = false;
                this.f9101k.postDelayed(this.f9104n, 60000);
                return null;
            }
        }
        C3833d.m12246a("BLUETOOTH", "Can't start discovery, Saving paired devices. List size: " + this.f9097g.size());
        return new BluetoothData(this.f9099i, this.f9102l, this.f9097g);
    }

    private void m12112j() {
        try {
            this.c.getApplicationContext().unregisterReceiver(this.f9103m);
        } catch (Throwable th) {
            C3833d.m12246a("BLUETOOTH", "Failed to unregister receiver: " + th.getMessage());
        }
    }

    public String mo6804a() {
        this.d = true;
        if (C3843e.m12285a(this.c, "android.permission.BLUETOOTH")) {
            return m12083a((Object) m12111i());
        }
        C3833d.m12246a("BLUETOOTH", "Don't have permissions to collect bluetooth");
        return "";
    }

    public String[] mo6805b() {
        return f9096f;
    }
}
