package com.vungle.publisher;

import com.vungle.publisher.rz.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class sb implements Factory<a> {
    static final /* synthetic */ boolean f11046a = (!sb.class.desiredAssertionStatus());
    private final MembersInjector<a> f11047b;

    public /* synthetic */ Object get() {
        return m13902a();
    }

    public sb(MembersInjector<a> membersInjector) {
        if (f11046a || membersInjector != null) {
            this.f11047b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13902a() {
        return (a) MembersInjectors.injectMembers(this.f11047b, new a());
    }

    public static Factory<a> m13901a(MembersInjector<a> membersInjector) {
        return new sb(membersInjector);
    }
}
