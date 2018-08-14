package com.vungle.publisher;

import com.vungle.publisher.ns.a.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class nx implements Factory<a> {
    static final /* synthetic */ boolean f10834a = (!nx.class.desiredAssertionStatus());
    private final MembersInjector<a> f10835b;

    public /* synthetic */ Object get() {
        return m13766a();
    }

    public nx(MembersInjector<a> membersInjector) {
        if (f10834a || membersInjector != null) {
            this.f10835b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13766a() {
        return (a) MembersInjectors.injectMembers(this.f10835b, new a());
    }

    public static Factory<a> m13765a(MembersInjector<a> membersInjector) {
        return new nx(membersInjector);
    }
}
