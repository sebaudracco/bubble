package com.elephant.data.p035a;

import android.content.Context;
import android.text.TextUtils;
import com.elephant.data.p046f.C1804d;
import com.elephant.data.p046f.C1806f;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.C1817e;
import com.elephant.data.p048g.p050b.C1813b;

final class C1729i implements Runnable {
    private /* synthetic */ Context f3547a;
    private /* synthetic */ String f3548b;
    private /* synthetic */ C1727g f3549c;

    C1729i(C1727g c1727g, Context context, String str) {
        this.f3549c = c1727g;
        this.f3547a = context;
        this.f3548b = str;
    }

    public final void run() {
        C1813b.m5267e(this.f3547a);
        if (!TextUtils.isEmpty(this.f3548b) && !C1816d.m5308f(this.f3548b)) {
            C1804d a = C1806f.m5221a(this.f3547a).m5223a();
            if (a.m5193a() && !a.m5203i() && a.m5202h() && a.m5204j() && a.m5194a(this.f3547a) && !C1817e.m5333a(this.f3547a).m5335a()) {
                C1727g.m4981a(this.f3549c, this.f3547a, this.f3548b);
            }
        }
    }
}
