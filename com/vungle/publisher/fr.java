package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class fr implements Factory<fq> {
    static final /* synthetic */ boolean f10137a = (!fr.class.desiredAssertionStatus());
    private final MembersInjector<fq> f10138b;

    public /* synthetic */ Object get() {
        return m13217a();
    }

    public fr(MembersInjector<fq> membersInjector) {
        if (f10137a || membersInjector != null) {
            this.f10138b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public fq m13217a() {
        return (fq) MembersInjectors.injectMembers(this.f10138b, new fq());
    }

    public static Factory<fq> m13216a(MembersInjector<fq> membersInjector) {
        return new fr(membersInjector);
    }
}
