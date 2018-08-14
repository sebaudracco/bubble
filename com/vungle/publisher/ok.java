package com.vungle.publisher;

import com.vungle.publisher.oj.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ok implements Factory<a> {
    static final /* synthetic */ boolean f10866a = (!ok.class.desiredAssertionStatus());
    private final MembersInjector<a> f10867b;

    public /* synthetic */ Object get() {
        return m13789a();
    }

    public ok(MembersInjector<a> membersInjector) {
        if (f10866a || membersInjector != null) {
            this.f10867b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13789a() {
        return (a) MembersInjectors.injectMembers(this.f10867b, new a());
    }

    public static Factory<a> m13788a(MembersInjector<a> membersInjector) {
        return new ok(membersInjector);
    }
}
