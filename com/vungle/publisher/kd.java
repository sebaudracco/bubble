package com.vungle.publisher;

import com.vungle.publisher.cy.a;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.kj.C1648a;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class kd extends cy<ki, kd, kj> {
    @Inject
    C1646a f3056e;

    @Singleton
    /* compiled from: vungle */
    public static class C1646a extends a<ki, kd, kj> {
        @Inject
        Provider<kd> f3054a;
        @Inject
        C1648a f3055b;

        public /* bridge */ /* synthetic */ int m4283a(List list) {
            return super.a(list);
        }

        protected /* synthetic */ da.a m4284a() {
            return m4287d();
        }

        public /* bridge */ /* synthetic */ List m4285a(String str, String[] strArr) {
            return super.a(str, strArr);
        }

        protected /* synthetic */ dp[] m4288d(int i) {
            return m4286c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4289e();
        }

        @Inject
        C1646a() {
        }

        protected C1648a m4287d() {
            return this.f3055b;
        }

        protected kd m4289e() {
            return (kd) this.f3054a.get();
        }

        protected kd[] m4286c(int i) {
            return new kd[i];
        }
    }

    protected /* synthetic */ da.a m4290a() {
        return m4291m();
    }

    protected /* synthetic */ C1592a b_() {
        return m4292n();
    }

    @Inject
    kd() {
    }

    protected C1648a m4291m() {
        return this.f3056e.m4287d();
    }

    protected C1646a m4292n() {
        return this.f3056e;
    }
}
