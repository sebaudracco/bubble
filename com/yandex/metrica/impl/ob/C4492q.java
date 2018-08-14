package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.C4305a;
import com.yandex.metrica.impl.C4305a.C4304a;
import java.util.HashMap;

public class C4492q {
    private final HashMap<String, C4305a> f12459a = new HashMap();

    public synchronized C4305a m16099a(C4503t c4503t, ca caVar) {
        C4305a c4305a;
        c4305a = (C4305a) this.f12459a.get(c4503t.mo7113l().toString());
        if (c4305a == null) {
            C4304a a = caVar.m15457a();
            c4305a = new C4305a(a.f11564a, a.f11565b);
            this.f12459a.put(c4503t.mo7113l().toString(), c4305a);
        }
        return c4305a;
    }

    public boolean m16100a(C4304a c4304a, ca caVar) {
        if (c4304a.f11565b <= caVar.m15457a().f11565b) {
            return false;
        }
        caVar.m15459a(c4304a).m15415h();
        return true;
    }

    public synchronized void m16101b(C4304a c4304a, ca caVar) {
        caVar.m15459a(c4304a).m15415h();
    }
}
