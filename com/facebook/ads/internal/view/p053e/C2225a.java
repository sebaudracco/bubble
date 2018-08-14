package com.facebook.ads.internal.view.p053e;

import android.database.ContentObserver;
import android.os.Handler;

class C2225a extends ContentObserver {
    private final C2322d f5419a;

    public C2225a(Handler handler, C2322d c2322d) {
        super(handler);
        this.f5419a = c2322d;
    }

    public boolean deliverSelfNotifications() {
        return false;
    }

    public void onChange(boolean z) {
        this.f5419a.m7330e();
    }
}
