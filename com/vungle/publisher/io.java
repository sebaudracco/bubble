package com.vungle.publisher;

import com.vungle.publisher.im.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class io implements Factory<a> {
    static final /* synthetic */ boolean f10474a = (!io.class.desiredAssertionStatus());
    private final MembersInjector<a> f10475b;

    public /* synthetic */ Object get() {
        return m13432a();
    }

    public io(MembersInjector<a> membersInjector) {
        if (f10474a || membersInjector != null) {
            this.f10475b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13432a() {
        return (a) MembersInjectors.injectMembers(this.f10475b, new a());
    }

    public static Factory<a> m13431a(MembersInjector<a> membersInjector) {
        return new io(membersInjector);
    }
}
