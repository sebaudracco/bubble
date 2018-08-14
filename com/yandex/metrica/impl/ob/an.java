package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.C4372h;
import com.yandex.metrica.impl.C4511p.C4510a;
import com.yandex.metrica.impl.bi;

public class an extends af {
    public an(C4503t c4503t) {
        super(c4503t);
    }

    public boolean mo7043a(C4372h c4372h) {
        C4503t a = m15143a();
        if (a.mo7112j().m14235C()) {
            String b = m15158b();
            if (!bi.m14957a(b)) {
                m15159c();
                a.m16144a(new C4372h(c4372h).mo7032c("").mo7031b(b).m15053a(C4510a.EVENT_TYPE_REFERRER_DEPRECATED.m16188a()));
            }
        }
        return false;
    }

    String m15158b() {
        return m15143a().m16176t();
    }

    void m15159c() {
        m15143a().m16177u();
    }
}
