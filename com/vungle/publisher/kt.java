package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class kt implements Factory<ks> {
    static final /* synthetic */ boolean f10649a = (!kt.class.desiredAssertionStatus());
    private final MembersInjector<ks> f10650b;

    public /* synthetic */ Object get() {
        return m13589a();
    }

    public kt(MembersInjector<ks> membersInjector) {
        if (f10649a || membersInjector != null) {
            this.f10650b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public ks m13589a() {
        return (ks) MembersInjectors.injectMembers(this.f10650b, new ks());
    }

    public static Factory<ks> m13588a(MembersInjector<ks> membersInjector) {
        return new kt(membersInjector);
    }
}
