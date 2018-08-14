package com.vungle.publisher;

import com.vungle.publisher.hx.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class hz implements Factory<a> {
    static final /* synthetic */ boolean f10311a = (!hz.class.desiredAssertionStatus());
    private final MembersInjector<a> f10312b;

    public /* synthetic */ Object get() {
        return m13336a();
    }

    public hz(MembersInjector<a> membersInjector) {
        if (f10311a || membersInjector != null) {
            this.f10312b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13336a() {
        return (a) MembersInjectors.injectMembers(this.f10312b, new a());
    }

    public static Factory<a> m13335a(MembersInjector<a> membersInjector) {
        return new hz(membersInjector);
    }
}
