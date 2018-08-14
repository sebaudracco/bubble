package com.vungle.publisher;

import com.vungle.publisher.em.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class eo implements Factory<a> {
    static final /* synthetic */ boolean f10063a = (!eo.class.desiredAssertionStatus());
    private final MembersInjector<a> f10064b;

    public /* synthetic */ Object get() {
        return m13171a();
    }

    public eo(MembersInjector<a> membersInjector) {
        if (f10063a || membersInjector != null) {
            this.f10064b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13171a() {
        return (a) MembersInjectors.injectMembers(this.f10064b, new a());
    }

    public static Factory<a> m13170a(MembersInjector<a> membersInjector) {
        return new eo(membersInjector);
    }
}
