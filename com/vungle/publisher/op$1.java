package com.vungle.publisher;

import com.vungle.publisher.log.Logger;

/* compiled from: vungle */
class op$1 implements mq$a {
    final /* synthetic */ op f10878a;

    op$1(op opVar) {
        this.f10878a = opVar;
    }

    public void mo6950a() {
        m13796d();
    }

    public void mo6951b() {
        Logger.m13635d(Logger.AD_TAG, "cancel video");
        op.a(this.f10878a);
    }

    public void mo6952c() {
        m13796d();
    }

    private void m13796d() {
        this.f10878a.onResume();
        op.b(this.f10878a).set(false);
    }
}
