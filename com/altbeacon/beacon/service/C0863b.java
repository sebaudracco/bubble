package com.altbeacon.beacon.service;

import android.os.SystemClock;

public class C0863b {
    private static final C0863b f1727a = new C0863b();
    private long f1728b = 0;

    private C0863b() {
    }

    public static C0863b m1752a() {
        return f1727a;
    }

    public long m1753b() {
        return this.f1728b;
    }

    public void m1754c() {
        this.f1728b = SystemClock.elapsedRealtime();
    }
}
