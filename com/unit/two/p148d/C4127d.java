package com.unit.two.p148d;

import android.content.Context;
import com.unit.two.p147c.C4109v;
import com.unit.two.p148d.p149a.C4120b;
import com.unit.two.p148d.p149a.C4123e;
import com.unit.two.p151f.C4144k;

final class C4127d implements C4109v {
    C4127d(C4125b c4125b) {
    }

    public final void mo6922a() {
        if (System.currentTimeMillis() - C4144k.m12811e(C4125b.f9609a) > 28800000) {
            C4125b e = C4125b.f9610b;
            Context d = C4125b.f9609a;
            C4123e.m12732a(d).m12733a(new C4120b(C4144k.m12801b(d), new C4126c(e, d)).m12718a(1).m12721a(40).m12720a(true));
            C4144k.m12796a(C4125b.f9609a, System.currentTimeMillis());
        }
    }
}
