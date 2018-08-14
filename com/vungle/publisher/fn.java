package com.vungle.publisher;

import com.vungle.publisher.fg.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class fn implements Factory<a> {
    static final /* synthetic */ boolean f10122a = (!fn.class.desiredAssertionStatus());
    private final MembersInjector<a> f10123b;

    public /* synthetic */ Object get() {
        return m13211a();
    }

    public fn(MembersInjector<a> membersInjector) {
        if (f10122a || membersInjector != null) {
            this.f10123b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13211a() {
        return (a) MembersInjectors.injectMembers(this.f10123b, new a());
    }

    public static Factory<a> m13210a(MembersInjector<a> membersInjector) {
        return new fn(membersInjector);
    }
}
