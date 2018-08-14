package com.vungle.publisher;

import com.vungle.publisher.op.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ov implements Factory<a> {
    static final /* synthetic */ boolean f10883a = (!ov.class.desiredAssertionStatus());
    private final MembersInjector<a> f10884b;

    public /* synthetic */ Object get() {
        return m13803a();
    }

    public ov(MembersInjector<a> membersInjector) {
        if (f10883a || membersInjector != null) {
            this.f10884b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13803a() {
        return (a) MembersInjectors.injectMembers(this.f10884b, new a());
    }

    public static Factory<a> m13802a(MembersInjector<a> membersInjector) {
        return new ov(membersInjector);
    }
}
