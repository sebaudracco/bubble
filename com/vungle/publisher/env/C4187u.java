package com.vungle.publisher.env;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4187u implements Factory<r> {
    static final /* synthetic */ boolean f10043a = (!C4187u.class.desiredAssertionStatus());
    private final MembersInjector<r> f10044b;

    public /* synthetic */ Object get() {
        return m13167a();
    }

    public C4187u(MembersInjector<r> membersInjector) {
        if (f10043a || membersInjector != null) {
            this.f10044b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public r m13167a() {
        return (r) MembersInjectors.injectMembers(this.f10044b, new r());
    }

    public static Factory<r> m13166a(MembersInjector<r> membersInjector) {
        return new C4187u(membersInjector);
    }
}
