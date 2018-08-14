package com.vungle.publisher;

import com.vungle.publisher.cn.c;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class dw {
    dr<?> f2829a;

    @Singleton
    /* compiled from: vungle */
    public static class C1601a {
        @Inject
        Provider<dw> f2828a;

        @Inject
        C1601a() {
        }

        public dw m3734a(dr<?> drVar) {
            dw dwVar = (dw) this.f2828a.get();
            dwVar.f2829a = drVar;
            return dwVar;
        }
    }

    @Inject
    dw() {
    }

    public void m3735a(c cVar, c cVar2) {
        String str = Logger.PREPARE_TAG;
        if (cVar2 != cVar && cVar2 != c.b) {
            Logger.v(Logger.PREPARE_TAG, "resetting prepare_retry_count from " + this.f2829a.j() + " to " + 0 + " for " + this.f2829a.B());
            this.f2829a.a(0);
        }
    }
}
