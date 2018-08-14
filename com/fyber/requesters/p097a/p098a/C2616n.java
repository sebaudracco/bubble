package com.fyber.requesters.p097a.p098a;

import com.fyber.ads.internal.C2424c;
import com.fyber.p094b.p095a.C2480a.C2477a;
import com.fyber.requesters.p097a.C2579k;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.utils.FyberLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: RequestAgent */
public final class C2616n {
    private Map<String, C2602f<?, C2623c>> f6522a;
    private List<C2595e> f6523b;

    /* compiled from: RequestAgent */
    public static class C2615a {
        List<C2595e> f6521a = new ArrayList();

        public C2615a() {
            this.f6521a.add(new C2601d());
            this.f6521a.add(new C2618q());
            this.f6521a.add(new C2614m());
            this.f6521a.add(new C2619r());
            this.f6521a.add(new C2598b());
            this.f6521a.add(new C2605h());
        }

        public final C2616n m8384a() {
            return new C2616n();
        }
    }

    private C2616n(C2615a c2615a) {
        this.f6522a = new HashMap(3);
        this.f6523b = Collections.unmodifiableList(c2615a.f6521a);
    }

    public final <T> C2602f<T, C2623c> m8387a(T t, C2623c c2623c) {
        return m8385a(t, c2623c, 0);
    }

    public final <T> C2602f<T, C2623c> m8389b(T t, C2623c c2623c) {
        return m8385a(t, c2623c, 1);
    }

    private <T> C2602f<T, C2623c> m8385a(T t, C2623c c2623c, int i) {
        C2602f<T, C2623c> a = new C2602f(t).m8340a((C2579k) c2623c).m8339a(i);
        String a2 = c2623c.mo3972a();
        C2602f c2602f = (C2602f) this.f6522a.get(a2);
        if (c2602f != null && c2602f.m8345d() == i) {
            a.m8343b(c2602f.m8348g() + 1);
        }
        this.f6522a.put(a2, a);
        return a;
    }

    public final <T> C2602f<T, C2623c> m8386a(C2579k c2579k) {
        C2602f<T, C2623c> c2602f = (C2602f) this.f6522a.get(c2579k.mo3972a());
        if (c2602f != null && c2602f.m8350i()) {
            Object obj;
            FyberLogger.m8448d("RequestAgent", "There's a cached response, checking its validity...");
            for (C2595e a : this.f6523b) {
                if (!a.mo3995a(c2602f, c2579k)) {
                    obj = null;
                    break;
                }
            }
            obj = 1;
            if (obj != null) {
                c2602f.m8347f();
                FyberLogger.m8448d("RequestAgent", "The response is valid, proceeding...");
                return c2602f;
            }
            FyberLogger.m8448d("RequestAgent", "The cached response is not valid anymore");
            if (c2602f.m8346e() > 0) {
                new C2477a(C2424c.CachedContainerFill, c2602f).m7868d().mo3907b();
            }
            c2602f.m8349h();
        }
        return null;
    }

    public final C2602f<?, C2623c> m8388a(String str) {
        FyberLogger.m8448d("RequestAgent", "Removing entry for cacheKey = " + str);
        C2602f<?, C2623c> c2602f = (C2602f) this.f6522a.remove(str);
        if (c2602f != null && c2602f.m8346e() > 0) {
            new C2477a(C2424c.CachedContainerFill, c2602f).m7868d().mo3907b();
        }
        return c2602f;
    }
}
