package com.fyber.ads;

import android.app.Activity;
import com.fyber.ads.internal.C2419b;

public abstract class Ad<V extends Ad<V, U>, U> {
    protected C2419b<U> f6007a;
    protected String f6008b;

    public abstract boolean canStart();

    public abstract AdFormat getAdFormat();

    public abstract void start(Activity activity);

    public Ad(String str, C2419b<U> c2419b) {
        this.f6008b = str;
        this.f6007a = c2419b;
    }

    public V withListener(U u) {
        this.f6007a.mo3847a(u);
        return this;
    }

    public String getPlacementId() {
        return this.f6008b;
    }
}
