package com.altbeacon.beacon.service.p009a;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.bluetooth.le.ScanSettings.Builder;
import android.content.Context;
import android.os.Handler;
import android.os.ParcelUuid;
import android.os.SystemClock;
import com.altbeacon.beacon.C0829b;
import com.altbeacon.beacon.p013c.C0835d;
import com.altbeacon.beacon.service.C0863b;
import com.altbeacon.p010a.C0811b;
import java.util.ArrayList;
import java.util.List;

@TargetApi(21)
public class C0857d extends C0847b {
    private BluetoothLeScanner f1713k;
    private ScanCallback f1714l;
    private long f1715m = 0;
    private long f1716n = 0;
    private boolean f1717o = false;
    private final C0829b f1718p = C0829b.m1619a(this.c);

    class C08531 implements Runnable {
        final /* synthetic */ C0857d f1703a;

        C08531(C0857d c0857d) {
            this.f1703a = c0857d;
        }

        public void run() {
            this.f1703a.m1714a(Boolean.valueOf(true));
        }
    }

    class C08564 extends ScanCallback {
        final /* synthetic */ C0857d f1712a;

        C08564(C0857d c0857d) {
            this.f1712a = c0857d;
        }

        public void onBatchScanResults(List<ScanResult> list) {
            C0835d.m1657a("CycledLeScannerForLollipop", "got batch records", new Object[0]);
            for (ScanResult scanResult : list) {
                this.f1712a.h.mo1868a(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes());
            }
            if (this.f1712a.f1715m > 0) {
                C0835d.m1657a("CycledLeScannerForLollipop", "got a filtered batch scan result in the background.", new Object[0]);
            }
        }

        public void onScanFailed(int i) {
            switch (i) {
                case 1:
                    C0835d.m1663d("CycledLeScannerForLollipop", "Scan failed: a BLE scan with the same settings is already started by the app", new Object[0]);
                    return;
                case 2:
                    C0835d.m1663d("CycledLeScannerForLollipop", "Scan failed: app cannot be registered", new Object[0]);
                    return;
                case 3:
                    C0835d.m1663d("CycledLeScannerForLollipop", "Scan failed: internal error", new Object[0]);
                    return;
                case 4:
                    C0835d.m1663d("CycledLeScannerForLollipop", "Scan failed: power optimized scan feature is not supported", new Object[0]);
                    return;
                default:
                    C0835d.m1663d("CycledLeScannerForLollipop", "Scan failed with unknown error (errorCode=" + i + ")", new Object[0]);
                    return;
            }
        }

        public void onScanResult(int i, ScanResult scanResult) {
            if (C0835d.m1659a()) {
                C0835d.m1657a("CycledLeScannerForLollipop", "got record", new Object[0]);
                List<ParcelUuid> serviceUuids = scanResult.getScanRecord().getServiceUuids();
                if (serviceUuids != null) {
                    for (ParcelUuid parcelUuid : serviceUuids) {
                        C0835d.m1657a("CycledLeScannerForLollipop", "with service uuid: " + parcelUuid, new Object[0]);
                    }
                }
            }
            this.f1712a.h.mo1868a(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes());
            if (this.f1712a.f1715m > 0) {
                C0835d.m1657a("CycledLeScannerForLollipop", "got a filtered scan result in the background.", new Object[0]);
            }
        }
    }

    public C0857d(Context context, long j, long j2, boolean z, C0804a c0804a, C0811b c0811b) {
        super(context, j, j2, z, c0804a, c0811b);
    }

    private void m1736a(List<ScanFilter> list, ScanSettings scanSettings) {
        final BluetoothLeScanner o = m1739o();
        if (o != null) {
            final ScanCallback p = m1740p();
            this.f.removeCallbacksAndMessages(null);
            final List<ScanFilter> list2 = list;
            final ScanSettings scanSettings2 = scanSettings;
            this.f.post(new Runnable(this) {
                final /* synthetic */ C0857d f1708e;

                public void run() {
                    try {
                        o.startScan(list2, scanSettings2, p);
                    } catch (IllegalStateException e) {
                        C0835d.m1662c("CycledLeScannerForLollipop", "Cannot start scan. Bluetooth may be turned off.", new Object[0]);
                    } catch (Throwable e2) {
                        C0835d.m1661b(e2, "CycledLeScannerForLollipop", "Cannot start scan. Unexpected NPE.", new Object[0]);
                    } catch (SecurityException e3) {
                        C0835d.m1663d("CycledLeScannerForLollipop", "Cannot start scan.  Security Exception", new Object[0]);
                    }
                }
            });
        }
    }

    private void m1737m() {
        if (m1738n()) {
            final BluetoothLeScanner o = m1739o();
            if (o != null) {
                final ScanCallback p = m1740p();
                this.f.removeCallbacksAndMessages(null);
                this.f.post(new Runnable(this) {
                    final /* synthetic */ C0857d f1711c;

                    public void run() {
                        try {
                            C0835d.m1657a("CycledLeScannerForLollipop", "Stopping LE scan on scan handler", new Object[0]);
                            o.stopScan(p);
                        } catch (IllegalStateException e) {
                            C0835d.m1662c("CycledLeScannerForLollipop", "Cannot stop scan. Bluetooth may be turned off.", new Object[0]);
                        } catch (Throwable e2) {
                            C0835d.m1661b(e2, "CycledLeScannerForLollipop", "Cannot stop scan. Unexpected NPE.", new Object[0]);
                        } catch (SecurityException e3) {
                            C0835d.m1663d("CycledLeScannerForLollipop", "Cannot stop scan.  Security Exception", new Object[0]);
                        }
                    }
                });
                return;
            }
            return;
        }
        C0835d.m1657a("CycledLeScannerForLollipop", "Not stopping scan because bluetooth is off", new Object[0]);
    }

    private boolean m1738n() {
        try {
            BluetoothAdapter i = m1723i();
            if (i != null) {
                return i.getState() == 12;
            } else {
                C0835d.m1662c("CycledLeScannerForLollipop", "Cannot get bluetooth adapter", new Object[0]);
                return false;
            }
        } catch (SecurityException e) {
            C0835d.m1662c("CycledLeScannerForLollipop", "SecurityException checking if bluetooth is on", new Object[0]);
            return false;
        }
    }

    private BluetoothLeScanner m1739o() {
        try {
            if (this.f1713k == null) {
                C0835d.m1657a("CycledLeScannerForLollipop", "Making new Android L scanner", new Object[0]);
                if (m1723i() != null) {
                    this.f1713k = m1723i().getBluetoothLeScanner();
                }
                if (this.f1713k == null) {
                    C0835d.m1662c("CycledLeScannerForLollipop", "Failed to make new Android L scanner", new Object[0]);
                }
            }
        } catch (SecurityException e) {
            C0835d.m1662c("CycledLeScannerForLollipop", "SecurityException making new Android L scanner", new Object[0]);
        }
        return this.f1713k;
    }

    private ScanCallback m1740p() {
        if (this.f1714l == null) {
            this.f1714l = new C08564(this);
        }
        return this.f1714l;
    }

    protected void mo1880d() {
        m1737m();
    }

    protected boolean mo1881e() {
        long elapsedRealtime = this.a - SystemClock.elapsedRealtime();
        boolean z = elapsedRealtime > 0;
        boolean z2 = this.f1717o;
        this.f1717o = !z;
        if (z) {
            long elapsedRealtime2 = SystemClock.elapsedRealtime() - C0863b.m1752a().m1753b();
            if (z2) {
                if (elapsedRealtime2 > 10000) {
                    this.f1715m = SystemClock.elapsedRealtime();
                    this.f1716n = 0;
                    C0835d.m1657a("CycledLeScannerForLollipop", "This is Android L. Doing a filtered scan for the background.", new Object[0]);
                    mo1882f();
                } else {
                    C0835d.m1657a("CycledLeScannerForLollipop", "This is Android L, but we last saw a beacon only %s ago, so we will not keep scanning in background.", Long.valueOf(elapsedRealtime2));
                }
            }
            if (this.f1715m > 0 && C0863b.m1752a().m1753b() > this.f1715m) {
                if (this.f1716n == 0) {
                    this.f1716n = C0863b.m1752a().m1753b();
                }
                if (SystemClock.elapsedRealtime() - this.f1716n >= 10000) {
                    C0835d.m1657a("CycledLeScannerForLollipop", "We've been detecting for a bit.  Stopping Android L background scanning", new Object[0]);
                    mo1880d();
                    this.f1715m = 0;
                } else {
                    C0835d.m1657a("CycledLeScannerForLollipop", "Delivering Android L background scanning results", new Object[0]);
                    this.h.mo1867a();
                }
            }
            C0835d.m1657a("CycledLeScannerForLollipop", "Waiting to start full Bluetooth scan for another %s milliseconds", Long.valueOf(elapsedRealtime));
            if (z2 && this.i) {
                m1724j();
            }
            Handler handler = this.e;
            Runnable c08531 = new C08531(this);
            if (elapsedRealtime > 1000) {
                elapsedRealtime = 1000;
            }
            handler.postDelayed(c08531, elapsedRealtime);
        } else if (this.f1715m > 0) {
            mo1880d();
            this.f1715m = 0;
        }
        return z;
    }

    protected void mo1882f() {
        if (m1738n()) {
            ScanSettings build;
            List arrayList = new ArrayList();
            if (!this.i || this.f1717o) {
                C0835d.m1657a("CycledLeScannerForLollipop", "starting non-filtered scan in SCAN_MODE_LOW_LATENCY", new Object[0]);
                build = new Builder().setScanMode(2).build();
            } else {
                C0835d.m1657a("CycledLeScannerForLollipop", "starting filtered scan in SCAN_MODE_LOW_POWER", new Object[0]);
                build = new Builder().setScanMode(0).build();
                arrayList = new C0861g().m1749a(this.f1718p.m1631d());
            }
            m1736a(arrayList, build);
            return;
        }
        C0835d.m1657a("CycledLeScannerForLollipop", "Not starting scan because bluetooth is off", new Object[0]);
    }

    protected void mo1883h() {
        C0835d.m1657a("CycledLeScannerForLollipop", "Stopping scan", new Object[0]);
        mo1880d();
        this.b = true;
    }
}
