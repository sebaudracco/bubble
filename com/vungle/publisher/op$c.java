package com.vungle.publisher;

import com.vungle.publisher.log.Logger;

/* compiled from: vungle */
class op$c implements Runnable {
    final /* synthetic */ op f10880a;

    op$c(op opVar) {
        this.f10880a = opVar;
    }

    public void run() {
        try {
            this.f10880a.c(false);
            this.f10880a.l();
            if (!op.c(this.f10880a).get()) {
                this.f10880a.m();
            }
            op.e(this.f10880a).setCurrentTimeMillis(op.d(this.f10880a).getCurrentPosition());
            this.f10880a.h.a(new ar(op.f(this.f10880a)));
        } catch (Throwable e) {
            Logger.m13649w(Logger.AD_TAG, e);
        } finally {
            op.g(this.f10880a).postDelayed(this, 50);
        }
    }
}
