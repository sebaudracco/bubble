package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class re implements Factory<rd> {
    static final /* synthetic */ boolean f10960a = (!re.class.desiredAssertionStatus());
    private final MembersInjector<rd> f10961b;

    public /* synthetic */ Object get() {
        return m13869a();
    }

    public re(MembersInjector<rd> membersInjector) {
        if (f10960a || membersInjector != null) {
            this.f10961b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public rd m13869a() {
        return (rd) MembersInjectors.injectMembers(this.f10961b, new rd());
    }

    public static Factory<rd> m13868a(MembersInjector<rd> membersInjector) {
        return new re(membersInjector);
    }
}
