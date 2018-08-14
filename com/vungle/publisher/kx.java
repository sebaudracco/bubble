package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class kx implements Factory<jn> {
    static final /* synthetic */ boolean f10658a = (!kx.class.desiredAssertionStatus());
    private final MembersInjector<jn> f10659b;

    public /* synthetic */ Object get() {
        return m13597a();
    }

    public kx(MembersInjector<jn> membersInjector) {
        if (f10658a || membersInjector != null) {
            this.f10659b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public jn m13597a() {
        return (jn) MembersInjectors.injectMembers(this.f10659b, new jn());
    }

    public static Factory<jn> m13596a(MembersInjector<jn> membersInjector) {
        return new kx(membersInjector);
    }
}
