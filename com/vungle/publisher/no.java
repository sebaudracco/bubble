package com.vungle.publisher;

import com.vungle.publisher.nk.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class no implements Factory<a> {
    static final /* synthetic */ boolean f10813a = (!no.class.desiredAssertionStatus());
    private final MembersInjector<a> f10814b;

    public /* synthetic */ Object get() {
        return m13752a();
    }

    public no(MembersInjector<a> membersInjector) {
        if (f10813a || membersInjector != null) {
            this.f10814b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13752a() {
        return (a) MembersInjectors.injectMembers(this.f10814b, new a());
    }

    public static Factory<a> m13751a(MembersInjector<a> membersInjector) {
        return new no(membersInjector);
    }
}
