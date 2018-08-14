package com.vungle.publisher.env;

import android.content.SharedPreferences;
import com.vungle.publisher.qg;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4184m implements MembersInjector<k> {
    static final /* synthetic */ boolean f10035a = (!C4184m.class.desiredAssertionStatus());
    private final Provider<qg> f10036b;
    private final Provider<SharedPreferences> f10037c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13161a((k) obj);
    }

    public C4184m(Provider<qg> provider, Provider<SharedPreferences> provider2) {
        if (f10035a || provider != null) {
            this.f10036b = provider;
            if (f10035a || provider2 != null) {
                this.f10037c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<k> m13160a(Provider<qg> provider, Provider<SharedPreferences> provider2) {
        return new C4184m(provider, provider2);
    }

    public void m13161a(k kVar) {
        if (kVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        kVar.eventBus = (qg) this.f10036b.get();
        kVar.a = (SharedPreferences) this.f10037c.get();
    }
}
