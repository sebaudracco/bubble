package com.vungle.publisher;

import com.vungle.publisher.my.b;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ne implements Factory<b> {
    static final /* synthetic */ boolean f10795a = (!ne.class.desiredAssertionStatus());
    private final MembersInjector<b> f10796b;

    public /* synthetic */ Object get() {
        return m13740a();
    }

    public ne(MembersInjector<b> membersInjector) {
        if (f10795a || membersInjector != null) {
            this.f10796b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public b m13740a() {
        return (b) MembersInjectors.injectMembers(this.f10796b, new b());
    }

    public static Factory<b> m13739a(MembersInjector<b> membersInjector) {
        return new ne(membersInjector);
    }
}
