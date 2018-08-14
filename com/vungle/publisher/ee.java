package com.vungle.publisher;

import com.vungle.publisher.eb.c;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ee implements Factory<c> {
    static final /* synthetic */ boolean f9987a = (!ee.class.desiredAssertionStatus());
    private final MembersInjector<c> f9988b;

    public /* synthetic */ Object get() {
        return m13097a();
    }

    public ee(MembersInjector<c> membersInjector) {
        if (f9987a || membersInjector != null) {
            this.f9988b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public c m13097a() {
        return (c) MembersInjectors.injectMembers(this.f9988b, new c());
    }

    public static Factory<c> m13096a(MembersInjector<c> membersInjector) {
        return new ee(membersInjector);
    }
}
