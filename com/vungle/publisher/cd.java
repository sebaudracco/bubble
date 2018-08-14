package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.bd.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cd implements MembersInjector<cb> {
    static final /* synthetic */ boolean f9833a = (!cd.class.desiredAssertionStatus());
    private final Provider<lm> f9834b;
    private final Provider<a> f9835c;
    private final Provider<qg> f9836d;
    private final Provider<Context> f9837e;

    public /* synthetic */ void injectMembers(Object obj) {
        m12911a((cb) obj);
    }

    public cd(Provider<lm> provider, Provider<a> provider2, Provider<qg> provider3, Provider<Context> provider4) {
        if (f9833a || provider != null) {
            this.f9834b = provider;
            if (f9833a || provider2 != null) {
                this.f9835c = provider2;
                if (f9833a || provider3 != null) {
                    this.f9836d = provider3;
                    if (f9833a || provider4 != null) {
                        this.f9837e = provider4;
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

    public static MembersInjector<cb> m12910a(Provider<lm> provider, Provider<a> provider2, Provider<qg> provider3, Provider<Context> provider4) {
        return new cd(provider, provider2, provider3, provider4);
    }

    public void m12911a(cb cbVar) {
        if (cbVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        cbVar.a = (lm) this.f9834b.get();
        cbVar.b = (a) this.f9835c.get();
        cbVar.c = (qg) this.f9836d.get();
        cbVar.d = (Context) this.f9837e.get();
    }
}
