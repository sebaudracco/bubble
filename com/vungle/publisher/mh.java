package com.vungle.publisher;

import com.vungle.publisher.mg.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class mh implements Factory<a> {
    static final /* synthetic */ boolean f10747a = (!mh.class.desiredAssertionStatus());
    private final MembersInjector<a> f10748b;

    public /* synthetic */ Object get() {
        return m13693a();
    }

    public mh(MembersInjector<a> membersInjector) {
        if (f10747a || membersInjector != null) {
            this.f10748b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13693a() {
        return (a) MembersInjectors.injectMembers(this.f10748b, new a());
    }

    public static Factory<a> m13692a(MembersInjector<a> membersInjector) {
        return new mh(membersInjector);
    }
}
