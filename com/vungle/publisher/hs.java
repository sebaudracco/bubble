package com.vungle.publisher;

import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.ji.b;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class hs extends ji {
    @Inject
    C1633a f2999d;

    @Singleton
    /* compiled from: vungle */
    static class C1633a extends b<wm> {
        @Inject
        Provider<hs> f2998a;

        protected /* synthetic */ dp g_() {
            return m4142a();
        }

        @Inject
        C1633a() {
        }

        protected hs m4142a() {
            return (hs) this.f2998a.get();
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m4143e();
    }

    @Inject
    hs() {
    }

    protected C1633a m4143e() {
        return this.f2999d;
    }
}
