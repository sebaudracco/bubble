package com.vungle.publisher;

import com.vungle.publisher.ts.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class tu implements Factory<a> {
    static final /* synthetic */ boolean f11121a = (!tu.class.desiredAssertionStatus());
    private final MembersInjector<a> f11122b;

    public /* synthetic */ Object get() {
        return m13958a();
    }

    public tu(MembersInjector<a> membersInjector) {
        if (f11121a || membersInjector != null) {
            this.f11122b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13958a() {
        return (a) MembersInjectors.injectMembers(this.f11122b, new a());
    }

    public static Factory<a> m13957a(MembersInjector<a> membersInjector) {
        return new tu(membersInjector);
    }
}
