package com.vungle.publisher;

import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.hr.C1632a;
import com.vungle.publisher.hx.C1634a;
import com.vungle.publisher.jk.a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ic extends jk<ic, hx, id, hr, hq> {
    @Inject
    C1635a f3006s;
    @Inject
    C1634a f3007w;

    @Singleton
    /* compiled from: vungle */
    public static class C1635a extends a<ic, hx, id, hr, hq, wm> {
        @Inject
        C1634a f3003c;
        @Inject
        C1632a f3004e;
        @Inject
        Provider<ic> f3005f;

        protected /* synthetic */ cn$a m4152a() {
            return m4157g();
        }

        protected /* synthetic */ cy.a m4154d() {
            return m4158h();
        }

        protected /* synthetic */ dp[] m4155d(int i) {
            return m4153c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4159i();
        }

        @Inject
        protected C1635a() {
        }

        public m m4156e() {
            return m.b;
        }

        protected C1632a m4157g() {
            return this.f3004e;
        }

        protected C1634a m4158h() {
            return this.f3003c;
        }

        protected ic[] m4153c(int i) {
            return new ic[i];
        }

        protected ic m4159i() {
            return (ic) this.f3005f.get();
        }
    }

    protected /* synthetic */ cy.a m4162a() {
        return m4161E();
    }

    protected /* synthetic */ C1592a b_() {
        return m4160D();
    }

    @Inject
    protected ic() {
    }

    protected C1635a m4160D() {
        return this.f3006s;
    }

    protected C1634a m4161E() {
        return this.f3007w;
    }
}
