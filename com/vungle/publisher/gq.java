package com.vungle.publisher;

import com.vungle.publisher.cy.a;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.gw.C1629a;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class gq extends cy<gv, gq, gw> {
    @Inject
    C1627a f2978e;

    @Singleton
    /* compiled from: vungle */
    public static class C1627a extends a<gv, gq, gw> {
        @Inject
        Provider<gq> f2976a;
        @Inject
        C1629a f2977b;

        public /* bridge */ /* synthetic */ int m4058a(List list) {
            return super.a(list);
        }

        protected /* synthetic */ da.a m4059a() {
            return m4062d();
        }

        public /* bridge */ /* synthetic */ List m4060a(String str, String[] strArr) {
            return super.a(str, strArr);
        }

        protected /* synthetic */ dp[] m4063d(int i) {
            return m4061c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4064e();
        }

        @Inject
        C1627a() {
        }

        protected C1629a m4062d() {
            return this.f2977b;
        }

        protected gq m4064e() {
            return (gq) this.f2976a.get();
        }

        protected gq[] m4061c(int i) {
            return new gq[i];
        }
    }

    protected /* synthetic */ da.a m4065a() {
        return m4066m();
    }

    protected /* synthetic */ C1592a b_() {
        return m4067n();
    }

    @Inject
    gq() {
    }

    protected C1629a m4066m() {
        return this.f2978e.m4062d();
    }

    protected C1627a m4067n() {
        return this.f2978e;
    }
}
