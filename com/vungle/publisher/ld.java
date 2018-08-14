package com.vungle.publisher;

import com.vungle.publisher.lb.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ld implements Factory<a> {
    static final /* synthetic */ boolean f10686a = (!ld.class.desiredAssertionStatus());
    private final MembersInjector<a> f10687b;

    public /* synthetic */ Object get() {
        return m13609a();
    }

    public ld(MembersInjector<a> membersInjector) {
        if (f10686a || membersInjector != null) {
            this.f10687b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13609a() {
        return (a) MembersInjectors.injectMembers(this.f10687b, new a());
    }

    public static Factory<a> m13608a(MembersInjector<a> membersInjector) {
        return new ld(membersInjector);
    }
}
