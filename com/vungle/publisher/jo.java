package com.vungle.publisher;

import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.ei.b;
import com.vungle.publisher.ej.C1605a;
import com.vungle.publisher.jn.C1642a;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class jo extends ej<jn, jo> {
    @Inject
    C1642a f3049g;
    @Inject
    C1643a f3050h;

    @Singleton
    /* compiled from: vungle */
    public static class C1643a extends C1605a<jn, jo, wr> {
        @Inject
        Provider<jo> f3048b;

        public /* bridge */ /* synthetic */ List mo2959a(String str, String[] strArr) {
            return super.mo2959a(str, strArr);
        }

        protected /* synthetic */ dp[] mo2962d(int i) {
            return m4264a(i);
        }

        protected /* synthetic */ dp g_() {
            return m4261a();
        }

        @Inject
        C1643a() {
        }

        jo m4262a(jn jnVar, wr wrVar, b bVar) {
            if (wrVar == null) {
                return null;
            }
            String q = wrVar.q();
            if (q == null) {
                return null;
            }
            jo joVar = (jo) super.mo2984a((cn) jnVar, (wc) wrVar, bVar);
            joVar.m3767a(q);
            return joVar;
        }

        protected jo[] m4264a(int i) {
            return new jo[i];
        }

        protected jo m4261a() {
            jo joVar = (jo) this.f3048b.get();
            joVar.f = this.a.m4013a(joVar);
            return joVar;
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m4268J();
    }

    protected /* synthetic */ cn$a m4269y() {
        return mo2985I();
    }

    @Inject
    jo() {
    }

    protected C1642a mo2985I() {
        return this.f3049g;
    }

    protected C1643a m4268J() {
        return this.f3050h;
    }

    public String mo2996E() {
        return qr.a(new Object[]{m3783z(), "index.html"});
    }
}
