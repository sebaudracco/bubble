package com.fyber.p094b.p099c;

import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.ads.AdFormat;
import com.fyber.ads.C2410a;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.internal.C2425d;
import com.fyber.ads.interstitials.InterstitialAd;
import com.fyber.ads.interstitials.p091b.C2440a;
import com.fyber.ads.interstitials.p091b.C2441b;
import com.fyber.p094b.C2481a;
import com.fyber.p094b.C2481a.C2475a;
import com.fyber.p094b.C2484h.C2482a;
import com.fyber.p094b.C2505e;
import com.fyber.p094b.p099c.C2495a.C2494a;
import com.fyber.requesters.p097a.C2623c;
import java.util.concurrent.Future;

/* compiled from: InterstitialFetchOperation */
public final class C2497b extends C2481a<AdFormat, InterstitialAd, C2440a> {

    /* compiled from: InterstitialFetchOperation */
    public static class C2496a extends C2475a<C2497b, C2496a> {
        protected final /* bridge */ /* synthetic */ C2475a mo3908a() {
            return this;
        }

        public final C2497b m7948b() {
            return new C2497b();
        }
    }

    @NonNull
    protected final /* synthetic */ C2505e mo3910a(C2410a c2410a) {
        return Fyber.getConfigs().m7605f().m7931a((C2410a) (C2440a) c2410a);
    }

    protected final /* synthetic */ void mo3915b(C2410a c2410a) {
        this.a.m8287c((C2440a) c2410a);
    }

    private C2497b(C2496a c2496a) {
        super(c2496a);
    }

    protected final Future<C2440a> mo3912a(C2623c c2623c) {
        return C2498c.m7956a(c2623c);
    }

    protected final int mo3909a() {
        return 10;
    }

    protected final void mo3913a(C2425d c2425d) {
        C2441b.m7759a(c2425d);
    }

    protected final void mo3914b() {
        this.a.m8288d(AdFormat.INTERSTITIAL);
    }

    protected final /* synthetic */ C2482a mo3911a(@NonNull C2423a c2423a) {
        return new C2494a(c2423a);
    }
}
