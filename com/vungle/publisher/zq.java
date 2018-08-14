package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class zq implements Factory<zo> {
    static final /* synthetic */ boolean f11394a = (!zq.class.desiredAssertionStatus());
    private final MembersInjector<zo> f11395b;

    public /* synthetic */ Object get() {
        return m14226a();
    }

    public zq(MembersInjector<zo> membersInjector) {
        if (f11394a || membersInjector != null) {
            this.f11395b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public zo m14226a() {
        return (zo) MembersInjectors.injectMembers(this.f11395b, new zo());
    }

    public static Factory<zo> m14225a(MembersInjector<zo> membersInjector) {
        return new zq(membersInjector);
    }
}
