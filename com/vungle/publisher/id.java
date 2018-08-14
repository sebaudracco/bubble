package com.vungle.publisher;

import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.jl.b;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class id extends jl<hx> {
    @Inject
    C1636a f3009e;

    @Singleton
    /* compiled from: vungle */
    static class C1636a extends b<id, hx> {
        @Inject
        Provider<id> f3008a;

        protected /* synthetic */ dp[] m4165d(int i) {
            return m4164c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4163a();
        }

        @Inject
        protected C1636a() {
        }

        protected id[] m4164c(int i) {
            return new id[i];
        }

        protected id m4163a() {
            return (id) this.f3008a.get();
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m4166m();
    }

    @Inject
    protected id() {
    }

    protected C1636a m4166m() {
        return this.f3009e;
    }
}
