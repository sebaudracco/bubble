package com.moat.analytics.mobile.inm;

import com.moat.analytics.mobile.inm.base.exception.C3376a;

class av implements Runnable {
    final /* synthetic */ aq f8559a;
    final /* synthetic */ au f8560b;

    av(au auVar, aq aqVar) {
        this.f8560b = auVar;
        this.f8559a = aqVar;
    }

    public void run() {
        try {
            this.f8560b.f8557d.mo6485a(this.f8559a);
        } catch (Exception e) {
            C3376a.m11557a(e);
        }
    }
}
