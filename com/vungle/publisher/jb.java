package com.vungle.publisher;

import com.vungle.publisher.iz.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class jb implements Factory<a> {
    static final /* synthetic */ boolean f10515a = (!jb.class.desiredAssertionStatus());
    private final MembersInjector<a> f10516b;

    public /* synthetic */ Object get() {
        return m13464a();
    }

    public jb(MembersInjector<a> membersInjector) {
        if (f10515a || membersInjector != null) {
            this.f10516b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13464a() {
        return (a) MembersInjectors.injectMembers(this.f10516b, new a());
    }

    public static Factory<a> m13463a(MembersInjector<a> membersInjector) {
        return new jb(membersInjector);
    }
}
