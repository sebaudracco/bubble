package com.vungle.publisher;

import com.vungle.publisher.hr.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class is implements Factory<a> {
    static final /* synthetic */ boolean f10483a = (!is.class.desiredAssertionStatus());
    private final MembersInjector<a> f10484b;

    public /* synthetic */ Object get() {
        return m13440a();
    }

    public is(MembersInjector<a> membersInjector) {
        if (f10483a || membersInjector != null) {
            this.f10484b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13440a() {
        return (a) MembersInjectors.injectMembers(this.f10484b, new a());
    }

    public static Factory<a> m13439a(MembersInjector<a> membersInjector) {
        return new is(membersInjector);
    }
}
