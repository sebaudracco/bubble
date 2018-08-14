package com.vungle.publisher;

import com.vungle.publisher.kj.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class kl implements Factory<a> {
    static final /* synthetic */ boolean f10625a = (!kl.class.desiredAssertionStatus());
    private final MembersInjector<a> f10626b;

    public /* synthetic */ Object get() {
        return m13575a();
    }

    public kl(MembersInjector<a> membersInjector) {
        if (f10625a || membersInjector != null) {
            this.f10626b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13575a() {
        return (a) MembersInjectors.injectMembers(this.f10626b, new a());
    }

    public static Factory<a> m13574a(MembersInjector<a> membersInjector) {
        return new kl(membersInjector);
    }
}
