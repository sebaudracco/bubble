package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class en implements Factory<em> {
    static final /* synthetic */ boolean f10010a = (!en.class.desiredAssertionStatus());
    private final MembersInjector<em> f10011b;

    public /* synthetic */ Object get() {
        return m13127a();
    }

    public en(MembersInjector<em> membersInjector) {
        if (f10010a || membersInjector != null) {
            this.f10011b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public em m13127a() {
        return (em) MembersInjectors.injectMembers(this.f10011b, new em());
    }

    public static Factory<em> m13126a(MembersInjector<em> membersInjector) {
        return new en(membersInjector);
    }
}
