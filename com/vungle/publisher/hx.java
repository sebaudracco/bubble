package com.vungle.publisher;

import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.id.C1636a;
import com.vungle.publisher.jj.a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class hx extends jj<ic, hx, id> {
    @Inject
    C1634a f3002e;

    @Singleton
    /* compiled from: vungle */
    static class C1634a extends a<ic, hx, id, hr, hq> {
        @Inject
        Provider<hx> f3000a;
        @Inject
        C1636a f3001b;

        protected /* synthetic */ da.a m4144a() {
            return m4146d();
        }

        protected /* synthetic */ dp[] m4147d(int i) {
            return m4145c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4148e();
        }

        @Inject
        protected C1634a() {
        }

        protected C1636a m4146d() {
            return this.f3001b;
        }

        protected hx[] m4145c(int i) {
            return new hx[i];
        }

        protected hx m4148e() {
            return (hx) this.f3000a.get();
        }
    }

    protected /* synthetic */ da.a m4149a() {
        return m4151n();
    }

    protected /* synthetic */ C1592a b_() {
        return m4150m();
    }

    @Inject
    protected hx() {
    }

    protected C1634a m4150m() {
        return this.f3002e;
    }

    protected C1636a m4151n() {
        return this.f3002e.m4146d();
    }
}
