package com.moat.analytics.mobile.inm;

import com.moat.analytics.mobile.inm.base.exception.C3376a;

class C3383g implements Runnable {
    final /* synthetic */ C3372f f8596a;

    C3383g(C3372f c3372f) {
        this.f8596a = c3372f;
    }

    public void run() {
        try {
            this.f8596a.m11492a("Shutting down.");
            this.f8596a.f8532l.m11478b();
            this.f8596a.f8532l = null;
        } catch (Exception e) {
            C3376a.m11557a(e);
        }
    }
}
