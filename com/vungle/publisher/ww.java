package com.vungle.publisher;

import com.vungle.publisher.wv.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ww implements Factory<a> {
    static final /* synthetic */ boolean f11278a = (!ww.class.desiredAssertionStatus());
    private final MembersInjector<a> f11279b;

    public /* synthetic */ Object get() {
        return m14114a();
    }

    public ww(MembersInjector<a> membersInjector) {
        if (f11278a || membersInjector != null) {
            this.f11279b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14114a() {
        return (a) MembersInjectors.injectMembers(this.f11279b, new a());
    }

    public static Factory<a> m14113a(MembersInjector<a> membersInjector) {
        return new ww(membersInjector);
    }
}
