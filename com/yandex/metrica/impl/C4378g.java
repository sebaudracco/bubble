package com.yandex.metrica.impl;

import com.yandex.metrica.impl.ob.bz;
import com.yandex.metrica.impl.ob.dt;
import com.yandex.metrica.impl.utils.C4525g;
import com.yandex.metrica.impl.utils.C4528i;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;

public class C4378g {
    private final bz f11864a;
    private final Executor f11865b;
    private final C4369c f11866c;
    private volatile Map<String, Long> f11867d = null;

    class C43771 implements Runnable {
        final /* synthetic */ C4378g f11863a;

        C43771(C4378g c4378g) {
            this.f11863a = c4378g;
        }

        public void run() {
            Map a = this.f11863a.f11866c.m15041a();
            Map hashMap = new HashMap();
            if (!bk.m14988a(a)) {
                for (Entry entry : a.entrySet()) {
                    hashMap.put(entry.getKey(), Long.valueOf(C4528i.m16276a((String) entry.getValue(), 0)));
                }
            }
            this.f11863a.f11867d = hashMap;
        }
    }

    public C4378g(bz bzVar, C4369c c4369c, Executor executor) {
        this.f11864a = bzVar;
        this.f11865b = executor;
        this.f11866c = c4369c;
        m15099b();
        this.f11865b.execute(new C43771(this));
    }

    private void m15099b() {
        String l = this.f11864a.m15450l(null);
        dt dtVar = new dt();
        Map a = C4525g.m16272a(l);
        if (!bk.m14988a(a)) {
            for (Entry entry : a.entrySet()) {
                dtVar.m15792a((String) entry.getKey(), C4528i.m16275a((String) entry.getValue(), Integer.MAX_VALUE));
            }
        }
        this.f11864a.m15432b(null);
        this.f11864a.m15430a(null);
        this.f11864a.m15452n(null);
    }

    public void m15100a() {
        m15099b();
    }
}
