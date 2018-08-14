package com.vungle.publisher;

import android.content.Context;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ck implements Factory<ci> {
    static final /* synthetic */ boolean f9844a = (!ck.class.desiredAssertionStatus());
    private final MembersInjector<ci> f9845b;
    private final Provider<Context> f9846c;

    public /* synthetic */ Object get() {
        return m12931a();
    }

    public ck(MembersInjector<ci> membersInjector, Provider<Context> provider) {
        if (f9844a || membersInjector != null) {
            this.f9845b = membersInjector;
            if (f9844a || provider != null) {
                this.f9846c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ci m12931a() {
        return (ci) MembersInjectors.injectMembers(this.f9845b, new ci((Context) this.f9846c.get()));
    }

    public static Factory<ci> m12930a(MembersInjector<ci> membersInjector, Provider<Context> provider) {
        return new ck(membersInjector, provider);
    }
}
