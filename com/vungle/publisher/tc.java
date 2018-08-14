package com.vungle.publisher;

import android.content.Context;
import android.net.ConnectivityManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class tc implements MembersInjector<ta> {
    static final /* synthetic */ boolean f11076a = (!tc.class.desiredAssertionStatus());
    private final Provider<Context> f11077b;
    private final Provider<sz> f11078c;
    private final Provider<qg> f11079d;
    private final Provider<ConnectivityManager> f11080e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13925a((ta) obj);
    }

    public tc(Provider<Context> provider, Provider<sz> provider2, Provider<qg> provider3, Provider<ConnectivityManager> provider4) {
        if (f11076a || provider != null) {
            this.f11077b = provider;
            if (f11076a || provider2 != null) {
                this.f11078c = provider2;
                if (f11076a || provider3 != null) {
                    this.f11079d = provider3;
                    if (f11076a || provider4 != null) {
                        this.f11080e = provider4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ta> m13924a(Provider<Context> provider, Provider<sz> provider2, Provider<qg> provider3, Provider<ConnectivityManager> provider4) {
        return new tc(provider, provider2, provider3, provider4);
    }

    public void m13925a(ta taVar) {
        if (taVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        taVar.a = (Context) this.f11077b.get();
        taVar.b = (sz) this.f11078c.get();
        taVar.c = (qg) this.f11079d.get();
        taVar.d = (ConnectivityManager) this.f11080e.get();
    }
}
