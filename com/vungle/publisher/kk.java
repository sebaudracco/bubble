package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class kk implements Factory<kj> {
    static final /* synthetic */ boolean f10623a = (!kk.class.desiredAssertionStatus());
    private final MembersInjector<kj> f10624b;

    public /* synthetic */ Object get() {
        return m13573a();
    }

    public kk(MembersInjector<kj> membersInjector) {
        if (f10623a || membersInjector != null) {
            this.f10624b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public kj m13573a() {
        return (kj) MembersInjectors.injectMembers(this.f10624b, new kj());
    }

    public static Factory<kj> m13572a(MembersInjector<kj> membersInjector) {
        return new kk(membersInjector);
    }
}
