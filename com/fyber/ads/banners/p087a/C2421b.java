package com.fyber.ads.banners.p087a;

import com.fyber.ads.internal.C2425d;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: BannerClient */
public final class C2421b {
    public static C2421b f6039a = new C2421b();
    private AtomicReference<C2425d> f6040b = new AtomicReference(C2425d.READY_TO_CHECK_OFFERS);

    public static boolean m7671a(C2425d c2425d) {
        f6039a.f6040b.getAndSet(c2425d);
        return true;
    }

    public static C2425d m7670a() {
        return (C2425d) f6039a.f6040b.get();
    }
}
