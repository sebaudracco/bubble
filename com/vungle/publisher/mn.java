package com.vungle.publisher;

import com.vungle.publisher.mj.a;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class mn implements Factory<a> {
    static final /* synthetic */ boolean f10754a = (!mn.class.desiredAssertionStatus());
    private final MembersInjector<a> f10755b;

    public /* synthetic */ Object get() {
        return m13703a();
    }

    public mn(MembersInjector<a> membersInjector) {
        if (f10754a || membersInjector != null) {
            this.f10755b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public a m13703a() {
        return (a) MembersInjectors.injectMembers(this.f10755b, new a());
    }

    public static Factory<a> m13702a(MembersInjector<a> membersInjector) {
        return new mn(membersInjector);
    }
}
