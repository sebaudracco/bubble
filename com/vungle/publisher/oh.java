package com.vungle.publisher;

import com.vungle.publisher.og.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class oh implements Factory<a> {
    static final /* synthetic */ boolean f10856a = (!oh.class.desiredAssertionStatus());
    private final MembersInjector<a> f10857b;

    public /* synthetic */ Object get() {
        return m13783a();
    }

    public oh(MembersInjector<a> membersInjector) {
        if (f10856a || membersInjector != null) {
            this.f10857b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13783a() {
        return (a) MembersInjectors.injectMembers(this.f10857b, new a());
    }

    public static Factory<a> m13782a(MembersInjector<a> membersInjector) {
        return new oh(membersInjector);
    }
}
