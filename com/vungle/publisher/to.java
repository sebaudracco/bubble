package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class to implements Factory<tn> {
    static final /* synthetic */ boolean f11099a = (!to.class.desiredAssertionStatus());
    private final MembersInjector<tn> f11100b;

    public /* synthetic */ Object get() {
        return m13950a();
    }

    public to(MembersInjector<tn> membersInjector) {
        if (f11099a || membersInjector != null) {
            this.f11100b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public tn m13950a() {
        return (tn) MembersInjectors.injectMembers(this.f11100b, new tn());
    }

    public static Factory<tn> m13949a(MembersInjector<tn> membersInjector) {
        return new to(membersInjector);
    }
}
