package com.vungle.publisher.env;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.vungle.publisher.bw;
import com.vungle.publisher.qg;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4176c implements MembersInjector<a> {
    static final /* synthetic */ boolean f10015a = (!C4176c.class.desiredAssertionStatus());
    private final Provider<Context> f10016b;
    private final Provider<qg> f10017c;
    private final Provider<bw> f10018d;
    private final Provider<WifiManager> f10019e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13132a((a) obj);
    }

    public C4176c(Provider<Context> provider, Provider<qg> provider2, Provider<bw> provider3, Provider<WifiManager> provider4) {
        if (f10015a || provider != null) {
            this.f10016b = provider;
            if (f10015a || provider2 != null) {
                this.f10017c = provider2;
                if (f10015a || provider3 != null) {
                    this.f10018d = provider3;
                    if (f10015a || provider4 != null) {
                        this.f10019e = provider4;
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

    public static MembersInjector<a> m13131a(Provider<Context> provider, Provider<qg> provider2, Provider<bw> provider3, Provider<WifiManager> provider4) {
        return new C4176c(provider, provider2, provider3, provider4);
    }

    public void m13132a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.c = (Context) this.f10016b.get();
        aVar.d = (qg) this.f10017c.get();
        aVar.e = (bw) this.f10018d.get();
        aVar.a = (Context) this.f10016b.get();
        aVar.b = (WifiManager) this.f10019e.get();
    }
}
