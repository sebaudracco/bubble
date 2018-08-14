package com.vungle.publisher;

import com.vungle.publisher.my.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class nb implements Factory<a> {
    static final /* synthetic */ boolean f10780a = (!nb.class.desiredAssertionStatus());
    private final MembersInjector<a> f10781b;

    public /* synthetic */ Object get() {
        return m13734a();
    }

    public nb(MembersInjector<a> membersInjector) {
        if (f10780a || membersInjector != null) {
            this.f10781b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13734a() {
        return (a) MembersInjectors.injectMembers(this.f10781b, new a());
    }

    public static Factory<a> m13733a(MembersInjector<a> membersInjector) {
        return new nb(membersInjector);
    }
}
