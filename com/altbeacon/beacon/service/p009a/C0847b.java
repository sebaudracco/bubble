package com.altbeacon.beacon.service.p009a;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import com.altbeacon.beacon.C0829b;
import com.altbeacon.beacon.p013c.C0835d;
import com.altbeacon.beacon.startup.StartupBroadcastReceiver;
import com.altbeacon.p010a.C0811b;
import com.stepleaderdigital.reveal.Reveal;
import java.util.Date;
import org.altbeacon.beacon.service.scanner.CycledLeScanner;

@TargetApi(18)
public abstract class C0847b {
    protected long f1671a = 0;
    protected boolean f1672b;
    protected final Context f1673c;
    protected long f1674d;
    protected final Handler f1675e = new Handler(Looper.getMainLooper());
    protected final Handler f1676f;
    protected final C0811b f1677g;
    protected final C0804a f1678h;
    protected boolean f1679i = false;
    protected boolean f1680j = false;
    private BluetoothAdapter f1681k;
    private long f1682l = 0;
    private long f1683m = 0;
    private long f1684n = 0;
    private long f1685o = 0;
    private boolean f1686p = false;
    private boolean f1687q;
    private boolean f1688r = false;
    private boolean f1689s = false;
    private long f1690t;
    private final HandlerThread f1691u;
    private volatile boolean f1692v = false;
    private PendingIntent f1693w = null;

    class C08461 implements Runnable {
        final /* synthetic */ C0847b f1670a;

        C08461(C0847b c0847b) {
            this.f1670a = c0847b;
        }

        public void run() {
            this.f1670a.m1721g();
        }
    }

    protected C0847b(Context context, long j, long j2, boolean z, C0804a c0804a, C0811b c0811b) {
        this.f1690t = j;
        this.f1674d = j2;
        this.f1673c = context;
        this.f1678h = c0804a;
        this.f1677g = c0811b;
        this.f1679i = z;
        this.f1691u = new HandlerThread("CycledLeScannerThread");
        this.f1691u.start();
        this.f1676f = new Handler(this.f1691u.getLooper());
    }

    public static C0847b m1706a(Context context, long j, long j2, boolean z, C0804a c0804a, C0811b c0811b) {
        if (VERSION.SDK_INT < 18) {
            C0835d.m1662c("CycledLeScanner", "Not supported prior to API 18.", new Object[0]);
            return null;
        }
        Object obj;
        if (VERSION.SDK_INT < 21) {
            C0835d.m1660b("CycledLeScanner", "This is not Android 5.0.  We are using old scanning APIs", new Object[0]);
            obj = null;
        } else if (C0829b.m1622m()) {
            C0835d.m1660b("CycledLeScanner", "This Android 5.0, but L scanning is disabled. We are using old scanning APIs", new Object[0]);
            obj = null;
        } else {
            C0835d.m1660b("CycledLeScanner", "This Android 5.0.  We are using new scanning APIs", new Object[0]);
            obj = 1;
        }
        return obj != null ? new C0857d(context, j, j2, z, c0804a, c0811b) : new C0852c(context, j, j2, z, c0804a, c0811b);
    }

    private boolean m1707a(String str) {
        return this.f1673c.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    private void m1708m() {
        C0835d.m1657a("CycledLeScanner", "Done with scan cycle", new Object[0]);
        try {
            this.f1678h.mo1867a();
            if (this.f1687q) {
                if (m1723i() != null) {
                    if (m1723i().isEnabled()) {
                        if (this.f1692v && this.f1674d == 0 && !m1711p()) {
                            C0835d.m1657a("CycledLeScanner", "Not stopping scanning.  Device capable of multiple indistinct detections per scan.", new Object[0]);
                        } else {
                            long elapsedRealtime = SystemClock.elapsedRealtime();
                            if (VERSION.SDK_INT < 24 || this.f1674d + this.f1690t >= 6000 || elapsedRealtime - this.f1682l >= 6000) {
                                try {
                                    C0835d.m1657a("CycledLeScanner", "stopping bluetooth le scan", new Object[0]);
                                    mo1883h();
                                } catch (Throwable e) {
                                    C0835d.m1658a(e, "CycledLeScanner", "Internal Android exception scanning for beacons", new Object[0]);
                                }
                            } else {
                                C0835d.m1657a("CycledLeScanner", "Not stopping scan because this is Android N and we keep scanning for a minimum of 6 seconds at a time. We will stop in " + (6000 - (elapsedRealtime - this.f1682l)) + " millisconds.", new Object[0]);
                            }
                        }
                        this.f1683m = SystemClock.elapsedRealtime();
                    } else {
                        C0835d.m1657a("CycledLeScanner", "Bluetooth is disabled.  Cannot scan for beacons.", new Object[0]);
                        this.f1680j = true;
                    }
                }
                this.f1671a = m1709n();
                if (this.f1689s) {
                    m1714a(Boolean.valueOf(true));
                }
            }
            if (!this.f1689s) {
                C0835d.m1657a("CycledLeScanner", "Scanning disabled.  No ranging or monitoring regions are active.", new Object[0]);
                this.f1688r = false;
                m1726l();
            }
        } catch (SecurityException e2) {
            C0835d.m1662c("CycledLeScanner", "SecurityException working accessing bluetooth.", new Object[0]);
        }
    }

    private long m1709n() {
        if (this.f1674d == 0) {
            return SystemClock.elapsedRealtime();
        }
        C0835d.m1657a("CycledLeScanner", "Normalizing between scan period from %s to %s", Long.valueOf(this.f1674d), Long.valueOf(this.f1674d - (SystemClock.elapsedRealtime() % (this.f1690t + this.f1674d))));
        return (this.f1674d - (SystemClock.elapsedRealtime() % (this.f1690t + this.f1674d))) + SystemClock.elapsedRealtime();
    }

    private boolean m1710o() {
        return m1707a("android.permission.ACCESS_COARSE_LOCATION") || m1707a("android.permission.ACCESS_FINE_LOCATION");
    }

    private boolean m1711p() {
        boolean z = VERSION.SDK_INT >= 24 && this.f1685o > 0 && ((SystemClock.elapsedRealtime() + this.f1674d) + this.f1690t) - this.f1685o > CycledLeScanner.ANDROID_N_MAX_SCAN_DURATION_MILLIS;
        if (z) {
            C0835d.m1657a("CycledLeScanner", "The next scan cycle would go over the Android N max duration.", new Object[0]);
            if (this.f1686p) {
                C0835d.m1657a("CycledLeScanner", "Stopping scan to prevent Android N scan timeout.", new Object[0]);
                return true;
            }
            C0835d.m1662c("CycledLeScanner", "Allowing a long running scan to be stopped by the OS.  To prevent this, set longScanForcingEnabled in the AndroidBeaconLibrary.", new Object[0]);
        }
        return false;
    }

    public void m1712a() {
        C0835d.m1657a("CycledLeScanner", "start called", new Object[0]);
        this.f1689s = true;
        if (this.f1688r) {
            C0835d.m1657a("CycledLeScanner", "scanning already started", new Object[0]);
        } else {
            m1714a(Boolean.valueOf(true));
        }
    }

    public void m1713a(long j, long j2, boolean z) {
        C0835d.m1657a("CycledLeScanner", "Set scan periods called with %s, %s Background mode must have changed.", Long.valueOf(j), Long.valueOf(j2));
        if (this.f1679i != z) {
            this.f1680j = true;
        }
        this.f1679i = z;
        this.f1690t = j;
        this.f1674d = j2;
        if (this.f1679i) {
            C0835d.m1657a("CycledLeScanner", "We are in the background.  Setting wakeup alarm", new Object[0]);
            m1724j();
        } else {
            C0835d.m1657a("CycledLeScanner", "We are not in the background.  Cancelling wakeup alarm", new Object[0]);
            m1726l();
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f1671a > elapsedRealtime) {
            long j3 = this.f1683m + j2;
            if (j3 < this.f1671a) {
                this.f1671a = j3;
                C0835d.m1660b("CycledLeScanner", "Adjusted nextScanStartTime to be %s", new Date((this.f1671a - SystemClock.elapsedRealtime()) + System.currentTimeMillis()));
            }
        }
        if (this.f1684n > elapsedRealtime) {
            elapsedRealtime = this.f1682l + j;
            if (elapsedRealtime < this.f1684n) {
                this.f1684n = elapsedRealtime;
                C0835d.m1660b("CycledLeScanner", "Adjusted scanStopTime to be %s", Long.valueOf(this.f1684n));
            }
        }
    }

    protected void m1714a(Boolean bool) {
        try {
            this.f1688r = true;
            if (m1723i() == null) {
                C0835d.m1663d("CycledLeScanner", "No Bluetooth adapter.  beaconService cannot scan.", new Object[0]);
            }
            if (!this.f1689s || !bool.booleanValue()) {
                C0835d.m1657a("CycledLeScanner", "disabling scan", new Object[0]);
                this.f1687q = false;
                this.f1688r = false;
                mo1880d();
                this.f1685o = 0;
                this.f1683m = SystemClock.elapsedRealtime();
                this.f1676f.removeCallbacksAndMessages(null);
                m1708m();
            } else if (!mo1881e()) {
                C0835d.m1657a("CycledLeScanner", "starting a new scan cycle", new Object[0]);
                if (!this.f1687q || this.f1672b || this.f1680j) {
                    this.f1687q = true;
                    this.f1672b = false;
                    try {
                        if (m1723i() != null) {
                            if (m1723i().isEnabled()) {
                                if (this.f1677g != null && this.f1677g.m1510c()) {
                                    C0835d.m1662c("CycledLeScanner", "Skipping scan because crash recovery is in progress.", new Object[0]);
                                } else if (this.f1689s) {
                                    if (this.f1680j) {
                                        this.f1680j = false;
                                        C0835d.m1657a("CycledLeScanner", "restarting a bluetooth le scan", new Object[0]);
                                    } else {
                                        C0835d.m1657a("CycledLeScanner", "starting a new bluetooth le scan", new Object[0]);
                                    }
                                    try {
                                        if (VERSION.SDK_INT < 23 || m1710o()) {
                                            this.f1685o = SystemClock.elapsedRealtime();
                                            mo1882f();
                                        }
                                    } catch (Throwable e) {
                                        C0835d.m1661b(e, "CycledLeScanner", "Internal Android exception scanning for beacons", new Object[0]);
                                    }
                                } else {
                                    C0835d.m1657a("CycledLeScanner", "Scanning unnecessary - no monitoring or ranging active.", new Object[0]);
                                }
                                this.f1682l = SystemClock.elapsedRealtime();
                            } else {
                                C0835d.m1657a("CycledLeScanner", "Bluetooth is disabled.  Cannot scan for beacons.", new Object[0]);
                            }
                        }
                    } catch (Throwable e2) {
                        C0835d.m1661b(e2, "CycledLeScanner", "Exception starting Bluetooth scan.  Perhaps Bluetooth is disabled or unavailable?", new Object[0]);
                    }
                } else {
                    C0835d.m1657a("CycledLeScanner", "We are already scanning and have been for " + (SystemClock.elapsedRealtime() - this.f1685o) + " millis", new Object[0]);
                }
                this.f1684n = SystemClock.elapsedRealtime() + this.f1690t;
                m1721g();
                C0835d.m1657a("CycledLeScanner", "Scan started", new Object[0]);
            }
        } catch (SecurityException e3) {
            C0835d.m1662c("CycledLeScanner", "SecurityException working accessing bluetooth.", new Object[0]);
        }
    }

    public void m1715a(boolean z) {
        this.f1692v = z;
    }

    public void m1716b() {
        C0835d.m1657a("CycledLeScanner", "stop called", new Object[0]);
        this.f1689s = false;
        if (this.f1688r) {
            m1714a(Boolean.valueOf(false));
        } else {
            C0835d.m1657a("CycledLeScanner", "scanning already stopped", new Object[0]);
        }
    }

    public boolean m1717c() {
        return this.f1692v;
    }

    protected abstract void mo1880d();

    protected abstract boolean mo1881e();

    protected abstract void mo1882f();

    protected void m1721g() {
        long j = 1000;
        long elapsedRealtime = this.f1684n - SystemClock.elapsedRealtime();
        if (!this.f1689s || elapsedRealtime <= 0) {
            m1708m();
            return;
        }
        C0835d.m1657a("CycledLeScanner", "Waiting to stop scan cycle for another %s milliseconds", Long.valueOf(elapsedRealtime));
        if (this.f1679i) {
            m1724j();
        }
        Handler handler = this.f1675e;
        Runnable c08461 = new C08461(this);
        if (elapsedRealtime <= 1000) {
            j = elapsedRealtime;
        }
        handler.postDelayed(c08461, j);
    }

    protected abstract void mo1883h();

    protected BluetoothAdapter m1723i() {
        try {
            if (this.f1681k == null) {
                this.f1681k = ((BluetoothManager) this.f1673c.getApplicationContext().getSystemService(Reveal.STATUS_BLUETOOTH)).getAdapter();
                if (this.f1681k == null) {
                    C0835d.m1662c("CycledLeScanner", "Failed to construct a BluetoothAdapter", new Object[0]);
                }
            }
        } catch (SecurityException e) {
            C0835d.m1663d("CycledLeScanner", "Cannot consruct bluetooth adapter.  Security Exception", new Object[0]);
        }
        return this.f1681k;
    }

    protected void m1724j() {
        long j = 300000;
        if (300000 < this.f1674d) {
            j = this.f1674d;
        }
        ((AlarmManager) this.f1673c.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(2, SystemClock.elapsedRealtime() + (j < this.f1690t ? this.f1690t : j), m1725k());
        C0835d.m1657a("CycledLeScanner", "Set a wakeup alarm to go off in %s ms: %s", Long.valueOf(r2), m1725k());
    }

    protected PendingIntent m1725k() {
        if (this.f1693w == null) {
            Intent intent = new Intent(this.f1673c, StartupBroadcastReceiver.class);
            intent.putExtra("wakeup", true);
            this.f1693w = PendingIntent.getBroadcast(this.f1673c, 0, intent, 134217728);
        }
        return this.f1693w;
    }

    protected void m1726l() {
        C0835d.m1657a("CycledLeScanner", "cancel wakeup alarm: %s", this.f1693w);
        ((AlarmManager) this.f1673c.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(2, Long.MAX_VALUE, m1725k());
        C0835d.m1657a("CycledLeScanner", "Set a wakeup alarm to go off in %s ms: %s", Long.valueOf(Long.MAX_VALUE - SystemClock.elapsedRealtime()), m1725k());
    }
}
