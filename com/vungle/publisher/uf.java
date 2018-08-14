package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class uf implements Factory<ue> {
    static final /* synthetic */ boolean f11135a = (!uf.class.desiredAssertionStatus());
    private final MembersInjector<ue> f11136b;

    public /* synthetic */ Object get() {
        return m13972a();
    }

    public uf(MembersInjector<ue> membersInjector) {
        if (f11135a || membersInjector != null) {
            this.f11136b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ue m13972a() {
        return (ue) MembersInjectors.injectMembers(this.f11136b, new ue());
    }

    public static Factory<ue> m13971a(MembersInjector<ue> membersInjector) {
        return new uf(membersInjector);
    }
}
