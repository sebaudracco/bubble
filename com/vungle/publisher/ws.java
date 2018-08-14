package com.vungle.publisher;

import com.vungle.publisher.wr.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ws implements Factory<a> {
    static final /* synthetic */ boolean f11259a = (!ws.class.desiredAssertionStatus());
    private final MembersInjector<a> f11260b;

    public /* synthetic */ Object get() {
        return m14092a();
    }

    public ws(MembersInjector<a> membersInjector) {
        if (f11259a || membersInjector != null) {
            this.f11260b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14092a() {
        return (a) MembersInjectors.injectMembers(this.f11260b, new a());
    }

    public static Factory<a> m14091a(MembersInjector<a> membersInjector) {
        return new ws(membersInjector);
    }
}
