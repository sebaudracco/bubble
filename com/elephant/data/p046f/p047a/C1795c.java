package com.elephant.data.p046f.p047a;

import com.elephant.data.p039b.p040a.C1731a;
import com.elephant.data.p046f.C1803c;
import com.elephant.data.p046f.C1806f;
import com.elephant.data.p048g.p050b.C1813b;

final class C1795c implements Runnable {
    final /* synthetic */ C1794b f3783a;

    C1795c(C1794b c1794b) {
        this.f3783a = c1794b;
    }

    public final void run() {
        if (this.f3783a.m5172b()) {
            this.f3783a.f3781c = true;
            String a = C1813b.m5252a(this.f3783a.f3780b);
            C1806f.m5221a(this.f3783a.f3780b).m5224a(this.f3783a.f3780b, new C1803c(a).m5186a(C1731a.f3560c).m5188a(C1731a.f3559b).m5187a(true), new C1797d(this));
        }
    }
}
