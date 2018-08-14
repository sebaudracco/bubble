package com.vungle.publisher;

import com.vungle.publisher.jt.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class jv implements Factory<a> {
    static final /* synthetic */ boolean f10590a = (!jv.class.desiredAssertionStatus());
    private final MembersInjector<a> f10591b;

    public /* synthetic */ Object get() {
        return m13549a();
    }

    public jv(MembersInjector<a> membersInjector) {
        if (f10590a || membersInjector != null) {
            this.f10591b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13549a() {
        return (a) MembersInjectors.injectMembers(this.f10591b, new a());
    }

    public static Factory<a> m13548a(MembersInjector<a> membersInjector) {
        return new jv(membersInjector);
    }
}
