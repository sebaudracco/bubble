package com.vungle.publisher;

import com.vungle.publisher.id.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4195if implements Factory<a> {
    static final /* synthetic */ boolean f10324a = (!C4195if.class.desiredAssertionStatus());
    private final MembersInjector<a> f10325b;

    public /* synthetic */ Object get() {
        return m13346a();
    }

    public C4195if(MembersInjector<a> membersInjector) {
        if (f10324a || membersInjector != null) {
            this.f10325b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13346a() {
        return (a) MembersInjectors.injectMembers(this.f10325b, new a());
    }

    public static Factory<a> m13345a(MembersInjector<a> membersInjector) {
        return new C4195if(membersInjector);
    }
}
