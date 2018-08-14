package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ur implements Factory<uq> {
    static final /* synthetic */ boolean f11153a = (!ur.class.desiredAssertionStatus());
    private final MembersInjector<uq> f11154b;

    public /* synthetic */ Object get() {
        return m13990a();
    }

    public ur(MembersInjector<uq> membersInjector) {
        if (f11153a || membersInjector != null) {
            this.f11154b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public uq m13990a() {
        return (uq) MembersInjectors.injectMembers(this.f11154b, new uq());
    }

    public static Factory<uq> m13989a(MembersInjector<uq> membersInjector) {
        return new ur(membersInjector);
    }
}
