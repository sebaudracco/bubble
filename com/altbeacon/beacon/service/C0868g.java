package com.altbeacon.beacon.service;

import android.os.SystemClock;
import com.altbeacon.beacon.Beacon;
import com.altbeacon.beacon.C0829b;
import com.altbeacon.beacon.p013c.C0835d;

public class C0868g {
    public static long f1742a = 5000;
    private static long f1743e = 20000;
    protected long f1744b = 0;
    Beacon f1745c;
    protected C0871j f1746d = null;
    private boolean f1747f = true;

    public C0868g(Beacon beacon) {
        try {
            this.f1746d = (C0871j) C0829b.m1621j().getConstructors()[0].newInstance(new Object[0]);
        } catch (Exception e) {
            C0835d.m1663d("RangedBeacon", "Could not construct RssiFilterImplClass %s", C0829b.m1621j().getName());
        }
        m1781a(beacon);
    }

    public void m1781a(Beacon beacon) {
        this.f1745c = beacon;
        m1782a(Integer.valueOf(this.f1745c.m1551g()));
    }

    public void m1782a(Integer num) {
        if (num.intValue() != 127) {
            this.f1747f = true;
            this.f1744b = SystemClock.elapsedRealtime();
            this.f1746d.mo1884a(num);
        }
    }

    public void m1783a(boolean z) {
        this.f1747f = z;
    }

    public boolean m1784a() {
        return this.f1747f;
    }

    public Beacon m1785b() {
        return this.f1745c;
    }

    public void m1786c() {
        if (this.f1746d.mo1885a()) {
            C0835d.m1657a("RangedBeacon", "No measurements available to calculate running average", new Object[0]);
            return;
        }
        this.f1745c.m1542a(this.f1746d.mo1886b());
        C0835d.m1657a("RangedBeacon", "calculated new runningAverageRssi: %s", Double.valueOf(r0));
    }

    public boolean m1787d() {
        return this.f1746d.mo1885a();
    }

    public long m1788e() {
        return SystemClock.elapsedRealtime() - this.f1744b;
    }

    public boolean m1789f() {
        return m1788e() > f1742a;
    }
}
