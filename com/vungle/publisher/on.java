package com.vungle.publisher;

import com.vungle.publisher.om.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class on implements Factory<a> {
    static final /* synthetic */ boolean f10871a = (!on.class.desiredAssertionStatus());
    private final MembersInjector<a> f10872b;

    public /* synthetic */ Object get() {
        return m13793a();
    }

    public on(MembersInjector<a> membersInjector) {
        if (f10871a || membersInjector != null) {
            this.f10872b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13793a() {
        return (a) MembersInjectors.injectMembers(this.f10872b, new a());
    }

    public static Factory<a> m13792a(MembersInjector<a> membersInjector) {
        return new on(membersInjector);
    }
}
