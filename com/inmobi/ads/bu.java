package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.C3046c.C3044h;
import com.inmobi.ads.ViewableAd.AdEvent;

/* compiled from: ViewableNativeV2VideoAd */
class bu extends ViewableAd {
    @NonNull
    private final au f7317b;
    private ap f7318c;
    private boolean f7319d = false;
    private final az f7320e;

    bu(@NonNull au auVar) {
        super(auVar);
        this.f7317b = auVar;
        this.f7320e = new az();
    }

    @Nullable
    public View mo6226a(View view, ViewGroup viewGroup, boolean z) {
        if (this.f7319d) {
            return null;
        }
        Context l = this.f7317b.m9340l();
        if (l == null) {
            return null;
        }
        this.f7318c = new ap(l, this.f7317b, this.f7317b.m9339k());
        View a = this.f7318c.m9393a(view, viewGroup, false);
        m9103a(a);
        this.f7317b.m9416B();
        return a;
    }

    public void mo6228a(@NonNull C3044h c3044h, @Nullable View... viewArr) {
        if (this.f7317b.m9332e() != null) {
            this.f7320e.m9458a(this.f7317b.m9332e(), mo6249b(), this.f7317b, c3044h);
        }
    }

    public void mo6229c() {
    }

    public void mo6227a(AdEvent adEvent) {
    }

    public void mo6230d() {
        if (!this.f7319d) {
            this.f7320e.m9459a(this.f7317b.m9332e(), this.f7317b);
            this.f7319d = true;
            this.f7318c.mo6198a();
            super.mo6230d();
        }
    }
}
