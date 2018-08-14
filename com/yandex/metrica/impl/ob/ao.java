package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.C4372h;
import java.util.ArrayList;
import java.util.Iterator;

public class ao extends af {
    private final ArrayList<af> f11902a = new ArrayList();

    public ao(C4503t c4503t) {
        super(c4503t);
        this.f11902a.add(new ai(c4503t));
        if (c4503t.mo7113l().m16107d() && c4503t.mo7114m().getPackageName().equals(c4503t.mo7113l().m16105b())) {
            this.f11902a.add(new al(c4503t, bp.m15358a(m15143a().mo7114m()), new cx()));
            this.f11902a.add(new ag(c4503t));
        }
    }

    public boolean mo7043a(C4372h c4372h) {
        Iterator it = this.f11902a.iterator();
        while (it.hasNext()) {
            ((af) it.next()).mo7043a(c4372h);
        }
        return false;
    }
}
