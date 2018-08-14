package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.C4372h;
import java.util.List;

public class al extends af {
    private final bp f11900a;
    private final cx f11901b;

    protected al(C4503t c4503t, bp bpVar, cx cxVar) {
        super(c4503t);
        this.f11900a = bpVar;
        this.f11901b = cxVar;
    }

    public boolean mo7043a(C4372h c4372h) {
        C4503t a = m15143a();
        if (a.m16134C().m15425d() && a.m16132A()) {
            br c = this.f11900a.m15366c();
            List a2 = this.f11901b.m15622a(m15143a().mo7114m(), c.m15389a());
            if (a2 == null) {
                a.m16173q();
            } else {
                a.m16160f(C4372h.m15049a(c4372h, a2));
                c.m15390a(a2);
            }
        }
        return false;
    }
}
