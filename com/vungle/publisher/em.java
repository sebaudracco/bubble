package com.vungle.publisher;

import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.ei.b;
import com.vungle.publisher.ej.C1605a;
import com.vungle.publisher.el.C1607a;
import com.vungle.publisher.lb.C1650a;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class em extends ej<el, em> {
    @Inject
    C1607a f2880g;
    @Inject
    C1608a f2881h;
    @Inject
    C1650a f2882i;

    @Singleton
    /* compiled from: vungle */
    public static class C1608a extends C1605a<el, em, wg> {
        @Inject
        Provider<em> f2879b;

        public /* bridge */ /* synthetic */ List mo2959a(String str, String[] strArr) {
            return super.mo2959a(str, strArr);
        }

        protected /* synthetic */ dp[] mo2962d(int i) {
            return m3862a(i);
        }

        protected /* synthetic */ dp g_() {
            return m3859a();
        }

        @Inject
        C1608a() {
        }

        em m3860a(el elVar, wg wgVar, b bVar) {
            if (wgVar == null) {
                return null;
            }
            if (bVar == b.b) {
                String n = wgVar.n();
                if (n == null) {
                    return null;
                }
                em emVar = (em) super.mo2984a((cn) elVar, (wc) wgVar, bVar);
                emVar.m3767a(n);
                return emVar;
            }
            throw new IllegalArgumentException("cannot create archive of type: " + bVar);
        }

        protected em[] m3862a(int i) {
            return new em[i];
        }

        protected em m3859a() {
            em emVar = (em) this.f2879b.get();
            emVar.f = this.a.m4013a(emVar);
            return emVar;
        }
    }

    protected /* synthetic */ C1592a b_() {
        return mo2985I();
    }

    protected /* synthetic */ cn$a m3867y() {
        return m3865J();
    }

    @Inject
    protected em() {
    }

    protected C1608a mo2985I() {
        return this.f2881h;
    }

    protected C1607a m3865J() {
        return this.f2880g;
    }

    public lb m3866K() {
        return this.f2882i.m4326a(m3759D().toURI().toString());
    }
}
