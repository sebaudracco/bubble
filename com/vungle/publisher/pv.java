package com.vungle.publisher;

import com.vungle.publisher.pj.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class pv implements Factory<a> {
    static final /* synthetic */ boolean f10921a = (!pv.class.desiredAssertionStatus());
    private final MembersInjector<a> f10922b;

    public /* synthetic */ Object get() {
        return m13823a();
    }

    public pv(MembersInjector<a> membersInjector) {
        if (f10921a || membersInjector != null) {
            this.f10922b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13823a() {
        return (a) MembersInjectors.injectMembers(this.f10922b, new a());
    }

    public static Factory<a> m13822a(MembersInjector<a> membersInjector) {
        return new pv(membersInjector);
    }
}
