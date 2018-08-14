package com.vungle.publisher;

import com.vungle.publisher.dw.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class dy implements Factory<a> {
    static final /* synthetic */ boolean f9968a = (!dy.class.desiredAssertionStatus());
    private final MembersInjector<a> f9969b;

    public /* synthetic */ Object get() {
        return m13078a();
    }

    public dy(MembersInjector<a> membersInjector) {
        if (f9968a || membersInjector != null) {
            this.f9969b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13078a() {
        return (a) MembersInjectors.injectMembers(this.f9969b, new a());
    }

    public static Factory<a> m13077a(MembersInjector<a> membersInjector) {
        return new dy(membersInjector);
    }
}
