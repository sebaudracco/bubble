package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4190g implements Factory<c> {
    static final /* synthetic */ boolean f10168a = (!C4190g.class.desiredAssertionStatus());
    private final MembersInjector<c> f10169b;

    public /* synthetic */ Object get() {
        return m13235a();
    }

    public C4190g(MembersInjector<c> membersInjector) {
        if (f10168a || membersInjector != null) {
            this.f10169b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public c m13235a() {
        return (c) MembersInjectors.injectMembers(this.f10169b, new c());
    }

    public static Factory<c> m13234a(MembersInjector<c> membersInjector) {
        return new C4190g(membersInjector);
    }
}
