package com.vungle.publisher;

import com.vungle.publisher.fq.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class fs implements Factory<a> {
    static final /* synthetic */ boolean f10139a = (!fs.class.desiredAssertionStatus());
    private final MembersInjector<a> f10140b;

    public /* synthetic */ Object get() {
        return m13219a();
    }

    public fs(MembersInjector<a> membersInjector) {
        if (f10139a || membersInjector != null) {
            this.f10140b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13219a() {
        return (a) MembersInjectors.injectMembers(this.f10140b, new a());
    }

    public static Factory<a> m13218a(MembersInjector<a> membersInjector) {
        return new fs(membersInjector);
    }
}
