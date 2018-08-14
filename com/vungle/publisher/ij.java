package com.vungle.publisher;

import com.vungle.publisher.ic.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ij implements Factory<a> {
    static final /* synthetic */ boolean f10334a = (!ij.class.desiredAssertionStatus());
    private final MembersInjector<a> f10335b;

    public /* synthetic */ Object get() {
        return m13354a();
    }

    public ij(MembersInjector<a> membersInjector) {
        if (f10334a || membersInjector != null) {
            this.f10335b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13354a() {
        return (a) MembersInjectors.injectMembers(this.f10335b, new a());
    }

    public static Factory<a> m13353a(MembersInjector<a> membersInjector) {
        return new ij(membersInjector);
    }
}
