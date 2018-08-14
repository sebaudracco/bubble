package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ln implements Factory<lm> {
    static final /* synthetic */ boolean f10701a = (!ln.class.desiredAssertionStatus());
    private final MembersInjector<lm> f10702b;

    public /* synthetic */ Object get() {
        return m13628a();
    }

    public ln(MembersInjector<lm> membersInjector) {
        if (f10701a || membersInjector != null) {
            this.f10702b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public lm m13628a() {
        return (lm) MembersInjectors.injectMembers(this.f10702b, new lm());
    }

    public static Factory<lm> m13627a(MembersInjector<lm> membersInjector) {
        return new ln(membersInjector);
    }
}
