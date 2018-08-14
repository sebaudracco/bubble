package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class hy implements Factory<hx> {
    static final /* synthetic */ boolean f10309a = (!hy.class.desiredAssertionStatus());
    private final MembersInjector<hx> f10310b;

    public /* synthetic */ Object get() {
        return m13334a();
    }

    public hy(MembersInjector<hx> membersInjector) {
        if (f10309a || membersInjector != null) {
            this.f10310b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public hx m13334a() {
        return (hx) MembersInjectors.injectMembers(this.f10310b, new hx());
    }

    public static Factory<hx> m13333a(MembersInjector<hx> membersInjector) {
        return new hy(membersInjector);
    }
}
