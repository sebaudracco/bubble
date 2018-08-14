package com.vungle.publisher;

import com.vungle.publisher.jy.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ka implements Factory<a> {
    static final /* synthetic */ boolean f10604a = (!ka.class.desiredAssertionStatus());
    private final MembersInjector<a> f10605b;

    public /* synthetic */ Object get() {
        return m13559a();
    }

    public ka(MembersInjector<a> membersInjector) {
        if (f10604a || membersInjector != null) {
            this.f10605b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13559a() {
        return (a) MembersInjectors.injectMembers(this.f10605b, new a());
    }

    public static Factory<a> m13558a(MembersInjector<a> membersInjector) {
        return new ka(membersInjector);
    }
}
