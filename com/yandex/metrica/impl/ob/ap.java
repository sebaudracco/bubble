package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.C4372h;
import com.yandex.metrica.impl.C4511p.C4510a;

public class ap extends af {
    private by f11903a;

    public ap(C4503t c4503t) {
        super(c4503t);
        this.f11903a = c4503t.m16134C();
    }

    public boolean mo7043a(C4372h c4372h) {
        C4503t a = m15143a();
        if (!this.f11903a.m15423c()) {
            C4372h a2;
            if (a.mo7112j().m14292y()) {
                a2 = C4372h.m15048a(c4372h, C4510a.EVENT_TYPE_APP_UPDATE);
            } else {
                a2 = C4372h.m15048a(c4372h, C4510a.EVENT_TYPE_INIT);
            }
            a.m16156d(a2.mo7032c(this.f11903a.m15424d("")));
            a.m16152b(true);
            this.f11903a.m15419a();
            this.f11903a.m15426e();
        }
        return false;
    }
}
