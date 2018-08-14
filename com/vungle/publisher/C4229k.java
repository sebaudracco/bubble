package com.vungle.publisher;

import com.vungle.publisher.c.b;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4229k implements Factory<b> {
    static final /* synthetic */ boolean f10602a = (!C4229k.class.desiredAssertionStatus());
    private final MembersInjector<b> f10603b;

    public /* synthetic */ Object get() {
        return m13557a();
    }

    public C4229k(MembersInjector<b> membersInjector) {
        if (f10602a || membersInjector != null) {
            this.f10603b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public b m13557a() {
        return (b) MembersInjectors.injectMembers(this.f10603b, new b());
    }

    public static Factory<b> m13556a(MembersInjector<b> membersInjector) {
        return new C4229k(membersInjector);
    }
}
