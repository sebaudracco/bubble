package com.moat.analytics.mobile.inm;

import com.moat.analytics.mobile.inm.base.exception.C3376a;

class C3394t implements Runnable {
    final /* synthetic */ C3393s f8618a;

    C3394t(C3393s c3393s) {
        this.f8618a = c3393s;
    }

    public void run() {
        try {
            this.f8618a.f8617a.m11607c();
        } catch (Exception e) {
            C3376a.m11557a(e);
        }
    }
}
