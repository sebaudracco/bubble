package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class qp implements Factory<qo> {
    static final /* synthetic */ boolean f10943a = (!qp.class.desiredAssertionStatus());
    private final MembersInjector<qo> f10944b;

    public /* synthetic */ Object get() {
        return m13839a();
    }

    public qp(MembersInjector<qo> membersInjector) {
        if (f10943a || membersInjector != null) {
            this.f10944b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public qo m13839a() {
        return (qo) MembersInjectors.injectMembers(this.f10944b, new qo());
    }

    public static Factory<qo> m13838a(MembersInjector<qo> membersInjector) {
        return new qp(membersInjector);
    }
}
