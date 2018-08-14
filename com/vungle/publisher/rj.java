package com.vungle.publisher;

import com.vungle.publisher.rg.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class rj implements Factory<a> {
    static final /* synthetic */ boolean f10967a = (!rj.class.desiredAssertionStatus());
    private final MembersInjector<a> f10968b;

    public /* synthetic */ Object get() {
        return m13875a();
    }

    public rj(MembersInjector<a> membersInjector) {
        if (f10967a || membersInjector != null) {
            this.f10968b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13875a() {
        return (a) MembersInjectors.injectMembers(this.f10968b, new a());
    }

    public static Factory<a> m13874a(MembersInjector<a> membersInjector) {
        return new rj(membersInjector);
    }
}
