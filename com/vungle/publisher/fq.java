package com.vungle.publisher;

import com.vungle.publisher.ew.C1618a;
import com.vungle.publisher.je.a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class fq extends je<fq, el, wg, ji, C1618a, wv> {
    @Inject
    C1623a f2963a;

    @Singleton
    /* compiled from: vungle */
    public static class C1623a extends a<fq, el, wg, ji, C1618a, wv> {
        @Inject
        Provider<fq> f2961a;
        @Inject
        C1618a f2962b;

        public /* synthetic */ eh.a m4009c() {
            return m4007a();
        }

        protected /* synthetic */ je m4010d() {
            return m4008b();
        }

        @Inject
        C1623a() {
        }

        public C1618a m4007a() {
            return this.f2962b;
        }

        protected fq m4008b() {
            return (fq) this.f2961a.get();
        }
    }

    protected /* synthetic */ a m4012b() {
        return m4011a();
    }

    @Inject
    fq() {
    }

    protected C1623a m4011a() {
        return this.f2963a;
    }
}
