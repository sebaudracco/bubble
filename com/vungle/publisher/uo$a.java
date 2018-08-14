package com.vungle.publisher;

import com.vungle.publisher.tr.a;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class uo$a extends a<uo> {
    protected /* synthetic */ tr m4716b() {
        return m4714a();
    }

    @Inject
    uo$a() {
    }

    protected uo m4715a(cn cnVar, jf jfVar, String str) {
        uo uoVar = (uo) c();
        uoVar.a = cnVar;
        uoVar.b = jfVar;
        uoVar.a(str);
        return uoVar;
    }

    protected uo m4714a() {
        return new uo();
    }
}
