package com.vungle.publisher;

import com.vungle.publisher.hs.C1633a;
import com.vungle.publisher.je.a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class im extends je<im, hr, wm, ji, C1633a, wv> {
    @Inject
    C1637a f3012a;

    @Singleton
    /* compiled from: vungle */
    public static class C1637a extends a<im, hr, wm, ji, C1633a, wv> {
        @Inject
        Provider<im> f3010a;
        @Inject
        C1633a f3011b;

        public /* synthetic */ eh.a m4169c() {
            return m4167a();
        }

        protected /* synthetic */ je m4170d() {
            return m4168b();
        }

        @Inject
        C1637a() {
        }

        public C1633a m4167a() {
            return this.f3011b;
        }

        protected im m4168b() {
            return (im) this.f3010a.get();
        }
    }

    protected /* synthetic */ a m4172b() {
        return m4171a();
    }

    @Inject
    im() {
    }

    protected C1637a m4171a() {
        return this.f3012a;
    }
}
