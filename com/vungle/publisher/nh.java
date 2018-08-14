package com.vungle.publisher;

import com.vungle.publisher.my.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nh implements MembersInjector<b> {
    static final /* synthetic */ boolean f10801a = (!nh.class.desiredAssertionStatus());
    private final Provider<qg> f10802b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13746a((b) obj);
    }

    public nh(Provider<qg> provider) {
        if (f10801a || provider != null) {
            this.f10802b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<b> m13745a(Provider<qg> provider) {
        return new nh(provider);
    }

    public void m13746a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.eventBus = (qg) this.f10802b.get();
    }
}
