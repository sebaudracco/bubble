package com.vungle.publisher;

import com.vungle.publisher.c.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4194i implements Factory<a> {
    static final /* synthetic */ boolean f10313a = (!C4194i.class.desiredAssertionStatus());
    private final MembersInjector<a> f10314b;

    public /* synthetic */ Object get() {
        return m13338a();
    }

    public C4194i(MembersInjector<a> membersInjector) {
        if (f10313a || membersInjector != null) {
            this.f10314b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13338a() {
        return (a) MembersInjectors.injectMembers(this.f10314b, new a());
    }

    public static Factory<a> m13337a(MembersInjector<a> membersInjector) {
        return new C4194i(membersInjector);
    }
}
