package com.vungle.publisher.env;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4183l implements Factory<k> {
    static final /* synthetic */ boolean f10033a = (!C4183l.class.desiredAssertionStatus());
    private final MembersInjector<k> f10034b;

    public /* synthetic */ Object get() {
        return m13159a();
    }

    public C4183l(MembersInjector<k> membersInjector) {
        if (f10033a || membersInjector != null) {
            this.f10034b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public k m13159a() {
        return (k) MembersInjectors.injectMembers(this.f10034b, new k());
    }

    public static Factory<k> m13158a(MembersInjector<k> membersInjector) {
        return new C4183l(membersInjector);
    }
}
