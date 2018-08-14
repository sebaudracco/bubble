package com.yandex.metrica.impl;

import android.os.SystemClock;

class C4512q {
    private long f12566a = (SystemClock.elapsedRealtime() - 2000000);
    private boolean f12567b = true;

    C4512q() {
    }

    boolean m16211a() {
        boolean z = this.f12567b;
        this.f12567b = false;
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.f12566a;
        if (!z || elapsedRealtime <= 1000) {
            return false;
        }
        return true;
    }

    void m16212b() {
        this.f12567b = true;
        this.f12566a = SystemClock.elapsedRealtime();
    }

    boolean m16213c() {
        return this.f12567b;
    }
}
