package com.altbeacon.p010a;

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
import com.altbeacon.beacon.p013c.C0835d;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C0811b {
    private boolean f1521a = false;
    private boolean f1522b = false;
    private long f1523c = 0;
    private long f1524d = 0;
    private long f1525e = 0;
    private int f1526f = 0;
    private int f1527g = 0;
    private boolean f1528h = false;
    private long f1529i = 0;
    private Context f1530j = null;
    private C0810a f1531k;
    private final Set<String> f1532l = new HashSet();
    private final BroadcastReceiver f1533m = new C08091(this);

    class C08091 extends BroadcastReceiver {
        final /* synthetic */ C0811b f1520a;

        C08091(C0811b c0811b) {
            this.f1520a = c0811b;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                if (this.f1520a.f1521a) {
                    C0835d.m1657a("BluetoothCrashResolver", "Bluetooth discovery finished", new Object[0]);
                    this.f1520a.m1503g();
                } else {
                    C0835d.m1657a("BluetoothCrashResolver", "Bluetooth discovery finished (external)", new Object[0]);
                }
            }
            if (action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
                if (this.f1520a.f1521a) {
                    this.f1520a.f1522b = true;
                    C0835d.m1657a("BluetoothCrashResolver", "Bluetooth discovery started", new Object[0]);
                } else {
                    C0835d.m1657a("BluetoothCrashResolver", "Bluetooth discovery started (external)", new Object[0]);
                }
            }
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE)) {
                    case Integer.MIN_VALUE:
                        C0835d.m1657a("BluetoothCrashResolver", "Bluetooth state is ERROR", new Object[0]);
                        return;
                    case 10:
                        C0835d.m1657a("BluetoothCrashResolver", "Bluetooth state is OFF", new Object[0]);
                        this.f1520a.f1523c = SystemClock.elapsedRealtime();
                        return;
                    case 11:
                        this.f1520a.f1524d = SystemClock.elapsedRealtime();
                        C0835d.m1657a("BluetoothCrashResolver", "Bluetooth state is TURNING_ON", new Object[0]);
                        return;
                    case 12:
                        C0835d.m1657a("BluetoothCrashResolver", "Bluetooth state is ON", new Object[0]);
                        C0835d.m1657a("BluetoothCrashResolver", "Bluetooth was turned off for %s milliseconds", Long.valueOf(this.f1520a.f1524d - this.f1520a.f1523c));
                        if (this.f1520a.f1524d - this.f1520a.f1523c < 600) {
                            this.f1520a.m1509b();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public interface C0810a {
        void m1492a();
    }

    public C0811b(Context context) {
        this.f1530j = context.getApplicationContext();
        C0835d.m1657a("BluetoothCrashResolver", "constructed", new Object[0]);
        m1505i();
    }

    private int m1499d() {
        return 1590;
    }

    private void m1501e() {
        if (this.f1531k != null) {
            this.f1531k.m1492a();
        }
        if (SystemClock.elapsedRealtime() - this.f1529i > 60000) {
            m1504h();
        }
    }

    @TargetApi(17)
    private void m1502f() {
        this.f1527g++;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        C0835d.m1657a("BluetoothCrashResolver", "about to check if discovery is active", new Object[0]);
        if (defaultAdapter.isDiscovering()) {
            C0835d.m1662c("BluetoothCrashResolver", "Already discovering.  Recovery attempt abandoned.", new Object[0]);
            return;
        }
        C0835d.m1662c("BluetoothCrashResolver", "Recovery attempt started", new Object[0]);
        this.f1521a = true;
        this.f1522b = false;
        C0835d.m1657a("BluetoothCrashResolver", "about to command discovery", new Object[0]);
        if (!defaultAdapter.startDiscovery()) {
            C0835d.m1662c("BluetoothCrashResolver", "Can't start discovery.  Is Bluetooth turned on?", new Object[0]);
        }
        C0835d.m1657a("BluetoothCrashResolver", "startDiscovery commanded.  isDiscovering()=%s", Boolean.valueOf(defaultAdapter.isDiscovering()));
        C0835d.m1657a("BluetoothCrashResolver", "We will be cancelling this discovery in %s milliseconds.", Integer.valueOf(5000));
        m1506j();
    }

    private void m1503g() {
        C0835d.m1662c("BluetoothCrashResolver", "Recovery attempt finished", new Object[0]);
        synchronized (this.f1532l) {
            this.f1532l.clear();
        }
        this.f1521a = false;
    }

    private void m1504h() {
        Throwable th;
        Throwable th2;
        OutputStreamWriter outputStreamWriter = null;
        this.f1529i = SystemClock.elapsedRealtime();
        OutputStreamWriter outputStreamWriter2;
        try {
            outputStreamWriter2 = new OutputStreamWriter(this.f1530j.openFileOutput("BluetoothCrashResolverState.txt", 0));
            try {
                outputStreamWriter2.write(this.f1525e + "\n");
                outputStreamWriter2.write(this.f1526f + "\n");
                outputStreamWriter2.write(this.f1527g + "\n");
                outputStreamWriter2.write(this.f1528h ? "1\n" : "0\n");
                synchronized (this.f1532l) {
                    for (String write : this.f1532l) {
                        outputStreamWriter2.write(write);
                        outputStreamWriter2.write("\n");
                    }
                }
                if (outputStreamWriter2 != null) {
                    try {
                        outputStreamWriter2.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                outputStreamWriter = outputStreamWriter2;
            } catch (Throwable th3) {
                th = th3;
                if (outputStreamWriter2 != null) {
                    try {
                        outputStreamWriter2.close();
                    } catch (IOException e3) {
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            try {
                C0835d.m1662c("BluetoothCrashResolver", "Can't write macs to %s", "BluetoothCrashResolverState.txt");
                if (outputStreamWriter != null) {
                    try {
                        outputStreamWriter.close();
                    } catch (IOException e5) {
                    }
                }
                C0835d.m1657a("BluetoothCrashResolver", "Wrote %s Bluetooth addresses", Integer.valueOf(this.f1532l.size()));
            } catch (Throwable th4) {
                th2 = th4;
                outputStreamWriter2 = outputStreamWriter;
                th = th2;
                if (outputStreamWriter2 != null) {
                    outputStreamWriter2.close();
                }
                throw th;
            }
        } catch (Throwable th42) {
            th2 = th42;
            outputStreamWriter2 = null;
            th = th2;
            if (outputStreamWriter2 != null) {
                outputStreamWriter2.close();
            }
            throw th;
        }
        C0835d.m1657a("BluetoothCrashResolver", "Wrote %s Bluetooth addresses", Integer.valueOf(this.f1532l.size()));
    }

    private void m1505i() {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.f1530j.openFileInput("BluetoothCrashResolverState.txt")));
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    this.f1525e = Long.parseLong(readLine);
                }
                readLine = bufferedReader.readLine();
                if (readLine != null) {
                    this.f1526f = Integer.parseInt(readLine);
                }
                readLine = bufferedReader.readLine();
                if (readLine != null) {
                    this.f1527g = Integer.parseInt(readLine);
                }
                readLine = bufferedReader.readLine();
                if (readLine != null) {
                    this.f1528h = false;
                    if (readLine.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
                        this.f1528h = true;
                    }
                }
                while (true) {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    this.f1532l.add(readLine);
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                try {
                    C0835d.m1662c("BluetoothCrashResolver", "Can't read macs from %s", "BluetoothCrashResolverState.txt");
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                        }
                    }
                    C0835d.m1657a("BluetoothCrashResolver", "Read %s Bluetooth addresses", Integer.valueOf(this.f1532l.size()));
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    bufferedReader2 = bufferedReader;
                    th = th3;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (NumberFormatException e5) {
                C0835d.m1662c("BluetoothCrashResolver", "Can't parse file %s", "BluetoothCrashResolverState.txt");
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e6) {
                    }
                }
                C0835d.m1657a("BluetoothCrashResolver", "Read %s Bluetooth addresses", Integer.valueOf(this.f1532l.size()));
            }
        } catch (IOException e7) {
            bufferedReader = null;
            C0835d.m1662c("BluetoothCrashResolver", "Can't read macs from %s", "BluetoothCrashResolverState.txt");
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            C0835d.m1657a("BluetoothCrashResolver", "Read %s Bluetooth addresses", Integer.valueOf(this.f1532l.size()));
        } catch (NumberFormatException e8) {
            bufferedReader = null;
            C0835d.m1662c("BluetoothCrashResolver", "Can't parse file %s", "BluetoothCrashResolverState.txt");
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            C0835d.m1657a("BluetoothCrashResolver", "Read %s Bluetooth addresses", Integer.valueOf(this.f1532l.size()));
        } catch (Throwable th4) {
            th = th4;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
        C0835d.m1657a("BluetoothCrashResolver", "Read %s Bluetooth addresses", Integer.valueOf(this.f1532l.size()));
    }

    private void m1506j() {
        try {
            Thread.sleep(5000);
            if (!this.f1522b) {
                C0835d.m1662c("BluetoothCrashResolver", "BluetoothAdapter.ACTION_DISCOVERY_STARTED never received.  Recovery may fail.", new Object[0]);
            }
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter.isDiscovering()) {
                C0835d.m1657a("BluetoothCrashResolver", "Cancelling discovery", new Object[0]);
                defaultAdapter.cancelDiscovery();
                return;
            }
            C0835d.m1657a("BluetoothCrashResolver", "Discovery not running.  Won't cancel it", new Object[0]);
        } catch (InterruptedException e) {
            C0835d.m1657a("BluetoothCrashResolver", "DiscoveryCanceller sleep interrupted.", new Object[0]);
        }
    }

    public void m1507a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        this.f1530j.registerReceiver(this.f1533m, intentFilter);
        C0835d.m1657a("BluetoothCrashResolver", "started listening for BluetoothAdapter events", new Object[0]);
    }

    @TargetApi(18)
    public void m1508a(BluetoothDevice bluetoothDevice, LeScanCallback leScanCallback) {
        int size = this.f1532l.size();
        synchronized (this.f1532l) {
            this.f1532l.add(bluetoothDevice.getAddress());
        }
        int size2 = this.f1532l.size();
        if (size != size2 && size2 % 100 == 0) {
            C0835d.m1657a("BluetoothCrashResolver", "Distinct Bluetooth devices seen: %s", Integer.valueOf(this.f1532l.size()));
        }
        if (this.f1532l.size() > m1499d() && !this.f1521a) {
            C0835d.m1662c("BluetoothCrashResolver", "Large number of Bluetooth devices detected: %s Proactively attempting to clear out address list to prevent a crash", Integer.valueOf(this.f1532l.size()));
            C0835d.m1662c("BluetoothCrashResolver", "Stopping LE Scan", new Object[0]);
            BluetoothAdapter.getDefaultAdapter().stopLeScan(leScanCallback);
            m1502f();
            m1501e();
        }
    }

    public void m1509b() {
        if (VERSION.SDK_INT < 18) {
            C0835d.m1657a("BluetoothCrashResolver", "Ignoring crashes before API 18, because BLE is unsupported.", new Object[0]);
            return;
        }
        C0835d.m1662c("BluetoothCrashResolver", "BluetoothService crash detected", new Object[0]);
        if (this.f1532l.size() > 0) {
            C0835d.m1657a("BluetoothCrashResolver", "Distinct Bluetooth devices seen at crash: %s", Integer.valueOf(this.f1532l.size()));
        }
        this.f1525e = SystemClock.elapsedRealtime();
        this.f1526f++;
        if (this.f1521a) {
            C0835d.m1657a("BluetoothCrashResolver", "Ignoring Bluetooth crash because recovery is already in progress.", new Object[0]);
        } else {
            m1502f();
        }
        m1501e();
    }

    public boolean m1510c() {
        return this.f1521a;
    }
}
