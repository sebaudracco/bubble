package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class bx implements Factory<bw> {
    static final /* synthetic */ boolean f9822a = (!bx.class.desiredAssertionStatus());
    private final MembersInjector<bw> f9823b;

    public /* synthetic */ Object get() {
        return m12902a();
    }

    public bx(MembersInjector<bw> membersInjector) {
        if (f9822a || membersInjector != null) {
            this.f9823b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public bw m12902a() {
        return (bw) MembersInjectors.injectMembers(this.f9823b, new bw());
    }

    public static Factory<bw> m12901a(MembersInjector<bw> membersInjector) {
        return new bx(membersInjector);
    }
}
