package com.vungle.publisher;

import com.vungle.publisher.jn.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ky implements Factory<a> {
    static final /* synthetic */ boolean f10660a = (!ky.class.desiredAssertionStatus());
    private final MembersInjector<a> f10661b;

    public /* synthetic */ Object get() {
        return m13599a();
    }

    public ky(MembersInjector<a> membersInjector) {
        if (f10660a || membersInjector != null) {
            this.f10661b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13599a() {
        return (a) MembersInjectors.injectMembers(this.f10661b, new a());
    }

    public static Factory<a> m13598a(MembersInjector<a> membersInjector) {
        return new ky(membersInjector);
    }
}
