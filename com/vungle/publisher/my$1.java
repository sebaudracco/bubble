package com.vungle.publisher;

import com.vungle.publisher.log.Logger;

/* compiled from: vungle */
class my$1 implements mq$a {
    final /* synthetic */ my f10770a;

    my$1(my myVar) {
        this.f10770a = myVar;
    }

    public void mo6950a() {
        this.f10770a.u.a(this.f10770a.f, true);
    }

    public void mo6951b() {
        Logger.m13635d(Logger.AD_TAG, "cancel video");
        this.f10770a.u.a(this.f10770a.f, false);
    }

    public void mo6952c() {
        this.f10770a.u.a(this.f10770a.f, true);
    }
}
