package com.vungle.publisher;

import com.vungle.publisher.uo.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class up implements Factory<a> {
    static final /* synthetic */ boolean f11151a = (!up.class.desiredAssertionStatus());
    private final MembersInjector<a> f11152b;

    public /* synthetic */ Object get() {
        return m13988a();
    }

    public up(MembersInjector<a> membersInjector) {
        if (f11151a || membersInjector != null) {
            this.f11152b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13988a() {
        return (a) MembersInjectors.injectMembers(this.f11152b, new a());
    }

    public static Factory<a> m13987a(MembersInjector<a> membersInjector) {
        return new up(membersInjector);
    }
}
