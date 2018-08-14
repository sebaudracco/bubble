package com.vungle.publisher;

import com.vungle.publisher.gl.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class gn implements Factory<a> {
    static final /* synthetic */ boolean f10192a = (!gn.class.desiredAssertionStatus());
    private final MembersInjector<a> f10193b;

    public /* synthetic */ Object get() {
        return m13263a();
    }

    public gn(MembersInjector<a> membersInjector) {
        if (f10192a || membersInjector != null) {
            this.f10193b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13263a() {
        return (a) MembersInjectors.injectMembers(this.f10193b, new a());
    }

    public static Factory<a> m13262a(MembersInjector<a> membersInjector) {
        return new gn(membersInjector);
    }
}
