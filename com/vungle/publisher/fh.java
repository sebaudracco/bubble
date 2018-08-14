package com.vungle.publisher;

import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.jl.b;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class fh extends jl<fb> {
    @Inject
    C1622a f2960e;

    @Singleton
    /* compiled from: vungle */
    static class C1622a extends b<fh, fb> {
        @Inject
        Provider<fh> f2959a;

        protected /* synthetic */ dp[] m4005d(int i) {
            return m4004c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4003a();
        }

        @Inject
        protected C1622a() {
        }

        protected fh[] m4004c(int i) {
            return new fh[i];
        }

        protected fh m4003a() {
            return (fh) this.f2959a.get();
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m4006m();
    }

    @Inject
    protected fh() {
    }

    protected C1622a m4006m() {
        return this.f2960e;
    }
}
