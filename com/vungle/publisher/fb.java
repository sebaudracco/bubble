package com.vungle.publisher;

import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.fh.C1622a;
import com.vungle.publisher.jj.a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class fb extends jj<fg, fb, fh> {
    @Inject
    C1620a f2951e;

    @Singleton
    /* compiled from: vungle */
    static class C1620a extends a<fg, fb, fh, el, ek> {
        @Inject
        Provider<fb> f2949a;
        @Inject
        C1622a f2950b;

        protected /* synthetic */ da.a m3976a() {
            return m3978d();
        }

        protected /* synthetic */ dp[] m3979d(int i) {
            return m3977c(i);
        }

        protected /* synthetic */ dp g_() {
            return m3980e();
        }

        @Inject
        protected C1620a() {
        }

        protected C1622a m3978d() {
            return this.f2950b;
        }

        protected fb[] m3977c(int i) {
            return new fb[i];
        }

        protected fb m3980e() {
            return (fb) this.f2949a.get();
        }
    }

    protected /* synthetic */ da.a m3981a() {
        return m3983n();
    }

    protected /* synthetic */ C1592a b_() {
        return m3982m();
    }

    @Inject
    protected fb() {
    }

    protected C1620a m3982m() {
        return this.f2951e;
    }

    protected C1622a m3983n() {
        return this.f2951e.m3978d();
    }
}
