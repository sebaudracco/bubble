package com.moat.analytics.mobile.inm;

import android.os.Handler;
import android.os.Looper;
import com.moat.analytics.mobile.inm.base.exception.C3376a;

class C3391q implements Runnable {
    final /* synthetic */ C3388n f8615a;

    C3391q(C3388n c3388n) {
        this.f8615a = c3388n;
    }

    public void run() {
        try {
            new Handler(Looper.getMainLooper()).post(new C3392r(this));
        } catch (Exception e) {
            C3376a.m11557a(e);
        }
    }
}
