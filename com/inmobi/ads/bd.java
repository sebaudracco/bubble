package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.ads.C3046c.C3044h;
import com.inmobi.ads.bv.C3024a;

/* compiled from: PollingVisibilityTracker */
class bd extends bv {
    private static final String f7248b = bd.class.getSimpleName();
    @Nullable
    private C3044h f7249c;

    bd(@Nullable C3044h c3044h) {
        this.f7249c = c3044h;
    }

    bd(@NonNull C3024a c3024a, @Nullable C3044h c3044h) {
        super(c3024a);
        this.f7249c = c3044h;
    }

    protected int mo6211a() {
        return this.f7249c == null ? 100 : this.f7249c.m9700e();
    }

    protected void mo6212b() {
        m9500i();
    }
}
