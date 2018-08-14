package com.yandex.metrica.impl.utils;

import android.content.Context;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.cc;

public class C4531k {
    private volatile long f12585a;
    private cc f12586b;

    private static class C4530a {
        static C4531k f12584a = new C4531k();
    }

    public static C4531k m16287a() {
        return C4530a.f12584a;
    }

    private C4531k() {
    }

    public synchronized long m16290b() {
        return this.f12585a;
    }

    public synchronized void m16289a(Context context) {
        if (context != null) {
            this.f12586b = new cc(bp.m15358a(context).m15364b());
            this.f12585a = this.f12586b.m15500c(0);
        }
    }

    public synchronized void m16288a(long j) {
        this.f12585a = (j - System.currentTimeMillis()) / 1000;
        if (this.f12586b != null) {
            this.f12586b.m15493a(this.f12585a);
            this.f12586b.m15415h();
        }
    }
}
