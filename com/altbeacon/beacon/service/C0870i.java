package com.altbeacon.beacon.service;

import android.os.SystemClock;
import com.altbeacon.beacon.C0829b;
import com.altbeacon.beacon.p013c.C0835d;
import java.io.Serializable;

public class C0870i implements Serializable {
    private static final String f1750a = C0870i.class.getSimpleName();
    private boolean f1751b = false;
    private long f1752c = 0;
    private final C0862a f1753d;

    public C0870i(C0862a c0862a) {
        this.f1753d = c0862a;
    }

    public C0862a m1794a() {
        return this.f1753d;
    }

    public boolean m1795b() {
        this.f1752c = SystemClock.elapsedRealtime();
        if (this.f1751b) {
            return false;
        }
        this.f1751b = true;
        return true;
    }

    public void m1796c() {
        this.f1751b = false;
        this.f1752c = 0;
    }

    public boolean m1797d() {
        if (!this.f1751b || this.f1752c <= 0 || SystemClock.elapsedRealtime() - this.f1752c <= C0829b.m1618a()) {
            return false;
        }
        C0835d.m1657a(f1750a, "We are newly outside the region because the lastSeenTime of %s was %s seconds ago, and that is over the expiration duration of %s", Long.valueOf(this.f1752c), Long.valueOf(SystemClock.elapsedRealtime() - this.f1752c), Long.valueOf(C0829b.m1618a()));
        m1796c();
        return true;
    }

    public boolean m1798e() {
        return this.f1751b;
    }
}
