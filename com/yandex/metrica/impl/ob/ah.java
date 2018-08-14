package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.C4372h;
import com.yandex.metrica.impl.C4511p.C4510a;

public class ah extends af {
    private by f11898a = m15143a().m16134C();

    public ah(C4503t c4503t) {
        super(c4503t);
    }

    public boolean mo7043a(C4372h c4372h) {
        C4503t a = m15143a();
        if (!this.f11898a.m15425d()) {
            if (!this.f11898a.m15423c()) {
                String b = c4372h.m15061b();
                this.f11898a.m15422c(b);
                a.m16156d(C4372h.m15048a(c4372h, C4510a.EVENT_TYPE_FIRST_ACTIVATION).mo7032c(b));
                a.m16152b(true);
            }
            this.f11898a.m15421b();
        }
        return false;
    }
}
