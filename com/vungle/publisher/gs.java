package com.vungle.publisher;

import com.vungle.publisher.gq.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class gs implements Factory<a> {
    static final /* synthetic */ boolean f10204a = (!gs.class.desiredAssertionStatus());
    private final MembersInjector<a> f10205b;

    public /* synthetic */ Object get() {
        return m13271a();
    }

    public gs(MembersInjector<a> membersInjector) {
        if (f10204a || membersInjector != null) {
            this.f10205b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13271a() {
        return (a) MembersInjectors.injectMembers(this.f10205b, new a());
    }

    public static Factory<a> m13270a(MembersInjector<a> membersInjector) {
        return new gs(membersInjector);
    }
}
