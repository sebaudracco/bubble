package com.vungle.publisher;

import com.vungle.publisher.ub.C1675a;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class uq extends C1675a {
    @Inject
    uo$a f3384b;
    @Inject
    Lazy<te> f3385c;
    @Inject
    Lazy<tn> f3386d;

    @Inject
    uq() {
    }

    public ub m4717a(cn cnVar, jf jfVar, String str) {
        tx txVar;
        if (jfVar.a()) {
            txVar = (tx) this.f3385c.get();
        } else {
            txVar = (tx) this.f3386d.get();
        }
        return super.m4692a(this.f3384b.m4715a(cnVar, jfVar, str), txVar);
    }
}
