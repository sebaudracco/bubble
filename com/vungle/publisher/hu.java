package com.vungle.publisher;

import com.vungle.publisher.hs.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class hu implements Factory<a> {
    static final /* synthetic */ boolean f10301a = (!hu.class.desiredAssertionStatus());
    private final MembersInjector<a> f10302b;

    public /* synthetic */ Object get() {
        return m13328a();
    }

    public hu(MembersInjector<a> membersInjector) {
        if (f10301a || membersInjector != null) {
            this.f10302b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13328a() {
        return (a) MembersInjectors.injectMembers(this.f10302b, new a());
    }

    public static Factory<a> m13327a(MembersInjector<a> membersInjector) {
        return new hu(membersInjector);
    }
}
