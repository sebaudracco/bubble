package com.yandex.metrica.impl;

import android.os.Handler;
import android.os.SystemClock;
import cz.msebera.android.httpclient.HttpStatus;

class C4540w {
    private final Handler f12593a;
    private final C4306b f12594b;
    private final C4541x f12595c;

    C4540w(Handler handler, C4306b c4306b) {
        this.f12593a = handler;
        this.f12594b = c4306b;
        this.f12595c = new C4541x(handler, c4306b);
    }

    void m16302a() {
        C4540w.m16301b(this.f12593a, this.f12594b, this.f12595c);
    }

    void m16303b() {
        C4540w.m16300a(this.f12593a, this.f12594b, this.f12595c);
    }

    static void m16300a(Handler handler, C4306b c4306b, Runnable runnable) {
        C4540w.m16301b(handler, c4306b, runnable);
        handler.postAtTime(runnable, C4540w.m16299a(c4306b), SystemClock.uptimeMillis() + ((long) (c4306b.m14470d().m14714b().m14260d() * HttpStatus.SC_INTERNAL_SERVER_ERROR)));
    }

    private static void m16301b(Handler handler, C4306b c4306b, Runnable runnable) {
        handler.removeCallbacks(runnable, C4540w.m16299a(c4306b));
    }

    private static String m16299a(C4306b c4306b) {
        return c4306b.m14470d().m14714b().m14277j();
    }
}
