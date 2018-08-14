package com.vungle.publisher;

import com.vungle.publisher.dk.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class dm implements Factory<a> {
    static final /* synthetic */ boolean f9948a = (!dm.class.desiredAssertionStatus());
    private final MembersInjector<a> f9949b;

    public /* synthetic */ Object get() {
        return m13055a();
    }

    public dm(MembersInjector<a> membersInjector) {
        if (f9948a || membersInjector != null) {
            this.f9949b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13055a() {
        return (a) MembersInjectors.injectMembers(this.f9949b, new a());
    }

    public static Factory<a> m13054a(MembersInjector<a> membersInjector) {
        return new dm(membersInjector);
    }
}
