package com.moat.analytics.mobile.inm;

import android.os.Handler;
import android.os.Looper;
import com.moat.analytics.mobile.inm.base.exception.C3376a;

class C3393s implements Runnable {
    final /* synthetic */ C3388n f8617a;

    C3393s(C3388n c3388n) {
        this.f8617a = c3388n;
    }

    public void run() {
        try {
            new Handler(Looper.getMainLooper()).post(new C3394t(this));
        } catch (Exception e) {
            C3376a.m11557a(e);
        }
    }
}
