package com.moat.analytics.mobile.inm;

import com.moat.analytics.mobile.inm.base.exception.C3376a;

class C3389o implements Runnable {
    final /* synthetic */ C3388n f8613a;

    C3389o(C3388n c3388n) {
        this.f8613a = c3388n;
    }

    public void run() {
        try {
            this.f8613a.m11611e();
        } catch (Exception e) {
            C3376a.m11557a(e);
        }
    }
}
