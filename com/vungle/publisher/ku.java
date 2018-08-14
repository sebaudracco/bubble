package com.vungle.publisher;

import com.vungle.publisher.ks.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ku implements Factory<a> {
    static final /* synthetic */ boolean f10651a = (!ku.class.desiredAssertionStatus());
    private final MembersInjector<a> f10652b;

    public /* synthetic */ Object get() {
        return m13591a();
    }

    public ku(MembersInjector<a> membersInjector) {
        if (f10651a || membersInjector != null) {
            this.f10652b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13591a() {
        return (a) MembersInjectors.injectMembers(this.f10652b, new a());
    }

    public static Factory<a> m13590a(MembersInjector<a> membersInjector) {
        return new ku(membersInjector);
    }
}
