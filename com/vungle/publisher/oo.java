package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.om.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class oo implements MembersInjector<a> {
    static final /* synthetic */ boolean f10873a = (!oo.class.desiredAssertionStatus());
    private final Provider<Context> f10874b;
    private final Provider<qg> f10875c;
    private final Provider<lz> f10876d;
    private final Provider<lw> f10877e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13795a((a) obj);
    }

    public oo(Provider<Context> provider, Provider<qg> provider2, Provider<lz> provider3, Provider<lw> provider4) {
        if (f10873a || provider != null) {
            this.f10874b = provider;
            if (f10873a || provider2 != null) {
                this.f10875c = provider2;
                if (f10873a || provider3 != null) {
                    this.f10876d = provider3;
                    if (f10873a || provider4 != null) {
                        this.f10877e = provider4;
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

    public static MembersInjector<a> m13794a(Provider<Context> provider, Provider<qg> provider2, Provider<lz> provider3, Provider<lw> provider4) {
        return new oo(provider, provider2, provider3, provider4);
    }

    public void m13795a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (Context) this.f10874b.get();
        aVar.b = (qg) this.f10875c.get();
        aVar.c = (lz) this.f10876d.get();
        aVar.d = (lw) this.f10877e.get();
    }
}
