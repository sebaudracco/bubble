package com.moat.analytics.mobile.inm;

import com.moat.analytics.mobile.inm.base.exception.C3376a;

class C3392r implements Runnable {
    final /* synthetic */ C3391q f8616a;

    C3392r(C3391q c3391q) {
        this.f8616a = c3391q;
    }

    public void run() {
        try {
            this.f8616a.f8615a.m11605b();
        } catch (Exception e) {
            C3376a.m11557a(e);
        }
    }
}
