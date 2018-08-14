package com.elephant.data.p035a;

import android.content.Context;
import android.text.TextUtils;
import com.elephant.data.p037d.C1752b;
import com.elephant.data.p037d.p038b.C1725i;
import com.elephant.data.p037d.p038b.C1744a;
import com.elephant.data.p046f.C1804d;
import com.elephant.data.p046f.C1806f;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.C1817e;
import com.elephant.data.p048g.C1821i;
import com.elephant.data.p048g.p050b.C1813b;

final class C1726f implements C1725i {
    private /* synthetic */ Context f3540a;

    C1726f(Context context) {
        this.f3540a = context;
    }

    public final void mo3533a(C1744a c1744a) {
        if (c1744a != null && !TextUtils.isEmpty(c1744a.f3604d)) {
            if (c1744a.f3602b == null || C1816d.f3911a.equals(c1744a.f3602b)) {
                C1804d a = C1806f.m5221a(this.f3540a).m5223a();
                if (a.m5193a() && !a.m5203i() && a.m5202h() && a.m5204j() && a.m5196b(this.f3540a) && !C1817e.m5333a(this.f3540a).m5335a()) {
                    C1821i.m5346a(new C1752b(this.f3540a, c1744a, 1));
                    C1813b.m5259b(this.f3540a, System.currentTimeMillis());
                }
            }
        }
    }
}
