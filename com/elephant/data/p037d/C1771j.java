package com.elephant.data.p037d;

import com.elephant.data.p048g.C1814b;

final class C1771j implements Runnable {
    private /* synthetic */ C1770i f3708a;

    C1771j(C1770i c1770i) {
        this.f3708a = c1770i;
    }

    public final void run() {
        if (this.f3708a.f3701a != null) {
            this.f3708a.m5118a(this.f3708a.f3704d, "", C1814b.hF);
            this.f3708a.f3701a.stopLoading();
        }
    }
}
