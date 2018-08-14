package com.vungle.publisher;

import com.vungle.publisher.py.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class qc implements Factory<a> {
    static final /* synthetic */ boolean f10933a = (!qc.class.desiredAssertionStatus());
    private final MembersInjector<a> f10934b;

    public /* synthetic */ Object get() {
        return m13831a();
    }

    public qc(MembersInjector<a> membersInjector) {
        if (f10933a || membersInjector != null) {
            this.f10934b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13831a() {
        return (a) MembersInjectors.injectMembers(this.f10934b, new a());
    }

    public static Factory<a> m13830a(MembersInjector<a> membersInjector) {
        return new qc(membersInjector);
    }
}
