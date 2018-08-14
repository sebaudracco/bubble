package com.vungle.publisher;

import com.vungle.publisher.ge.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class gg implements Factory<a> {
    static final /* synthetic */ boolean f10183a = (!gg.class.desiredAssertionStatus());
    private final MembersInjector<a> f10184b;

    public /* synthetic */ Object get() {
        return m13255a();
    }

    public gg(MembersInjector<a> membersInjector) {
        if (f10183a || membersInjector != null) {
            this.f10184b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13255a() {
        return (a) MembersInjectors.injectMembers(this.f10184b, new a());
    }

    public static Factory<a> m13254a(MembersInjector<a> membersInjector) {
        return new gg(membersInjector);
    }
}
