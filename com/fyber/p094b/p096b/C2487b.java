package com.fyber.p094b.p096b;

import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.ads.AdFormat;
import com.fyber.ads.C2410a;
import com.fyber.ads.banners.BannerAd;
import com.fyber.ads.banners.p087a.C2420a;
import com.fyber.ads.banners.p087a.C2421b;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.internal.C2425d;
import com.fyber.p094b.C2481a;
import com.fyber.p094b.C2481a.C2475a;
import com.fyber.p094b.C2484h.C2482a;
import com.fyber.p094b.C2505e;
import com.fyber.p094b.p096b.C2485a.C2483a;
import com.fyber.requesters.p097a.C2623c;
import java.util.concurrent.Future;

/* compiled from: BannerFetchOperation */
public final class C2487b extends C2481a<AdFormat, BannerAd, C2420a> {

    /* compiled from: BannerFetchOperation */
    public static class C2486a extends C2475a<C2487b, C2486a> {
        protected final /* bridge */ /* synthetic */ C2475a mo3908a() {
            return this;
        }

        public final C2487b m7904b() {
            return new C2487b();
        }
    }

    @NonNull
    protected final /* synthetic */ C2505e mo3910a(C2410a c2410a) {
        return Fyber.getConfigs().m7606g().m7931a((C2410a) (C2420a) c2410a);
    }

    protected final /* synthetic */ void mo3915b(C2410a c2410a) {
        this.a.m8287c((C2420a) c2410a);
    }

    private C2487b(C2486a c2486a) {
        super(c2486a);
        this.d = true;
    }

    protected final Future<C2420a> mo3912a(C2623c c2623c) {
        return C2489c.m7918a(this.b);
    }

    protected final int mo3909a() {
        return 15;
    }

    protected final void mo3913a(C2425d c2425d) {
        C2421b.m7671a(c2425d);
    }

    protected final void mo3914b() {
        this.a.m8288d(AdFormat.BANNER);
    }

    protected final /* synthetic */ C2482a mo3911a(@NonNull C2423a c2423a) {
        return new C2483a(c2423a);
    }
}
