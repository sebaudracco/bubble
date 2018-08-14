package com.vungle.publisher;

import com.vungle.publisher.ro.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class rp implements Factory<a> {
    static final /* synthetic */ boolean f10993a = (!rp.class.desiredAssertionStatus());
    private final MembersInjector<a> f10994b;

    public /* synthetic */ Object get() {
        return m13886a();
    }

    public rp(MembersInjector<a> membersInjector) {
        if (f10993a || membersInjector != null) {
            this.f10994b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13886a() {
        return (a) MembersInjectors.injectMembers(this.f10994b, new a());
    }

    public static Factory<a> m13885a(MembersInjector<a> membersInjector) {
        return new rp(membersInjector);
    }
}
