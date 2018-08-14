package com.vungle.publisher;

import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.ji.b;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ew extends ji {
    @Inject
    C1618a f2947d;

    @Singleton
    /* compiled from: vungle */
    static class C1618a extends b<wg> {
        @Inject
        Provider<ew> f2946a;

        protected /* synthetic */ dp g_() {
            return m3973a();
        }

        @Inject
        C1618a() {
        }

        protected ew m3973a() {
            return (ew) this.f2946a.get();
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m3974e();
    }

    @Inject
    ew() {
    }

    protected C1618a m3974e() {
        return this.f2947d;
    }
}
