package com.vungle.publisher;

import com.vungle.publisher.gk.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class hh implements Factory<a> {
    static final /* synthetic */ boolean f10260a = (!hh.class.desiredAssertionStatus());
    private final MembersInjector<a> f10261b;

    public /* synthetic */ Object get() {
        return m13298a();
    }

    public hh(MembersInjector<a> membersInjector) {
        if (f10260a || membersInjector != null) {
            this.f10261b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13298a() {
        return (a) MembersInjectors.injectMembers(this.f10261b, new a());
    }

    public static Factory<a> m13297a(MembersInjector<a> membersInjector) {
        return new hh(membersInjector);
    }
}
