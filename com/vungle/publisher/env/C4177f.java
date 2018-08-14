package com.vungle.publisher.env;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4177f implements Factory<AndroidDevice> {
    static final /* synthetic */ boolean f10020a = (!C4177f.class.desiredAssertionStatus());
    private final MembersInjector<AndroidDevice> f10021b;

    public /* synthetic */ Object get() {
        return m13134a();
    }

    public C4177f(MembersInjector<AndroidDevice> membersInjector) {
        if (f10020a || membersInjector != null) {
            this.f10021b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public AndroidDevice m13134a() {
        return (AndroidDevice) MembersInjectors.injectMembers(this.f10021b, new AndroidDevice());
    }

    public static Factory<AndroidDevice> m13133a(MembersInjector<AndroidDevice> membersInjector) {
        return new C4177f(membersInjector);
    }
}
