package com.vungle.publisher;

import com.vungle.publisher.wj.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class wk implements Factory<a> {
    static final /* synthetic */ boolean f11236a = (!wk.class.desiredAssertionStatus());
    private final MembersInjector<a> f11237b;

    public /* synthetic */ Object get() {
        return m14068a();
    }

    public wk(MembersInjector<a> membersInjector) {
        if (f11236a || membersInjector != null) {
            this.f11237b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m14068a() {
        return (a) MembersInjectors.injectMembers(this.f11237b, new a());
    }

    public static Factory<a> m14067a(MembersInjector<a> membersInjector) {
        return new wk(membersInjector);
    }
}
