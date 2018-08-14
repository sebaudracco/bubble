package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class vq implements Factory<vc> {
    static final /* synthetic */ boolean f11173a = (!vq.class.desiredAssertionStatus());
    private final MembersInjector<vc> f11174b;

    public /* synthetic */ Object get() {
        return m14007a();
    }

    public vq(MembersInjector<vc> membersInjector) {
        if (f11173a || membersInjector != null) {
            this.f11174b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public vc m14007a() {
        return (vc) MembersInjectors.injectMembers(this.f11174b, new vc());
    }

    public static Factory<vc> m14006a(MembersInjector<vc> membersInjector) {
        return new vq(membersInjector);
    }
}
