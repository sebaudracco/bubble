package com.vungle.publisher;

import com.vungle.publisher.cn.c;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.hq.C1631a;
import com.vungle.publisher.im.C1637a;
import com.vungle.publisher.jh.a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class hr extends jh<hr, hq, wm> {
    @Inject
    C1632a f2997w;

    @Singleton
    /* compiled from: vungle */
    public static class C1632a extends a<hr, hq, wm> {
        @Inject
        Provider<hr> f2994e;
        @Inject
        C1631a f2995f;
        @Inject
        C1637a f2996g;

        protected /* synthetic */ Object[] m4128b(int i) {
            return m4127a(i);
        }

        protected /* synthetic */ dp[] m4132d(int i) {
            return m4130c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4134f();
        }

        protected /* synthetic */ je.a m4135j() {
            return m4133e();
        }

        protected /* synthetic */ jg.a m4136k() {
            return m4131d();
        }

        @Inject
        C1632a() {
        }

        public hr m4124a(wm wmVar) {
            hr hrVar = (hr) super.a(wmVar);
            hrVar.a(c.e);
            return hrVar;
        }

        protected m m4126a() {
            return m.b;
        }

        protected C1631a m4131d() {
            return this.f2995f;
        }

        protected C1637a m4133e() {
            return this.f2996g;
        }

        protected String m4129c() {
            return "ad";
        }

        protected hr[] m4130c(int i) {
            return new hr[i];
        }

        protected String[] m4127a(int i) {
            return new String[i];
        }

        protected hr m4134f() {
            return (hr) this.f2994e.get();
        }
    }

    protected /* synthetic */ cn$a mo2979a() {
        return m4139s();
    }

    protected /* synthetic */ C1592a b_() {
        return m4140t();
    }

    protected /* synthetic */ a mo2983x() {
        return m4139s();
    }

    @Inject
    protected hr() {
    }

    protected C1632a m4139s() {
        return this.f2997w;
    }

    protected C1632a m4140t() {
        return m4139s();
    }

    protected boolean m4138b() {
        return true;
    }
}
