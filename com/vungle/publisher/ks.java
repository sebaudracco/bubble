package com.vungle.publisher;

import com.vungle.publisher.je.a;
import com.vungle.publisher.jy.C1645a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ks extends je<ks, jn, wr, jy, C1645a, vu> {
    @Inject
    C1649a f3068a;

    @Singleton
    /* compiled from: vungle */
    public static class C1649a extends a<ks, jn, wr, jy, C1645a, vu> {
        @Inject
        Provider<ks> f3066a;
        @Inject
        C1645a f3067b;

        public /* synthetic */ eh.a m4322c() {
            return m4320a();
        }

        protected /* synthetic */ je m4323d() {
            return m4321b();
        }

        @Inject
        C1649a() {
        }

        public C1645a m4320a() {
            return this.f3067b;
        }

        protected ks m4321b() {
            return (ks) this.f3066a.get();
        }
    }

    protected /* synthetic */ a m4325b() {
        return m4324a();
    }

    @Inject
    ks() {
    }

    protected C1649a m4324a() {
        return this.f3068a;
    }
}
