package com.yandex.metrica.impl;

import android.os.Handler;
import java.lang.ref.WeakReference;

class C4541x implements Runnable {
    private final WeakReference<Handler> f12596a;
    private final WeakReference<C4306b> f12597b;

    C4541x(Handler handler, C4306b c4306b) {
        this.f12596a = new WeakReference(handler);
        this.f12597b = new WeakReference(c4306b);
    }

    public void run() {
        Handler handler = (Handler) this.f12596a.get();
        C4306b c4306b = (C4306b) this.f12597b.get();
        if (handler != null && c4306b != null && c4306b.m14469c()) {
            C4540w.m16300a(handler, c4306b, this);
        }
    }
}
