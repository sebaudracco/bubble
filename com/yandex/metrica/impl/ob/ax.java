package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.C4372h;
import com.yandex.metrica.impl.C4511p.C4510a;

public class ax extends af {
    public ax(C4503t c4503t) {
        super(c4503t);
    }

    public boolean mo7043a(C4372h c4372h) {
        C4503t a = m15143a();
        if (a.m16141a().m15284a(c4372h)) {
            a.m16152b(true);
            a.m16156d(C4372h.m15048a(c4372h, C4510a.EVENT_TYPE_START));
        }
        return false;
    }
}
