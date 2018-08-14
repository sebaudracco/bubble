package com.vungle.publisher;

import com.vungle.publisher.df.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class dh implements Factory<a> {
    static final /* synthetic */ boolean f9938a = (!dh.class.desiredAssertionStatus());
    private final MembersInjector<a> f9939b;

    public /* synthetic */ Object get() {
        return m13047a();
    }

    public dh(MembersInjector<a> membersInjector) {
        if (f9938a || membersInjector != null) {
            this.f9939b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13047a() {
        return (a) MembersInjectors.injectMembers(this.f9939b, new a());
    }

    public static Factory<a> m13046a(MembersInjector<a> membersInjector) {
        return new dh(membersInjector);
    }
}
