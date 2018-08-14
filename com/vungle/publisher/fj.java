package com.vungle.publisher;

import com.vungle.publisher.fh.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class fj implements Factory<a> {
    static final /* synthetic */ boolean f10112a = (!fj.class.desiredAssertionStatus());
    private final MembersInjector<a> f10113b;

    public /* synthetic */ Object get() {
        return m13203a();
    }

    public fj(MembersInjector<a> membersInjector) {
        if (f10112a || membersInjector != null) {
            this.f10113b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13203a() {
        return (a) MembersInjectors.injectMembers(this.f10113b, new a());
    }

    public static Factory<a> m13202a(MembersInjector<a> membersInjector) {
        return new fj(membersInjector);
    }
}
