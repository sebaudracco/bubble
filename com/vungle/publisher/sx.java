package com.vungle.publisher;

import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class sx implements MembersInjector<sv> {
    static final /* synthetic */ boolean f11065a = (!sx.class.desiredAssertionStatus());
    private final Provider<ConnectivityManager> f11066b;
    private final Provider<ta> f11067c;
    private final Provider<TelephonyManager> f11068d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13918a((sv) obj);
    }

    public sx(Provider<ConnectivityManager> provider, Provider<ta> provider2, Provider<TelephonyManager> provider3) {
        if (f11065a || provider != null) {
            this.f11066b = provider;
            if (f11065a || provider2 != null) {
                this.f11067c = provider2;
                if (f11065a || provider3 != null) {
                    this.f11068d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<sv> m13917a(Provider<ConnectivityManager> provider, Provider<ta> provider2, Provider<TelephonyManager> provider3) {
        return new sx(provider, provider2, provider3);
    }

    public void m13918a(sv svVar) {
        if (svVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        svVar.a = (ConnectivityManager) this.f11066b.get();
        svVar.b = this.f11067c;
        svVar.c = (TelephonyManager) this.f11068d.get();
    }
}
