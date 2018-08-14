package com.vungle.publisher;

import com.vungle.publisher.eh.a;
import com.vungle.publisher.log.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: vungle */
public abstract class je<D extends je<D, A, R, E, F, T>, A extends cn, R extends wc, E extends eh, F extends a<E, T, R>, T extends wu> {
    protected Map<jf, List<E>> f10523b;
    protected cn f10524c;

    /* compiled from: vungle */
    static abstract class C4221a<D extends je<D, A, R, E, F, T>, A extends cn, R extends wc, E extends eh, F extends a<E, T, R>, T extends wu> {
        protected abstract F m13473c();

        protected abstract D m13474d();

        C4221a() {
        }

        public D m13470a(A a, R r) {
            return m13472a(m13474d(), a, r);
        }

        public D m13469a(A a) {
            return m13471a(m13474d(), (cn) a);
        }

        protected D m13471a(D d, A a) {
            d.f10524c = a;
            Map b = m13473c().b((String) a.u);
            if (b != null) {
                d.f10523b = b;
                Logger.m13635d(Logger.REPORT_TAG, "got " + b.size() + " event trackings by adId: " + ((String) a.u));
            } else {
                Logger.m13635d(Logger.REPORT_TAG, "no event trackings for adId: " + ((String) a.u));
            }
            return d;
        }

        public D m13472a(D d, A a, R r) {
            d.f10524c = a;
            d.f10523b = m13473c().a((String) a.u, r.m14034e());
            return d;
        }
    }

    protected abstract C4221a<D, A, R, E, F, T> m13478b();

    public void m13479c() {
        if (this.f10523b != null) {
            for (List<eh> it : this.f10523b.values()) {
                for (eh d_ : it) {
                    d_.d_();
                }
            }
        }
    }

    public List<String> m13475a(jf jfVar) {
        Map map = this.f10523b;
        if (map != null) {
            List<eh> list = (List) map.get(jfVar);
            if (list != null && list.size() > 0) {
                List<String> arrayList = new ArrayList();
                for (eh a : list) {
                    arrayList.add(a.a());
                }
                return arrayList;
            }
        }
        return null;
    }

    public void m13477a(StringBuilder stringBuilder) {
        dp.a(stringBuilder, "eventTrackings", this.f10523b == null ? null : Integer.valueOf(this.f10523b.size()));
    }

    public Map<jf, List<E>> m13476a(R r) {
        a c = m13478b().m13473c();
        if (c != null) {
            this.f10523b = c.a(r);
        }
        return this.f10523b;
    }
}
