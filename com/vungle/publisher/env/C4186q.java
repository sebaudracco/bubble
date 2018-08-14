package com.vungle.publisher.env;

import android.content.SharedPreferences;
import com.vungle.publisher.pj.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4186q implements MembersInjector<o> {
    static final /* synthetic */ boolean f10040a = (!C4186q.class.desiredAssertionStatus());
    private final Provider<a> f10041b;
    private final Provider<SharedPreferences> f10042c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13165a((o) obj);
    }

    public C4186q(Provider<a> provider, Provider<SharedPreferences> provider2) {
        if (f10040a || provider != null) {
            this.f10041b = provider;
            if (f10040a || provider2 != null) {
                this.f10042c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<o> m13164a(Provider<a> provider, Provider<SharedPreferences> provider2) {
        return new C4186q(provider, provider2);
    }

    public void m13165a(o oVar) {
        if (oVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        oVar.b = (a) this.f10041b.get();
        oVar.c = (SharedPreferences) this.f10042c.get();
    }
}
