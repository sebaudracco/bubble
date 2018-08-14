package com.vungle.publisher;

import com.vungle.publisher.el.C1607a;
import com.vungle.publisher.gk.C1625a;
import com.vungle.publisher.hr.C1632a;
import com.vungle.publisher.jn.C1642a;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class cn$b {
    @Inject
    C1607a f2769a;
    @Inject
    C1632a f2770b;
    @Inject
    C1642a f2771c;
    @Inject
    C1625a f2772d;

    @Inject
    cn$b() {
    }

    public <A extends cn, R extends wc, F extends ea<A, R>> F m3559a(m mVar) {
        return (ea) new 1(this, mVar).a(mVar);
    }

    public cn m3558a(m mVar, String str) {
        return (cn) new 2(this, str).a(mVar);
    }
}
