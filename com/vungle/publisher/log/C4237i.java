package com.vungle.publisher.log;

import android.content.Context;
import com.vungle.publisher.env.AndroidDevice;
import com.vungle.publisher.env.o;
import com.vungle.publisher.sv;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4237i implements MembersInjector<g> {
    static final /* synthetic */ boolean f10716a = (!C4237i.class.desiredAssertionStatus());
    private final Provider<Context> f10717b;
    private final Provider<d> f10718c;
    private final Provider<a> f10719d;
    private final Provider<AndroidDevice> f10720e;
    private final Provider<sv> f10721f;
    private final Provider<o> f10722g;

    public /* synthetic */ void injectMembers(Object obj) {
        m13661a((g) obj);
    }

    public C4237i(Provider<Context> provider, Provider<d> provider2, Provider<a> provider3, Provider<AndroidDevice> provider4, Provider<sv> provider5, Provider<o> provider6) {
        if (f10716a || provider != null) {
            this.f10717b = provider;
            if (f10716a || provider2 != null) {
                this.f10718c = provider2;
                if (f10716a || provider3 != null) {
                    this.f10719d = provider3;
                    if (f10716a || provider4 != null) {
                        this.f10720e = provider4;
                        if (f10716a || provider5 != null) {
                            this.f10721f = provider5;
                            if (f10716a || provider6 != null) {
                                this.f10722g = provider6;
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
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<g> m13660a(Provider<Context> provider, Provider<d> provider2, Provider<a> provider3, Provider<AndroidDevice> provider4, Provider<sv> provider5, Provider<o> provider6) {
        return new C4237i(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public void m13661a(g gVar) {
        if (gVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gVar.d = (Context) this.f10717b.get();
        gVar.e = (d) this.f10718c.get();
        gVar.f = (a) this.f10719d.get();
        gVar.g = (AndroidDevice) this.f10720e.get();
        gVar.h = (sv) this.f10721f.get();
        gVar.i = (o) this.f10722g.get();
    }
}
