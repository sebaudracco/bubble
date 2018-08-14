package com.vungle.publisher;

import com.vungle.publisher.bd.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class be implements Factory<a> {
    static final /* synthetic */ boolean f9783a = (!be.class.desiredAssertionStatus());
    private final MembersInjector<a> f9784b;

    public /* synthetic */ Object get() {
        return m12892a();
    }

    public be(MembersInjector<a> membersInjector) {
        if (f9783a || membersInjector != null) {
            this.f9784b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m12892a() {
        return (a) MembersInjectors.injectMembers(this.f9784b, new a());
    }

    public static Factory<a> m12891a(MembersInjector<a> membersInjector) {
        return new be(membersInjector);
    }
}
