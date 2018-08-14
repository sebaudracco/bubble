package com.vungle.publisher;

import com.vungle.publisher.bw.b;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.tr.c;
import java.util.EnumMap;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ub {
    @Inject
    ue f3375a;
    private tr f3376b;
    private tx f3377c;
    private un f3378d;
    private b f3379e;

    @Singleton
    /* compiled from: vungle */
    public static class C1675a {
        private static final EnumMap<c, b> f3373b = new EnumMap(c.class);
        @Inject
        Provider<ub> f3374a;

        static {
            f3373b.put(c.a, b.i);
            f3373b.put(c.b, b.d);
            f3373b.put(c.c, b.j);
            f3373b.put(c.d, b.f);
            f3373b.put(c.e, b.c);
            f3373b.put(c.f, b.o);
            f3373b.put(c.g, b.e);
            f3373b.put(c.h, b.k);
            f3373b.put(c.j, b.q);
            f3373b.put(c.i, b.r);
        }

        @Inject
        protected C1675a() {
        }

        public ub m4692a(tr trVar, tx txVar) {
            return m4693a(trVar, txVar, new un());
        }

        public ub m4693a(tr trVar, tx txVar, un unVar) {
            ub ubVar = (ub) this.f3374a.get();
            ubVar.f3376b = trVar;
            ubVar.f3377c = txVar;
            b bVar = (b) f3373b.get(trVar.b());
            if (bVar == null) {
                Logger.w(Logger.NETWORK_TAG, "missing mapping for HttpTransaction requestType = " + trVar.b().toString());
                bVar = b.n;
            }
            ubVar.f3379e = bVar;
            ubVar.f3378d = unVar;
            return ubVar;
        }
    }

    @Inject
    ub() {
    }

    public tr m4698a() {
        return this.f3376b;
    }

    public un m4699b() {
        return this.f3378d;
    }

    public b m4700c() {
        return this.f3379e;
    }

    public void m4701d() {
        this.f3378d.d();
        this.f3377c.m4652a(this, this.f3375a.m4702a(this.f3376b));
    }

    public String toString() {
        return "{" + this.f3376b + ", " + this.f3378d + "}";
    }
}
