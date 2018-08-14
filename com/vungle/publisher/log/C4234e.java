package com.vungle.publisher.log;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4234e implements Factory<d> {
    static final /* synthetic */ boolean f10710a = (!C4234e.class.desiredAssertionStatus());
    private final MembersInjector<d> f10711b;

    public /* synthetic */ Object get() {
        return m13655a();
    }

    public C4234e(MembersInjector<d> membersInjector) {
        if (f10710a || membersInjector != null) {
            this.f10711b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public d m13655a() {
        return (d) MembersInjectors.injectMembers(this.f10711b, new d());
    }

    public static Factory<d> m13654a(MembersInjector<d> membersInjector) {
        return new C4234e(membersInjector);
    }
}
