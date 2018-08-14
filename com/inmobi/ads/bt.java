package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.C3046c.C3044h;
import com.inmobi.ads.ViewableAd.AdEvent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: ViewableNativeV2DisplayAd */
class bt extends ViewableAd {
    @NonNull
    private final ai f7313b;
    private ap f7314c;
    private boolean f7315d = false;
    private final az f7316e;

    bt(@NonNull ai aiVar) {
        super(aiVar);
        this.f7313b = aiVar;
        this.f7316e = new az();
    }

    @Nullable
    public View mo6226a(View view, ViewGroup viewGroup, boolean z) {
        if (this.f7315d) {
            return null;
        }
        Context l = this.f7313b.m9340l();
        if (l == null) {
            return null;
        }
        this.f7314c = new ap(l, this.f7313b, this.f7313b.m9339k());
        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "Ad markup loaded into the container will be inflated into a View.");
        View a = this.f7314c.m9393a(view, viewGroup, true);
        m9103a(a);
        return a;
    }

    public void mo6228a(@NonNull C3044h c3044h, @Nullable View... viewArr) {
        if (this.f7313b.m9332e() != null) {
            this.f7316e.m9458a(this.f7313b.m9332e(), mo6249b(), this.f7313b, c3044h);
        }
    }

    public void mo6229c() {
    }

    public void mo6227a(AdEvent adEvent) {
    }

    public void mo6230d() {
        if (!this.f7315d) {
            this.f7316e.m9459a(this.f7313b.m9332e(), this.f7313b);
            this.f7315d = true;
            this.f7314c.mo6198a();
            super.mo6230d();
        }
    }
}
